import React from "react";
import { Route, Redirect } from "react-router-dom";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import NotFound from "../not-found/NotFound";

const TutorProtected = ({ component: Component, auth, ...rest }) => (
  <Route
    {...rest}
    render={(props) =>
      auth.isAuthenticated === true ? (
        auth.userInfo.roles && auth.userInfo.roles[0].roleName === "tutor" ? (
          <Component {...props} />
        ) : (
          <NotFound />
        )
      ) : (
        <Redirect to="/login" />
      )
    }
  />
);
TutorProtected.propTypes = {
  auth: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  auth: state.auth,
});

export default connect(mapStateToProps)(TutorProtected);
