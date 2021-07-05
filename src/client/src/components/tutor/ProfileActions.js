import React from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import { useToasts } from "react-toast-notifications";
import { getCurrentProfile } from "../../actions/profileActions";
import axios from "axios";

const ProfileActions = (props) => {
  const { addToast } = useToasts();

  const handleRequest = async () => {
    await axios
      .get(`/api/profile/request-approval/${props.profile.id}`)
      .then((res) => {
        if (res.data.status) {
          window.$("#exampleModal").modal("hide");
          addToast(res.data.message, {
            appearance: "success",
            autoDismiss: true,
          });
        }
      })
      .catch((err) =>
        addToast(err.data.message, {
          appearance: "success",
          autoDismiss: true,
        })
      );

    setTimeout(() => {
      props.getCurrentProfile();
    }, 2000);
  };

  return (
    <div>
      {/* Dashboard Actions */}
      <div className="btn-group mb-4">
        <Link to="/edit-profile" className="btn btn-light">
          <i className="fa fa-user-circle text-info mr-2" />
          Edit Profile
        </Link>
        {props.profile.profileState === "BEGINNING" ? (
          <button
            type="button"
            data-toggle="modal"
            data-target="#exampleModal"
            className="btn btn-light"
          >
            <i className="fa fa-check text-info mr-1" />
            Request Approval
          </button>
        ) : props.profile.profileState === "PENDING" ? (
          <button
            type="button"
            className="btn btn-light"
            disabled
          >
            <i className="fa fa-check text-info mr-1" />
            Approval Pending
          </button>
        ) : (
          <button
            type="button"
            className="btn btn-light"
            disabled
          >
            <i className="fa fa-award text-info mr-1" />
            APPROVED
          </button>
        )}
      </div>
      {props.profile.profileState === "BEGINNING" && <p className="lead text-muted">You need to send approval request and get approved for your profile to be seen by parents.</p>}
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
              Do you really want to request for profile approval?
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
                onClick={handleRequest}
              >
                Request approval
              </button>
            </div>
          </div>
        </div>
      </div>
      {/* End of modal section */}
    </div>
  );
};

ProfileActions.protoTypes = {
  getCurrentProfile: PropTypes.func.isRequired,
};
export default connect(null, { getCurrentProfile })(ProfileActions);
