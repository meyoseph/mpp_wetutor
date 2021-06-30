import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Link } from "react-router-dom";
import isEmpty from '../../validation/is-empty';

class ProfileItem extends Component {
    render() {
        const { profile } = this.props;
        const skillsList = profile.subjects.split(",");
        console.log("Is authenticated",this.props.isAuthenticated);
        return (
            <div className="card card-body bg-light mb-3">
                <div className="row">
                    <div className="col-2">
                        <img src="https://www.gravatar.com/avatar/24fe3615bdba49bdf3e9ffb23f1b7bfd?s=200&r=pg&d=mm" alt="" className="rounded-circle"/>
                    </div>
                    <div className="col-lg-6 col-md-4 col-8">
                        <h3>{profile.firstName + " "+ profile.lastName}</h3>
                        <p>
                            Education Level : {profile.educations}
                        </p>
                        <p>
                            {profile.status} {isEmpty(profile.company) ? null : (<span>at {profile.company}</span>)}
                        </p>
                        <p>
                            {profile.workExperiences+" of work experience"}
                        </p>
                        <Link className="btn btn-info mt-4" to={this.props.isAuthenticated ? `/profile/${profile.tutorId}` :"/login"}>
                            View Profile
                        </Link>
                    </div>
                    <div className="col-md-4 d-none d-md-block">
                        <h4>Subject Set</h4>
                        <ul className="list-group">
                            {skillsList.slice(0,4).map((subject,index) => (
                                <li key={index} className="list-group-item">
                                    <i className="fa fa-check pr-1" />
                                    {subject}
                                </li>
                            ))}
                        </ul>
                    </div>
                </div>
            </div>
        )
    }
}
ProfileItem.propTypes ={
    profile: PropTypes.object.isRequired
}

export default ProfileItem