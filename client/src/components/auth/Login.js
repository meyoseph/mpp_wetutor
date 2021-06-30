import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
// import classnames from 'classnames';
import { loginUser, getUserInfo } from "../../actions/authActions";
import TextFieldGroup from "../common/TextFieldGroup";

class Login extends Component {
  constructor() {
    super();
    this.state = {
      email: "",
      password: "",
      errors: {},
    };
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  async componentWillReceiveProps(nextProps) {
    if (nextProps.auth.isAuthenticated) {
      let userType;
      await this.props.getUserInfo();
      if (
        nextProps.auth.userInfo &&
        nextProps.auth.userInfo.roles &&
        nextProps.auth.userInfo.roles[0] !== undefined
      ) {
        userType =
          nextProps.auth.userInfo && nextProps.auth.userInfo.roles[0].roleName;
      }

      if (userType === "tutor") {
        this.props.history.push("/dashboard");
      }
      if (userType === "parent") {
        this.props.history.push("/parent-dashboard");
      }
      if (userType === "admin") {
        this.props.history.push("/admin-dashboard");
      }
    }

    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  onSubmit(e) {
    e.preventDefault();
    const userData = {
      email: this.state.email,
      password: this.state.password,
    };
    this.props.loginUser(userData);
  }
  render() {
    const { errors } = this.state;

    return (
      // Login
      <div
        className="login shadow-lg p-3 mt-5 ml-auto mr-auto bg-white rounded"
        style={{ width: "700px", height: "400px", marginBottom: "160px" }}
      >
        <div className="container">
          <div className="row">
            <div className="col-md-6 m-auto">
              <h1 className="display-4 text-center">Log In</h1>
              <p className="lead text-center">
                Sign in to your WeTutor account
              </p>
              <hr style={{ width: "300px" }} />
              {errors.credentials && (
                <div class="alert alert-danger" role="alert">
                  {errors.credentials}
                </div>
              )}

              <form onSubmit={this.onSubmit}>
                <TextFieldGroup
                  placeholder="Email Address"
                  name="email"
                  value={this.state.email}
                  error={errors.email}
                  type="email"
                  onChange={this.onChange}
                />
                <TextFieldGroup
                  placeholder="Password"
                  name="password"
                  value={this.state.password}
                  error={errors.password}
                  type="password"
                  onChange={this.onChange}
                />
                <input type="submit" className="btn btn-info btn-block mt-4" />
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Login.propTypes = {
  loginUser: PropTypes.func.isRequired,
  getUserInfo: PropTypes.func.isRequired,
  auth: PropTypes.object.isRequired,
  errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  auth: state.auth,
  errors: state.errors,
});

export default connect(mapStateToProps, { loginUser, getUserInfo })(Login);
