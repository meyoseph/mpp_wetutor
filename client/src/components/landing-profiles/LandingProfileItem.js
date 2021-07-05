import React, { Component } from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";
import isEmpty from "../../validation/is-empty";
import Rating from "../common/Rating";

class LandingProfileItem extends Component {
  render() {
    const { profile } = this.props;
    const skillsList = profile.subjects.split(",");
    let profilePic =
      "https://www.gravatar.com/avatar/24fe3615bdba49bdf3e9ffb23f1b7bfd?s=200&r=pg&d=mm";

    if (profile && profile.profilePic) {
      profilePic = profile.profilePic;
    }

    return (
      <div className="card card-body bg-light mb-3">
        <div className="row">
          <div className="col-2">
            <img src={profilePic} alt="" className="rounded-circle" />
          </div>
          <div className="col-lg-6 col-md-4 col-8">
            <h3>{profile.firstName + " " + profile.lastName}</h3>
            <p>Education Level : {profile.educations}</p>
            <p>
              {profile.status}{" "}
              {isEmpty(profile.company) ? null : (
                <span>at {profile.company}</span>
              )}
            </p>
            <p>{profile.workExperiences + " of work experience"}</p>
            {profile.ratedBy > 0 ? (
              <span>
                <Rating value={profile.rating} type="none" />
                <p>
                  <small>Rated by {profile.ratedBy} people</small>
                </p>
              </span>
            ) : (
              <p>
                <small>Not rated yet</small>
              </p>
            )}
            <Link
              className="btn btn-info mt-4"
              to={
                this.props.isAuthenticated
                  ? `/profile/${profile.tutor.id}`
                  : "/login"
              }
            >
              View Profile
            </Link>
          </div>
          <div className="col-md-4 d-none d-md-block">
            <h4>Subject Set</h4>
            <ul className="list-group">
              {skillsList.slice(0, 4).map((subject, index) => (
                <li key={index} className="list-group-item">
                  <i className="fa fa-check pr-1" />
                  {subject}
                </li>
              ))}
            </ul>
          </div>
        </div>
      </div>
    );
  }
}
LandingProfileItem.propTypes = {
  profile: PropTypes.object.isRequired,
};

export default LandingProfileItem;
