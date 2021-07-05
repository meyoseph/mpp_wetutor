import React from "react";
import { Link } from "react-router-dom";

const Landing = () => {
  return (
    // Landing
    <div className="landing">
      <div className="dark-overlay landing-inner text-light">
        <div className="container">
          <div className="row">
            <div className="col-md-12 text-center">
              <h1 className="display-3 mb-4">WeTutor</h1>
              <p className="lead">
                {" "}
                Tutors create a profile/portfolio, share experience and get
                hired while parents get the best tutors.
              </p>
              <hr style={{ backgroundColor: "white", margin: "50px" }} />
              <Link to="/register" className="btn btn-lg btn-info mr-3">
                Sign Up
              </Link>
              <Link to="/login" className="btn btn-lg btn-light">
                Login
              </Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Landing;
