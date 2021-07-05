import React, { Component } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { logoutUser, clearFullUserInfo } from "../../actions/authActions";
import { clearCurrentProfile } from "../../actions/profileActions";

class Navbar extends Component {
  onLogoutClick(e) {
    e.preventDefault();
    this.props.clearCurrentProfile();
    this.props.clearFullUserInfo();
    this.props.logoutUser();
  }

  render() {
    const { isAuthenticated, user, userInfo } = this.props.auth;
    const { profile } = this.props.profile;

    let homeLink = "dashboard";
    let profileIcon = "https://www.gravatar.com/avatar/24fe3615bdba49bdf3e9ffb23f1b7bfd?s=200&r=pg&d=mm";

    if(profile && profile.profilePic){
      profileIcon = profile.profilePic;
    }

    if (userInfo && userInfo.roles && userInfo.roles[0] !== undefined) {
      homeLink =
        userInfo.roles[0].roleName === "admin"
          ? "admin-dashboard"
          : userInfo.roles[0].roleName === "parent"
          ? "parent-dashboard"
          : "dashboard";
    }

    const guestLinks = (
      <ul className="navbar-nav ml-auto">
        <li className="nav-item">
          <Link className="nav-link" to="/register">
            Sign Up
          </Link>
        </li>
        <li className="nav-item">
          <Link className="nav-link" to="/login">
            Login
          </Link>
        </li>
      </ul>
    );

    const authLinks = (
      <ul className="navbar-nav ml-auto">
        <li className="nav-item">
          <a
            href=""
            onClick={this.onLogoutClick.bind(this)}
            className="nav-link"
          >
            {/* {user.sub} */}
            <img
              className="rounded-circle"
              src={profileIcon}
              alt={user.name}
              style={{ width: "25px", marginRight: "5px" }}
              title="You must have a Gravatar connected to your email to display an image"
            />{" "}
            Logout
          </a>
        </li>
      </ul>
    );

    return (
      // <!-- Navbar -->
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
        <div className="container">
          <Link className="navbar-brand" to={isAuthenticated ? homeLink : "/"}>
            WeTutor
          </Link>
          <button
            className="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#mobile-nav"
          >
            <span className="navbar-toggler-icon"></span>
          </button>

          <div className="collapse navbar-collapse" id="mobile-nav">
            <ul className="navbar-nav mr-auto">
              <li className="nav-item">
                <Link className="nav-link" to="/tutors">
                  {" "}
                  Tutors
                </Link>
              </li>
            </ul>
            {isAuthenticated ? authLinks : guestLinks}
          </div>
        </div>
      </nav>
    );
  }
}

Navbar.propTypes = {
  logoutUser: PropTypes.func.isRequired,
  clearCurrentProfile: PropTypes.func.isRequired,
  clearFullUserInfo: PropTypes.func.isRequired,
  auth: PropTypes.object.isRequired,
  profile: PropTypes.object.isRequired,
};
const mapStateProps = (state) => ({
  auth: state.auth,
  profile: state.profile
});
export default connect(mapStateProps, {
  logoutUser,
  clearCurrentProfile,
  clearFullUserInfo,
})(Navbar);
