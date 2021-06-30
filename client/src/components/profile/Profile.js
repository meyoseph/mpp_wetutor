import React, { Component } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";
import Spinner from "../common/spinner";
import { getProfileByTutorId } from "../../actions/profileActions";
import ProfileBody from "./ProfileBody";

class Profile extends Component {
  componentDidMount() {
    if (this.props.match.params.tutorId) {
      this.props.getProfileByTutorId(this.props.match.params.tutorId);
    }
  }

  componentWillReceiveProps(nextProps){
    if(nextProps.profile.profile === null  && this.props.profile.loading){
      this.props.history.push('/not-found');
    }
  }
  render() {
    const { profile, loading } = this.props.profile;
    const { userInfo } = this.props.auth;

    let profileContent;

    let homeLink = "/dashboard";
    if (userInfo && userInfo.roles && userInfo.roles[0] !== undefined) {
      homeLink =
        userInfo.roles[0].roleName === "admin"
          ? "/admin-dashboard"
          : userInfo.roles[0].roleName === "parent"
          ? "/parent-dashboard"
          : "/dashboard";
    }

    if (profile === null || loading) {
      profileContent = <Spinner />;
    } else {
      profileContent = (
        <div>
          <div className="row">
            <div className="col-md-6">
              <Link to={homeLink} className="btn btn-light mb3 float-light">
                Back back
              </Link>
            </div>
            <div className="col-md-6" />
          </div>

          <ProfileBody profile={profile}/>
        </div>
      );
    }
    return (
      <div className="profile mt-4">
        <div className="container">
          <div className="row">
            <div className="col-md-12">{profileContent}</div>
          </div>
        </div>
      </div>
    );
  }
}

Profile.propTypes = {
  getProfileByTutorId: PropTypes.func.isRequired,
  profile: PropTypes.object.isRequired,
  auth: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  profile: state.profile,
  auth: state.auth
});

export default connect(mapStateToProps, { getProfileByTutorId })(Profile);