import React, { Component } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import Spinner from "../common/spinner";
import { getProfiles } from "../../actions/profileActions";
import ProfileItem from "./ProfileItem";

import profiles from "./mockProfileData";

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
    // const { profiles, loading } = this.props.profile;

    let filteredTutors =
      this.state.searchInput.length > 0
        ? profiles.filter((i) => {
            let firstSearchValue =
              i.firstname && i.firstname.toLocaleLowerCase();
            let secondSearchValue =
              i.lastname && i.lastname.toLocaleLowerCase();

            let response =
              firstSearchValue.indexOf(
                this.state.searchInput.toLocaleLowerCase()
              ) !== -1 ||
              secondSearchValue.indexOf(
                this.state.searchInput.toLocaleLowerCase()
              ) !== -1;
            return response;
          })
        : profiles;
    let loading = false;
    let profileContent;

    if (profiles === null || loading) {
      profileContent = <Spinner />;
    } else {
      if (profiles.length > 0) {
        profileContent = filteredTutors.map((profile) => (
          <ProfileItem key={profile._id} profile={profile} />
        ));
      } else {
        profileContent = <h4>No profiles found ...</h4>;
      }
    }
    return (
      <div className="profiles">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Tutor Profile</h1>
              <p className="lead text-center">Browse and contact with tutors</p>
              <div className="row">
                <div className="col-md-9">
                  <input
                    value={this.state.searchInput}
                    className="form-control mt-0"
                    name="searchInput"
                    onChange={this.onChange}
                    type="search"
                    placeholder="Live Search using name"
                    aria-label="Search"
                  />
                </div>
                <div
                  className="col-md-3"
                  style={{ marginLeft: "0px", marginTop: "9px" }}
                >
                  <button
                    class="btn btn-info"
                    type="submit"
                    style={{ width: "100%" }}
                  >
                    Search
                  </button>
                </div>
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
  getProfiles: PropTypes.func.isRequired,
  profile: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  profile: state.profile,
});

export default connect(mapStateToProps, { getProfiles })(Profiles);
