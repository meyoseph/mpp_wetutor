import React, { Component } from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import Spinner from "../common/spinner";
import ProfileActions from "./ProfileActions";
import {
  getCurrentProfile,
} from "../../actions/profileActions";
import MyProfile from "./MyProfile";
import { ToastProvider } from "react-toast-notifications";

class Dashboard extends Component {
  componentDidMount() {
    this.props.getCurrentProfile();
  }

  render() {
    const { userInfo } = this.props.auth;
    const { profile, loading } = this.props.profile;

    let dashboardContent;
    if (profile === null || loading) {
      dashboardContent = <Spinner />;
    } else {
      // Check if logged in user has a profile data
      if (Object.keys(profile).length > 0) {
        dashboardContent = (
          <div>
            <p className="lead text-muted">
              Welcome{" "}
              <Link to={`/profile/${profile.tutor.id}`}>{userInfo.userName}</Link>
            </p>
            <hr />
            <ToastProvider>
              <ProfileActions
                profile={profile}
              />
            </ToastProvider>
            <MyProfile profile={profile} />
            <div style={{ marginBottom: "60px" }}>
              <button
                // onClick={this.onDeleteClick.bind(this)}
                className="btn btn-danger"
              >
                Delete My Account
              </button>
            </div>
          </div>
        );
      } else {
        // User is logged in but has no profile
        dashboardContent = (
          <div>
            <p className="lead text-muted">Welcome {userInfo.userName}</p>
            <hr/>
            <p>You have not yet set a profile, please add some info</p>
            <Link to="/create-profile" className="btn btn-lg btn-info">
              Create Profile
            </Link>
          </div>
        );
      }
    }
    return (
      <div className="dashboard" style={{ height: "auto", marginBottom:"400px" }}>
        <div className="container-fluid mt-4">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4">Dashboard</h1>
              {dashboardContent}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Dashboard.protoTypes = {
  getCurrentProfile: PropTypes.func.isRequired,
  //   deleteAccount: PropTypes.func.isRequired,
  auth: PropTypes.object.isRequired,
  profile: PropTypes.object.isRequired,
};
const mapStateProps = (state) => ({
  profile: state.profile,
  auth: state.auth,
});
export default connect(mapStateProps, { getCurrentProfile })(
  Dashboard
);
