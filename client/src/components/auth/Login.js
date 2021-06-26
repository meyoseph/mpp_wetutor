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

  componentWillReceiveProps(nextProps) {
    if (nextProps.auth.isAuthenticated) {
      this.props.getUserInfo(nextProps.auth.user.sub);
      this.props.history.push("/dashboard");
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
    console.log("User credential", userData);
  }
  render() {
    const { errors } = this.state;
    
  console.log(errors);
    return (
      // Login
      <div
        className="login shadow-lg p-3 mb-5 mt-5 ml-auto mr-auto bg-white rounded"
        style={{ width: "700px", height: "400px" }}
      >
        <div className="container">
          <div className="row">
            <div className="col-md-6 m-auto">
              <h1 className="display-4 text-center">Log In</h1>
              <p className="lead text-center">
                Sign in to your WeTutor account
              </p>
              <hr style={{ width: "300px" }} />
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
  errors: PropTypes.object.isRequired
}

const mapStateToProps = ( state ) =>({
  auth: state.auth,
  errors: state.errors
}) 

export default connect(mapStateToProps, { loginUser, getUserInfo })(Login);
