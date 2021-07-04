import React, { Component } from "react";
import { connect } from "react-redux";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import TextFieldGroup from "../common/TextFieldGroup";
import { getUserInfo } from "../../actions/authActions";
import { useToasts } from "react-toast-notifications";
import { GET_ERRORS } from "../../actions/types";
import axios from "axios";
import store from "../../store";

function withToast(Component) {
  return function WrappedComponent(props) {
    const toastFuncs = useToasts();
    return <Component {...props} {...toastFuncs} />;
  };
}

class SubscriptionForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cardNumber: "",
      expirationMonth: 0,
      expirationYear: 0,
      cvc: "",
      loadging: false,
      errors: {},
    };

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  async onSubmit(e) {
    e.preventDefault();

    this.setState({ loading: true });

    const subscriptionData = {
      cardNumber: this.state.cardNumber,
      expirationMonth: Number(this.state.expirationMonth),
      expirationYear: Number(this.state.expirationYear),
      cvc: this.state.cvc,
      userEmail: this.props.auth.user && this.props.auth.user.sub,
    };

    await axios
      .post("api/checkout", subscriptionData)
      .then((res) => {
        if (res.data.success) {
          this.props.addToast(res.data.message, {
            appearance: "success",
            autoDismiss: true,
          });
          this.setState({
            cardNumber: "",
            expirationMonth: 0,
            expirationYear: 0,
            cvc: "",
          });
          this.props.getUserInfo();
          this.props.history.push("/parent-dashboard");
        }
      })
      .catch((err) =>
        store.dispatch({
          type: GET_ERRORS,
          payload: err.response.data,
        })
      );

      this.setState({ loading: false });
  }

  render() {
    const { errors } = this.state;

    return (
      <div
        className="create-profile shadow-lg mb-5 mt-5 bg-white rounded"
        style={{ width: "1000px", height: "630px" }}
      >
        <div className="container">
          <div className="row">
            <div className="col-md-12 mt-5">
              <Link to="/parent-dashboard" className="btn btn-light">
                Go back
              </Link>
            </div>
            <div className="col-md-12 m-auto">
              <h1 className="display-4 text-center">Subscribe</h1>
              <p className="lead text-center">
                Start your subscription and browse all the best tutors
              </p>
              <hr style={{ width: "600px" }} />
              <small className="d-block pb-3">* = required fields</small>
            </div>
          </div>
          <div className="col-md-12 m-auto">
            <form onSubmit={this.onSubmit}>
              <div className="row">
                <div className="col-md-12">
                  <TextFieldGroup
                    placeholder="* Card number"
                    name="cardNumber"
                    value={this.state.cardNumber}
                    onChange={this.onChange}
                    error={errors.cardNumber}
                    info="Number on your card"
                  />
                </div>
                <div className="col-md-12">
                  <div className="row">
                    <div className="col-md-6">
                      <TextFieldGroup
                        placeholder="* Expiration Month"
                        name="expirationMonth"
                        type="number"
                        value={this.state.expirationMonth}
                        onChange={this.onChange}
                        error={errors.expirationMonth}
                        info="Your card's expiration month"
                      />
                    </div>
                    <div className="col-md-6">
                      <TextFieldGroup
                        placeholder="* Expiration Year"
                        name="expirationYear"
                        type="number"
                        value={this.state.expirationYear}
                        onChange={this.onChange}
                        error={errors.expirationYear}
                        info="Your card's expiration year"
                      />
                    </div>
                  </div>
                </div>
                <div className="col-md-12">
                  <TextFieldGroup
                    placeholder="CVC"
                    name="cvc"
                    value={this.state.cvc}
                    onChange={this.onChange}
                    type="number"
                    error={errors.cvc}
                    info="cvc"
                  />
                </div>
              </div>
              <div className="row">
                <div className="col-md-5"></div>
                <div className="col-md-2">
                  {this.state.loading && (
                    <div className="spinner-border text-info " role="status">
                      <span className="sr-only justify-content-center">
                        Loading...
                      </span>
                    </div>
                  )}
                </div>
                <div className="col-md-5"></div>
              </div>
              <input
                type="submit"
                value="Submit"
                className="btn btn-info btn-block mt-4 mb-5"
              />
            </form>
          </div>
        </div>
      </div>
    );
  }
}

SubscriptionForm.propTypes = {
  auth: PropTypes.object.isRequired,
  errors: PropTypes.object.isRequired,
  getUserInfo: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
  auth: state.auth,
  errors: state.errors,
});
export default connect(mapStateToProps, { getUserInfo })(
  withToast(SubscriptionForm)
);
