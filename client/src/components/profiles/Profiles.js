import React, { Component } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import Spinner from "../common/spinner";
import { getProfiles } from "../../actions/profileActions";
import ProfileItem from "./ProfileItem";

// import profiles from "./mockProfileData";

export const filter = (list, search) =>
  list &&
  list.filter((d) => {
    let values = [];
    values.push(d.lawyer.first_name);
    values.push(d.lawyer.last_name);
    values.push(...Object.values(d.legal_fields));
    console.log("values", values);

    for (let i = 0; i < values.length; i++) {
      if (
        values[i] &&
        values[i]
          .toString()
          .toLocaleLowerCase()
          .includes(search.toLocaleLowerCase())
      )
        return true;
    }
    return false;
  });

class Profiles extends Component {
  constructor() {
    super();
    this.state = {
      searchInput: "",
    };
    this.onChange = this.onChange.bind(this);
  }
  componentDidMount() {
    this.props.getProfiles();
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  render() {
    const { profiles, loading } = this.props.profile;
    const { isAuthenticated } = this.props.auth;
    let filteredTutors =
      this.state.searchInput.length > 0
        ? profiles.filter((i) => {
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
        : profiles;
    let profileContent;

    if (profiles === null || loading) {
      profileContent = <Spinner />;
    } else {
      if (profiles.length > 0) {
        profileContent = filteredTutors.map((profile) => (
          <ProfileItem
            key={profile.id}
            profile={profile}
            isAuthenticated={isAuthenticated}
          />
        ));
      } else {
        profileContent = (
          <div>
            <h4
              style={{
                width: "200px",
                margin: "auto",
                marginTop: "100px",
                display: "block",
              }}
            >
              No tutors found ...
            </h4>
          </div>
        );
      }
    }
    return (
      <div className="profiles">
        <div className="container-fluid" sytle={{ height: "800px" }}>
          <div className="row">
            <div className="col-md-12 mt-4">
              <h1 className="display-4 text-center">Find tutors here</h1>
              <p className="lead text-center">Browse and contact with tutors</p>
              <div className="row">
                <div className="input-group col-md-12 mb-3">
                  <span className="input-group-text" id="basic-addon1">
                    <i className="fas fa-search"></i>
                  </span>
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
Profiles.propTypes = {
  auth: PropTypes.object.isRequired,
  getProfiles: PropTypes.func.isRequired,
  profile: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  auth: state.auth,
  profile: state.profile,
});

export default connect(mapStateToProps, { getProfiles })(Profiles);
