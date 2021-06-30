import React, { Component } from "react";
import isEmpty from "../../validation/is-empty";

class MyProfile extends Component {
  render() {
    const { profile } = this.props;

    const skillsList = profile.subjects.split(",");
    const languagesList = profile.languages.split(",");
    // Skills List
    const skills = skillsList.map((skill, index) => (
      <div key={index} className="p-3">
        <i className="fa fa-check" />
        {skill}
      </div>
    ));

    // Languages List
    const languages = languagesList.map((language, index) => (
      <div key={index} className="p-3">
        <i className="fa fa-check" />
        {language}
      </div>
    ));

    return (
      <div>
        <div className="row">
          <div className="col-md-12">
            <div className="card card-body bg-info text-white mb-3">
              <div className="row">
                <div className="col-4 col-md-3 m-auto">
                  <img
                    className="rounded-circle"
                    src={profile.profilePic}
                    alt=""
                  />
                </div>
              </div>
              <div className="text-center">
                <h1 className="display-4 text-center">{profile.firstName}{" "}{profile.lastName}</h1>
                <p className="lead text-center">
                  {profile.status}{" "}
                  {isEmpty(profile.gender) ? null : (
                    <span>{profile.gender}</span>
                  )}
                </p>
                {isEmpty(profile.age) ? null : (
                  <span>I am {profile.age} years old</span>
                )}
                {isEmpty(profile.location) ? null : <p>{profile.location}</p>}
                {isEmpty(profile.phoneNumber) ? null : <p>{profile.phoneNumber}</p>}
                <hr style={{backgroundColor:"white", width:'600px'}}/>
                {isEmpty(profile.phoneNumber) ? null : <p className="lead text-center">{profile.educations}</p>}
                {isEmpty(profile.phoneNumber) ? null : <p className="lead text-center">Major in {profile.majorSubject}</p>}
                {isEmpty(profile.workExperiences) ? null : <p>{profile.workExperiences} of work experience.</p>}
              </div>
            </div>
          </div>
        </div>
        <div className="row">
          <div className="col-md-12">
            <div className="card card-body bg-light mb-3">
              <h3 className="text-center text-info">
                {profile.firstName}'s Bio
              </h3>
              <p className="lead">
                {isEmpty(profile.motive) ? (
                  <span>{profile.firstName}</span>
                ) : (
                  <span>{profile.motive}</span>
                )}
              </p>
              <hr />
              <h3 className="text-center text-info">Skill Set</h3>
              <div className="row">
                <div className="d-flex flex-wrap justify-content-center align-items-center">
                  {skills}
                </div>
              </div>
              <hr />
              <h3 className="text-center text-info">Languages</h3>
              <div className="row">
                <div className="d-flex flex-wrap justify-content-center align-items-center">
                  {languages}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default MyProfile;