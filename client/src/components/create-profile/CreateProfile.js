import React, { Component } from "react";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import PropTypes from "prop-types";
import TextFieldGroup from "../common/TextFieldGroup";
import TextAreaFieldGroup from "../common/TextAreaFieldGroup";
import Select from "react-select";
import SelectListGroup from "../common/SelectListGroup";
import InputGroup from "../common/InputGroup";
import { createProfile } from "../../actions/profileActions";

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

      displaySocialInputs: false,
      // handle: "",
      // company: "",
      // website: "",
      // location: "",
      // status: "",
      // skills: "",
      // githubusername: "",
      twitter: "",
      facebook: "",
      linkedin: "",
      youtube: "",
      instagram: "",
      errors: {},
    };

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

  onSubmit(e) {
    e.preventDefault();
    let lang = [];
    let subj = [];
    lang = this.prepareData(this.state.languages);
    subj = this.prepareData(this.state.subjects);
    console.log("lang", lang);
    console.log("subj", subj);
    const profileData = {
      firstname: this.state.firstname,
      lastname: this.state.lastname,
      phonenumber: this.state.phonenumber,
      gender: this.state.gender,
      age: this.state.age,
      majorsubject: this.state.majorsubject,
      location: this.state.location,
      subjects: subj,
      education: this.state.education,
      languages: lang,
      workexperience: this.state.workexperience,
      bio: this.state.bio,

      // twitter: this.state.twitter,
      // facebook: this.state.facebook,
      // linkedin: this.state.linkedin,
      // youtube: this.state.youtube,
      // instagram: this.state.instagram,

      // handle: this.state.handle,
      // company: this.state.company,
      // website: this.state.website,
      // location: this.state.location,
      // status: this.state.status,
      // skills: this.state.skills,
      // githubusername: this.state.githubusername,
    };
    console.log("Profile Data", profileData);
    // this.props.createProfile(profileData, this.props.history);
  }

  render() {
    const { errors, displaySocialInputs } = this.state;

    let socialInputs;

    if (displaySocialInputs) {
      socialInputs = (
        <div>
          <InputGroup
            placeholder="Twitter Profile URL"
            name="twitter"
            icon="fa fa-twitter"
            value={this.state.twitter}
            onChange={this.onChange}
            error={errors.twitter}
          />
          <InputGroup
            placeholder="Facebook Profile URL"
            name="facebook"
            icon="fa fa-facebook"
            value={this.state.facebook}
            onChange={this.onChange}
            error={errors.facebook}
          />
          <InputGroup
            placeholder="Linkedin Profile URL"
            name="linkedin"
            icon="fa fa-linkedin"
            value={this.state.linkedin}
            onChange={this.onChange}
            error={errors.linkedin}
          />
          <InputGroup
            placeholder="Youtube Profile URL"
            name="youtube"
            icon="fa fa-youtube"
            value={this.state.youtube}
            onChange={this.onChange}
            error={errors.youtube}
          />
          <InputGroup
            placeholder="Instagram Profile URL"
            name="instagram"
            icon="fa fa-instagram"
            value={this.state.instagram}
            onChange={this.onChange}
            error={errors.instagram}
          />
        </div>
      );
    }
    // Select options for status
    const options = [
      { label: "* Select Professinal Status", value: 0 },
      { label: "Developer", value: "Developer" },
      { label: "Junior Developer", value: "Junior Developer" },
      { label: "Senior Developer", value: "Senior Developer" },
      { label: "Manager", value: "Manager" },
      { label: "Student or Learning", value: "Student or Learning" },
      { label: "Instructor or Teacher", value: "Instructor or Teacher" },
      { label: "Intern", value: "Intern" },
      { label: "Other", value: "Other" },
    ];

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
        style={{ width: "1200px", height: "800px" }}
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
                    error={errors.firstname}
                    info="Your first name"
                  />
                </div>
                <div className="col-md-6">
                  <TextFieldGroup
                    placeholder="Last name"
                    name="lastname"
                    value={this.state.lastname}
                    onChange={this.onChange}
                    error={errors.lastname}
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
                    error={errors.phonenumber}
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
                    error={errors.education}
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
                    error={errors.majorsubject}
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
                    Slect languages
                  </small>
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
                    error={errors.workExperience}
                    info="Give us an idea of how long you have worked"
                  />
                </div>
                <div className="col-md-6">
                  <TextAreaFieldGroup
                    placeholder="Short bio"
                    name="bio"
                    value={this.state.bio}
                    onChange={this.onChange}
                    error={errors.bio}
                    info="Tell us a little bit about yourself"
                  />
                </div>
              </div>
              {socialInputs}
              <input
                type="submit"
                value="Submit"
                className="btn btn-info btn-block mt-4 mb-5"
              />
            </form>
            {/* <div className="row">
              <form onSubmit={this.onSubmit}>
                <div className="row">
                  <div className="col-md-6">
                    <TextFieldGroup
                      placeholder="First name"
                      name="firstName"
                      value={this.state.firstname}
                      onChange={this.onChange}
                      error={errors.firstname}
                      info="Your first name"
                    />
                  </div>
                  <div className="col-md-6">
                    <TextFieldGroup
                      placeholder="Last name"
                      name="lastName"
                      value={this.state.lastname}
                      onChange={this.onChange}
                      error={errors.lastname}
                      info="Your last name"
                    />
                  </div>
                </div>
                <TextAreaFieldGroup
                  placeholder="* Phone Number"
                  name="phonenumber"
                  value={this.state.phonenumber}
                  onChange={this.onChange}
                  error={errors.phonenumber}
                  info="A phone number for your profile and for you to be called."
                />
                <TextFieldGroup
                  placeholder="* Subjects"
                  name="subjects"
                  value={this.state.subjects}
                  onChange={this.onChange}
                  error={errors.subjects}
                  info="Please use comman separated values (eg. English,Physics,Biology)"
                />
                <SelectListGroup
                  placeholder="Education"
                  name="education"
                  value={this.state.education}
                  onChange={this.onChange}
                  options={educationOptions}
                  error={errors.education}
                  info="Give us an idea of where you are at in your education"
                />
                <SelectListGroup
                  placeholder="Exprience"
                  name="workexperience"
                  value={this.state.workexperience}
                  onChange={this.onChange}
                  options={workExperienceOptions}
                  error={errors.workExperience}
                  info="Give us an idea of how long you have worked"
                />
                <TextFieldGroup
                  placeholder="Location"
                  name="location"
                  value={this.state.location}
                  onChange={this.onChange}
                  error={errors.location}
                  info="City or city & state suggested (eg. Boston, MA)"
                />
                <SelectListGroup
                  placeholder="Status"
                  name="status"
                  value={this.state.status}
                  onChange={this.onChange}
                  options={options}
                  error={errors.status}
                  info="Give us an idea of where you are at in your career"
                />
                <TextAreaFieldGroup
                  placeholder="Short bio"
                  name="bio"
                  value={this.state.bio}
                  onChange={this.onChange}
                  error={errors.bio}
                  info="Tell us a little bit about yourself"
                />
                <div className="mb-3">
                  <button
                    type="button"
                    onClick={() => {
                      this.setState((prevState) => ({
                        displaySocialInputs: !prevState.displaySocialInputs,
                      }));
                    }}
                    className="btn btn-light"
                  >
                    Add Social Network Links
                  </button>
                  <span className="text-muted"> Optional</span>
                </div>
                {socialInputs}
                <input
                  type="submit"
                  value="Submit"
                  className="btn btn-info btn-block mt-4 mb-5"
                />
              </form>
            </div> */}
          </div>
        </div>
      </div>
    );
  }
}

CreateProfile.propTypes = {
  profile: PropTypes.object.isRequired,
  errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  profile: state.profile,
  errors: state.errors,
});
export default connect(mapStateToProps, { createProfile })(
  withRouter(CreateProfile)
);
