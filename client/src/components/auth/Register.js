import React, { Component } from "react";
import PropTypes from "prop-types";
import { withRouter } from "react-router-dom";
// import classnames from "classnames";
import { connect } from "react-redux";
import { registerUser } from "../../actions/authActions";
import TextFieldGroup from "../common/TextFieldGroup";
import SelectListGroup from "../common/SelectListGroup";

class Register extends Component {
  constructor() {
    super();
    this.state = {
      username: "",
      email: "",
      password: "",
      password2: "",
      role: "",
      errors: {},
    };
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }
  componentDidMount() {
    if (this.props.auth.isAuthenticated) {
      this.props.history.push("/dashboard");
    }
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  onSubmit(e) {
    e.preventDefault();
    const newUser = {
      userName: this.state.username,
      email: this.state.email,
      password: this.state.password,
      password2: this.state.password2,
      role: this.state.role,
    };
    this.props.registerUser(newUser, this.props.history);
  }

  render() {
    const { errors } = this.state;

    const options = [
      { label: "* Select who you are ?", value: 0 },
      { label: "Parent", value: "parent" },
      { label: "Tutor", value: "tutor" },
    ];
    return (
      // Register
      <div
        className="register shadow-lg p-3 mb-5 mt-5 ml-auto mr-auto bg-white rounded"
        style={{ width: "700px", height: "650px" }}
      >
        <div className="container">
          <div className="row">
            <div className="col-md-7 m-auto">
              <h1 className="display-4 text-center">Sign Up</h1>
              <p className="lead text-center">Create your WeTutor account</p>
              <hr style={{ width: "400px" }} />
              <form noValidate onSubmit={this.onSubmit}>
                <TextFieldGroup
                  placeholder="Username"
                  name="username"
                  value={this.state.username}
                  error={errors.username}
                  onChange={this.onChange}
                />{" "}
                <TextFieldGroup
                  placeholder="Email Address"
                  name="email"
                  value={this.state.email}
                  error={errors.email}
                  type="email"
                  info="This site uses Gravatar so if you want a profile image, use a Gravatar email"
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
                <TextFieldGroup
                  placeholder="Confirm Password"
                  name="password2"
                  value={this.state.password2}
                  error={errors.password2}
                  type="password"
                  onChange={this.onChange}
                />
                <SelectListGroup
                  placeholder="Who are you ?"
                  name="role"
                  value={this.state.role}
                  onChange={this.onChange}
                  options={options}
                  error={errors.role}
                  info="Are you a parent or a tutor"
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

Register.propTypes = {
  registerUser: PropTypes.func.isRequired,
  auth: PropTypes.object.isRequired,
  errors: PropTypes.object.isRequired,
};
const mapStateToProps = (state) => ({
  auth: state.auth,
  errors: state.errors,
});

export default connect(mapStateToProps, { registerUser })(withRouter(Register));
