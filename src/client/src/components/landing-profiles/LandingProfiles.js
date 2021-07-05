import React, { Component } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import Spinner from "../common/spinner";
import { getTutorsProfile } from "../../actions/profileActions";
import LandingProfileItem from "./LandingProfileItem";

// import profiles from "./mockProfileData";

class LandingProfiles extends Component {
  constructor() {
    super();
    this.state = {
      searchInput: "",
    };
    this.onChange = this.onChange.bind(this);
  }
  componentDidMount() {
    this.props.getTutorsProfile();
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  render() {
    const { customProfiles, loading } = this.props.profile;
    const { isAuthenticated } = this.props.auth;
    let filteredTutors =
      this.state.searchInput.length > 0
        ? customProfiles.filter((i) => {
            let firstSearchValue =
              i.firstName && i.firstName.toLocaleLowerCase();
            let secondSearchValue =
              i.lastName && i.lastName.toLocaleLowerCase();
            let thirdSearchValue =
              i.educations && i.educations.toLocaleLowerCase();
            let forthSearchValue =
              i.workExperiences && i.workExperiences.toLocaleLowerCase();

            let response =
              firstSearchValue.indexOf(
                this.state.searchInput.toLocaleLowerCase()
              ) !== -1 ||
              secondSearchValue.indexOf(
                this.state.searchInput.toLocaleLowerCase()
              ) !== -1 ||
              thirdSearchValue.indexOf(
                this.state.searchInput.toLocaleLowerCase()
              ) !== -1 ||
              forthSearchValue.indexOf(
                this.state.searchInput.toLocaleLowerCase()
              ) !== -1;
            return response;
          })
        : customProfiles;
    let profileContent;

    if (customProfiles === null || loading) {
      profileContent = <Spinner />;
    } else {
      if (customProfiles.length > 0) {
        profileContent = filteredTutors.map((profile) => (
          <LandingProfileItem key={profile.id} profile={profile} isAuthenticated={isAuthenticated} />
        ));
      } else {
        profileContent = <div><h4 style={{ width: '200px',margin: 'auto', marginTop:"100px",marginBottom:"200px", display: 'block' }}>No tutors found ...</h4></div>;
      }
    }
    return (
      <div className="profiles">
        <div className="container-fluid" sytle={{height:"800px"}}>
          <div className="row">
            <div className="col-md-12 mt-4">
              <h1 className="display-4 text-center">Find tutors here</h1>
              <p className="lead text-center">Browse and contact with tutors</p>
              <div className="row">
                <div className="input-group col-md-12 mb-3">
                  <span className="input-group-text" id="basic-addon1"><i className="fas fa-search"></i></span>
                  <input
                    value={this.state.searchInput}
                    className="form-control mt-0"
                    name="searchInput"
                    onChange={this.onChange}
                    type="search"
                    placeholder="Live Search using name, education level or work experience"
                    aria-label="Search"
                  />
                </div>
                {/* <div
                  className="col-md-3"
                  style={{ marginLeft: "0px", marginTop: "9px" }}
                >
                  <button
                    className="btn btn-info"
                    type="submit"
                    style={{ width: "100%" }}
                  >
                    Search
                  </button>
                </div> */}
              </div>
              {profileContent}
            </div>
          </div>
        </div>
      </div>
    );
  }
}
LandingProfiles.propTypes = {
  auth: PropTypes.object.isRequired,
  getTutorsProfile: PropTypes.func.isRequired,
  profile: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  auth: state.auth,
  profile: state.profile,
});

export default connect(mapStateToProps, { getTutorsProfile })(LandingProfiles);
