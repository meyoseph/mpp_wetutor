import React, { Component } from "react";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import PropTypes from "prop-types";
import TextFieldGroup from "../common/TextFieldGroup";
import TextAreaFieldGroup from "../common/TextAreaFieldGroup";
import Select from "react-select";
import SelectListGroup from "../common/SelectListGroup";
import { createProfile } from "../../actions/profileActions";
import S3 from "react-s3";

class CreateProfile extends Component {
  constructor(props) {
    super(props);
    this.state = {
      firstname: "",
      lastname: "",
      gender: "",
      age: 1,
      majorsubject: "",
      subjects: [],
      education: "",
      location: "",
      languages: [],
      workexperience: "",
      phonenumber: "",
      bio: "",
      profilePic: "",
      loading: false,
      errors: {},
    };
    this.fileInput = React.createRef();

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  handleLanguagesChange = (selectedLanguages) => {
    this.setState({ languages: selectedLanguages });
  };

  handleSubjectsChange = (selectedSubjects) => {
    this.setState({ subjects: selectedSubjects });
  };

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }
  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  prepareData = (dict) => {
    let temp1 = Object.keys(dict).map((data, key) => {
      return dict[data];
    });

    let temp2 = temp1.map((temp) => {
      return temp["label"];
    });

    return temp2;
  };

  async onSubmit(e) {
    this.setState({ loading: true });
    e.preventDefault();

    // Make sure profile picture is uploaded and image source is loaded first
    // Allow the tutor not to add his or her profile picture
    if (this.fileInput.current.files.length > 0) {
      let file = this.fileInput.current.files[0];
      const config = {
        bucketName: process.env.REACT_APP_S3_BUCKET_NAME,
        region: process.env.REACT_APP_S3_REGION,
        accessKeyId: process.env.REACT_APP_S3_ACCESS_ID,
        secretAccessKey: process.env.REACT_APP_S3_ACCESS_KEY,
      };
      await S3.uploadFile(file, config)
        .then((data) => {
          console.log(data.location);
          this.setState({ profilePic: data.location });
        })
        .catch((err) => {
          this.setState({ errors: err.data });
        });
    }
    let lang = [];
    let subj = [];
    lang = this.prepareData(this.state.languages);
    subj = this.prepareData(this.state.subjects);
    const profileData = {
      firstName: this.state.firstname,
      lastName: this.state.lastname,
      phoneNumber: this.state.phonenumber,
      gender: this.state.gender,
      age: this.state.age.toString(),
      majorSubject: this.state.majorsubject,
      location: this.state.location,
      subjects: subj,
      educations: this.state.education,
      languages: lang,
      workExperiences: this.state.workexperience,
      motive: this.state.bio,
      profilePic: this.state.profilePic,
      tutorId: this.props.auth.userInfo.id,
    };
    this.props.createProfile(profileData, this.props.history);
    this.setState({ loading: false });
  }

  render() {
    const { errors } = this.state;

    // Select options for Education
    const educationOptions = [
      { label: "* Select your current education level", value: 0 },
      { label: "High school graduate", value: "High school graduate" },
      { label: "Diploma", value: "Diploma" },
      { label: "Degree", value: "Degree" },
      { label: "Masters", value: "Masters" },
    ];

    // Select options for Subjects
    const workExperienceOptions = [
      { label: "* Select work experince", value: 0 },
      { label: "None", value: "none" },
      { label: "1 year", value: "1 year" },
      { label: "2 Years", value: "2 Years" },
      { label: "3 Years", value: "3 Years" },
      { label: "Morethan 3 years", value: "Morethan 3 years" },
    ];

    // Select options for Languages
    const languageOptions = [
      { value: "English", label: "English" },
      { value: "French", label: "French" },
      { value: "Arabic", label: "Arabic" },
      { value: "German", label: "German" },
      { value: "Espanol", label: "Espanol" },
      { value: "Russian", label: "Russian" },
      { value: "Turkish", label: "Turkish" },
      { value: "Italian", label: "Italian" },
    ];

    // Select options for Gender
    const genderOptions = [
      { label: "* Select your gender", value: 0 },
      { value: "Male", label: "Male" },
      { value: "Female", label: "Female" },
    ];

    const subjectsOptions = [
      { label: "* Add more subjects", value: 0 },
      { value: "English", label: "English" },
      { value: "Mathematics", label: "Mathematics" },
      { value: "Physics", label: "Physics" },
      { value: "Biology", label: "Biology" },
      { value: "Chemistry", label: "Chemistry" },
      { value: "Geography", label: "Geography" },
      { value: "Information Technology", label: "Information Technology" },
    ];

    return (
      <div
        className="create-profile shadow-lg mb-5 mt-5 bg-white rounded"
        style={{ width: "1200px", height: errors ? "980px" : "700px" }}
      >
        <div className="container">
          <div className="row">
            <div className="col-md-12 m-auto">
              <h1 className="display-4 text-center">Create Your Profile</h1>
              <p className="lead text-center">
                Let's get some information to make your profile stand out
              </p>
              <hr style={{ width: "1100px" }} />
              <small className="d-block pb-3">* = required fields</small>
            </div>
          </div>
          <div className="col-md-12 m-auto">
            <form onSubmit={this.onSubmit}>
              <div className="row">
                <div className="col-md-6">
                  <TextFieldGroup
                    placeholder="First name"
                    name="firstname"
                    value={this.state.firstname}
                    onChange={this.onChange}
                    error={errors.first_name}
                    info="Your first name"
                  />
                </div>
                <div className="col-md-6">
                  <TextFieldGroup
                    placeholder="Last name"
                    name="lastname"
                    value={this.state.lastname}
                    onChange={this.onChange}
                    error={errors.last_name}
                    info="Your last name"
                  />
                </div>
              </div>
              <div className="row">
                <div className="col-md-6">
                  <TextFieldGroup
                    placeholder="* Phone Number"
                    name="phonenumber"
                    value={this.state.phonenumber}
                    onChange={this.onChange}
                    error={errors.phone_number}
                    info="A phone number for your profile and for you to be called."
                  />
                </div>
                <div className="col-md-6">
                  <TextFieldGroup
                    placeholder="Age"
                    name="age"
                    value={this.state.age}
                    onChange={this.onChange}
                    type="number"
                    error={errors.age}
                    info="Your age"
                  />
                </div>
              </div>
              <div className="row">
                <div className="col-md-6">
                  <SelectListGroup
                    placeholder="Gender"
                    name="gender"
                    value={this.state.gender}
                    onChange={this.onChange}
                    options={genderOptions}
                    error={errors.gender}
                    info="Your gender"
                  />
                </div>
                <div className="col-md-6">
                  <SelectListGroup
                    placeholder="Education"
                    name="education"
                    value={this.state.education}
                    onChange={this.onChange}
                    options={educationOptions}
                    error={errors.educations}
                    info="Give us an idea of where you are at in your education"
                  />
                </div>
              </div>
              <div className="row">
                <div className="col-md-6">
                  <TextFieldGroup
                    placeholder="* Major Subject"
                    name="majorsubject"
                    value={this.state.majorsubject}
                    onChange={this.onChange}
                    error={errors.major_subject}
                    info="Please write your major subject(eg. English or Physics or Biology)"
                  />
                </div>
                <div className="col-md-6">
                  <TextFieldGroup
                    placeholder="Location"
                    name="location"
                    value={this.state.location}
                    onChange={this.onChange}
                    error={errors.location}
                    info="City or city & state suggested (eg. Boston, MA)"
                  />
                </div>
              </div>
              <div className="row">
                <div className="col-md-6 mb-3">
                  <Select
                    name="languages"
                    onChange={this.handleLanguagesChange}
                    options={languageOptions}
                    className="basic-multi-select"
                    isMulti
                  />
                  <small className="form-text text-muted">
                    Select languages
                  </small>
                  {errors.languages && (
                    <small>
                      <div style={{ color: "red" }}>{errors.languages}</div>
                    </small>
                  )}
                </div>
                <div className="col-md-6 mb-3">
                  <Select
                    options={subjectsOptions}
                    onChange={this.handleSubjectsChange}
                    className="basic-multi-select"
                    isMulti
                  />
                  <small className="form-text text-muted">
                    Please select additional subjects
                  </small>
                  {errors.subjects && (
                    <small>
                      <div style={{ color: "red" }}>{errors.subjects}</div>
                    </small>
                  )}
                </div>
              </div>
              <div className="row">
                <div className="col-md-6">
                  <SelectListGroup
                    placeholder="Exprience"
                    name="workexperience"
                    value={this.state.workexperience}
                    onChange={this.onChange}
                    options={workExperienceOptions}
                    error={errors.work_experiences}
                    info="Give us an idea of how long you have worked"
                  />
                </div>
                <div className="col-md-6">
                  <TextAreaFieldGroup
                    placeholder="Short bio"
                    name="bio"
                    value={this.state.bio}
                    onChange={this.onChange}
                    error={errors.motive}
                    info="Tell us a little bit about yourself"
                  />
                </div>
              </div>
              <div className="row">
                <div className="col-md-6">
                  <label for="exampleFormControlFile1">Select file</label>
                  <input
                    type="file"
                    ref={this.fileInput}
                    accept="image/png, image/gif, image/jpeg"
                    class="form-control-file"
                    id="exampleFormControlFile1"
                  />
                  <small className="form-text text-muted">
                    Please select your profile pic
                  </small>
                </div>
              </div>
              <div className="row">
                <div className="col-md-5"></div>
                <div className="col-md-2">
                  {this.state.loading && (
                    <div className="spinner-border text-info " role="status">
                      <span className="sr-only justify-content-center">
                        Loading...
                      </span>
                    </div>
                  )}
                </div>
                <div className="col-md-5"></div>
              </div>
              <input
                type="submit"
                value="Submit"
                className="btn btn-info btn-block mt-4 mb-5"
              />
            </form>
          </div>
        </div>
      </div>
    );
  }
}

CreateProfile.propTypes = {
  auth: PropTypes.object.isRequired,
  profile: PropTypes.object.isRequired,
  errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  auth: state.auth,
  profile: state.profile,
  errors: state.errors,
});
export default connect(mapStateToProps, { createProfile })(
  withRouter(CreateProfile)
);
