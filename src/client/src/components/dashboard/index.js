import React from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";

import TutorDashboard from "../tutor/Dashboard";
import ParentDashboard from "../parent/Dashboard";

const Dashboard = (props) => {
  const { userInfo } = props.auth;
  return (
    <div>
      {userInfo && userInfo.roles && userInfo.roles[0].roleName === "parent" ? (
        <ParentDashboard />
      ) : (
        <TutorDashboard />
      )}
    </div>
  );
};

Dashboard.propTypes = {
  auth: PropTypes.object.isRequired,
  errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  auth: state.auth,
  errors: state.errors,
});

export default connect(mapStateToProps, {})(Dashboard);
