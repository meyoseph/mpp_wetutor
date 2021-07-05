import React, { useState } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";
import { useToasts } from "react-toast-notifications";
import axios from "axios";
import { getAllTutors } from "../../actions/authActions";

const TutorList = (props) => {
  const { addToast } = useToasts();
  const [loading, setLoading] = useState(false);
  const [userName, setUserName] = useState("");
  const [profileId, setProfileId] = useState("");
  const [actionType, setActionType] = useState("");

  const approveProfile = async (e) => {
    e.preventDefault();
    setLoading(true);

    await axios
      .get(`/api/admin/approve-profile/${profileId}`)
      .then((res) => {
        if (res.data.status) {
          setLoading(false);
          window.$("#exampleModal").modal("hide");
          addToast(res.data.message, {
            appearance: "success",
            autoDismiss: true,
          });
        }
      })
      .catch((err) =>
        addToast(err.data.message, {
          appearance: "error",
          autoDismiss: true,
        })
      );

    props.getAllTutors();
  };

  const blockProfile = async (e) => {
    e.preventDefault();
    setLoading(true);
    
    await axios
      .get(`/api/admin/block-profile/${profileId}`)
      .then((res) => {
        if (res.data.status) {
          setLoading(false);
          window.$("#exampleModal").modal("hide");
          addToast(res.data.message, {
            appearance: "success",
            autoDismiss: true,
          });
        }
      })
      .catch((err) =>
        addToast(err.data.message, {
          appearance: "error",
          autoDismiss: true,
        })
      );

    props.getAllTutors();
  };

  return (
    <div>
      <table className="table table-striped">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">User name</th>
            <th scope="col">Email</th>
            <th scope="col">Profile Status</th>
            <th scope="col">Actions</th>
          </tr>
        </thead>
        <tbody>
          {props.allTutors &&
            props.allTutors.map((tutor, key) => {
              return (
                <tr>
                  <th scope="row">{key + 1}</th>
                  <td>{tutor.user.userName}</td>
                  <td>{tutor.user.email}</td>
                  <td>
                    {tutor.profile == null
                      ? "No profile"
                      : tutor.profile.profileState === "BEGINNING"
                      ? "Waiting for request"
                      : tutor.profile.profileState}
                  </td>{" "}
                  <Link
                    className="btn btn-info mt-3"
                    to={`/profile/${tutor.user.id}`}
                    style={{ width: "120px" }}
                  >
                    View Profile
                  </Link>
                  {(tutor.profile.profileState === "PENDING" || tutor.profile.profileState === "BLOCKED")&& (
                    <button
                      className="btn btn-success mt-3 ml-2"
                      type="button"
                      data-toggle="modal"
                      data-target="#exampleModal"
                      // disabled={tutor.profileState === "PENDING" ? false : true}
                      style={{ width: "90px" }}
                      onClick={(e) => {
                        setActionType("approve");
                        setUserName(tutor.user.userName);
                        setProfileId(tutor.profile.id);
                      }}
                    >
                      Approve
                    </button>
                  )}
                  {(tutor.profile.profileState === "APPROVED" || tutor.profile.profileState === "PENDING") && (
                    <button
                      className="btn btn-danger mt-3 ml-2"
                      type="button"
                      data-toggle="modal"
                      data-target="#exampleModal"
                      // disabled={tutor.profileState === "APPROVED" ? false : true}
                      onClick={(e) => {
                        setActionType("block");
                        setUserName(tutor.user.userName);
                        setProfileId(tutor.profile.id);
                      }}
                      style={{ width: "90px" }}
                    >
                      Block
                    </button>
                  )}
                </tr>
              );
            })}
        </tbody>
      </table>
      {/* Modal Section */}
      <div
        className="modal fade"
        id="exampleModal"
        tabIndex="-1"
        role="dialog"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog" role="document">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="exampleModalLabel">
                Confirmation?
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
              Do you really want to {actionType} {" "} {userName}'s profile?
            </div>
            <div className="row">
              <div className="col-md-5"></div>
              <div className="col-md-2">
                {loading && (
                  <div className="spinner-border text-info " role="status">
                    <span className="sr-only justify-content-center">
                      Loading...
                    </span>
                  </div>
                )}
              </div>
              <div className="col-md-5"></div>
            </div>
            <div className="modal-footer">
              <button
                id="#closemodal"
                type="button"
                className="btn btn-secondary"
                data-dismiss="modal"
              >
                Close
              </button>
              <button
                type="button"
                className="btn btn-info"
                onClick={(e) => actionType ==="approve" ? approveProfile(e) : blockProfile(e)}
              >
                {actionType ==="approve" ? "Approve" : " Block"} {" "}profile
              </button>
            </div>
          </div>
        </div>
      </div>
      {/* End of modal section */}
    </div>
  );
};

TutorList.propTypes = {
  getAllTutors: PropTypes.func.isRequired,
};

export default connect(null, { getAllTutors })(TutorList);
