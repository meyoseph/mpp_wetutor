import React, { Component } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";
import Spinner from "../common/spinner";
import { getProfileByTutorId } from "../../actions/profileActions";
import ProfileBody from "./ProfileBody";
import ClickableRating from "../common/ClickableRating";
import { useToasts } from "react-toast-notifications";

import axios from "axios";

function withToast(Component) {
  return function WrappedComponent(props) {
    const toastFuncs = useToasts();
    return <Component {...props} {...toastFuncs} />;
  };
}

class Profile extends Component {
  constructor(props) {
    super(props);
    this.state = {
      ratedValue: 0,
      ratingLoading: false,
    };
    this.handleRating = this.handleRating.bind(this);
    this.rateTutor = this.rateTutor.bind(this);
  }
  componentDidMount() {
    if (this.props.match.params.tutorId) {
      this.props.getProfileByTutorId(this.props.match.params.tutorId);
    }
  }

  componentWillReceiveProps(nextProps) {
    // if (nextProps.profile.profile === null && this.props.profile.loading) {
    //   this.props.history.push("/not-found");
    // }
  }

  handleRating(value) {
    this.setState({ ratedValue: value });
  }

  async rateTutor(e) {
    e.preventDefault();

    this.setState({ loading: true });

    let ratingData = {
      parentEmail: this.props.auth.userInfo.email,
      tutorEmail: this.props.profile.profile.tutor.email,
      rating: this.state.ratedValue,
    };

    await axios.post('/api/tutor/rate-tutor', ratingData)
      .then((res) => {
        if (res.data.success) {
          window.$("#exampleModalCenter").modal("hide");
          this.props.addToast(res.data.message, {
            appearance: "success",
            autoDismiss: true,
          });
          this.props.getProfileByTutorId(this.props.match.params.tutorId);
        }
      })
      .catch((err) =>
        this.props.addToast(err.data.message, {
          appearance: "error",
          autoDismiss: true,
        })
      );

    this.setState({ loading: true });
  }

  render() {
    const { profile, loading } = this.props.profile;
    const { userInfo } = this.props.auth;
    const { ratingLoading, ratedValue } = this.state;

    let profileContent;

    let userType = "";

    let homeLink = "/dashboard";
    if (userInfo && userInfo.roles && userInfo.roles[0] !== undefined) {
      userType = userInfo.roles[0].roleName;
      homeLink =
        userInfo.roles[0].roleName === "admin"
          ? "/admin-dashboard"
          : userInfo.roles[0].roleName === "parent"
          ? "/parent-dashboard"
          : "/dashboard";
    }

    if (profile === null || loading) {
      profileContent = <Spinner />;
    } else {
      profileContent = (
        <div>
          <div className="row">
            <div className="col-md-6">
              <Link to={homeLink} className="btn btn-light mb3 float-light">
                Back back
              </Link>
            </div>
            {userType === "parent" && (
              <div className="col-md-6">
                <button
                  className="btn mb3 float-right"
                  type="button"
                  data-toggle="modal"
                  data-target="#exampleModalCenter"
                  style={{ backgroundColor: "orange", color: "white" }}
                >
                  Rate tutor
                </button>
              </div>
            )}
            {/* Modal */}
            <div
              className="modal fade"
              id="exampleModalCenter"
              tabIndex="-1"
              role="dialog"
              aria-labelledby="exampleModalCenterTitle"
              aria-hidden="true"
            >
              <div
                className="modal-dialog modal-dialog-centered"
                role="document"
              >
                <div className="modal-content">
                  <div
                    className="modal-header"
                    style={{ borderBottom: "0 none" }}
                  >
                    <h5 className="modal-title" id="exampleModalLongTitle">
                      Confirmation
                    </h5>
                    <button
                      type="button"
                      className="close"
                      data-dismiss="modal"
                      aria-label="Close"
                    >
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div className="modal-body">
                    How much do you want to give {profile.firstName}{" "}
                    {profile.lastName} ?
                  </div>
                  <div className="row" style={{ marginLeft: "150px" }}>
                    <ClickableRating
                      value={profile.rating}
                      profileRating={this.handleRating}
                      type="flex"
                    />
                    {"   "}
                    <div className="ml-2 mb-2">{ratedValue}</div>
                  </div>
                  <div className="row">
                    <div className="col-md-5"></div>
                    <div className="col-md-2">
                      {ratingLoading && (
                        <div
                          className="spinner-border text-info "
                          role="status"
                        >
                          <span className="sr-only justify-content-center">
                            Loading...
                          </span>
                        </div>
                      )}
                    </div>
                    <div className="col-md-5"></div>
                  </div>
                  <div
                    className="modal-footer mt-3"
                    style={{ borderTop: "0 none" }}
                  >
                    <button
                      type="button"
                      className="btn btn-secondary"
                      data-dismiss="modal"
                    >
                      Close
                    </button>
                    <button type="button" className="btn btn-info" onClick={this.rateTutor}>
                      Rate
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <ProfileBody profile={profile} userInfo={userInfo} />
        </div>
      );
    }
    return (
      <div className="profile mt-4">
        <div className="container">
          <div className="row">
            <div className="col-md-12">{profileContent}</div>
          </div>
        </div>
      </div>
    );
  }
}

Profile.propTypes = {
  getProfileByTutorId: PropTypes.func.isRequired,
  profile: PropTypes.object.isRequired,
  auth: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  profile: state.profile,
  auth: state.auth,
});

export default connect(mapStateToProps, { getProfileByTutorId })(
  withToast(Profile)
);
