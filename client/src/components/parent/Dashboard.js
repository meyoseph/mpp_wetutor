import React from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import Profiles from "../profiles/Profiles";
import { Link } from "react-router-dom";

const Dashboard = (props) => {
  const { userInfo } = props.auth;
  let dashboardContent;

  dashboardContent =
    userInfo && !userInfo.roles[0].active ? (
      <div className>
        <p className="lead text-muted">Welcome {userInfo.userName}</p>
        <hr />
        <p>You have not yet subscribed, please subscribe</p>
        <Link to="/subscribe" className="btn btn-lg btn-info">
          Go to subscription page
        </Link>
      </div>
    ) : (
      <div>
        <Profiles />
      </div>
    );
  return (
    <div
      className="dashboard"
      style={{ height: "auto", marginBottom: "400px" }}
    >
      <div className="container-fluid mt-4">
        <div className="row">
          <div className="col-md-12">{dashboardContent}</div>
        </div>
      </div>
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
