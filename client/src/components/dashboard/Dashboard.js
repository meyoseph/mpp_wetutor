import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {Link} from 'react-router-dom';
import { connect } from 'react-redux';

class Dashboard extends Component {

  render() {
    const { userInfo } = this.props.auth;
      let dashboardContent;
      console.log(userInfo);
    // User is logged in but has no profile
    dashboardContent = (
      <div>
        <p className="lead text-muted">Welcome {userInfo.userName}</p>
        <p>You have not yet set a profile, please add some info</p>
        <Link to="/create-profile" className="btn btn-lg btn-info">
          Create Profile
        </Link>
      </div>
    );
    return (
      <div className="dashboard" style={{height:"600px"}}>
        <div className="container">
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
  //   getCurrentProfile: PropTypes.func.isRequired,
  //   deleteAccount: PropTypes.func.isRequired,
  auth: PropTypes.object.isRequired,
  //   profile: PropTypes.object.isRequired,
};
const mapStateProps = (state) => ({
  //   profile: state.profile,
  auth: state.auth,
});
export default connect(mapStateProps, {})(Dashboard);
