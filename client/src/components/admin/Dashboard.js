import React, { Component } from "react";
import { connect } from "react-redux";
import PropTypes from "prop-types";

import { getAllTutors, getAllParents } from "../../actions/authActions";
import TutorList from "./TutorList";
import ParentList from "./ParentList";

class Dashboard extends Component {
  constructor() {
    super();
    this.state = {
      activeLink: "tutors",
    };
  }
  componentDidMount() {
    this.props.getAllTutors();
    this.props.getAllParents();
  }

  changeActiveLink(linkType) {
    this.setState({ activeLink: linkType });
  }
  render() {
    const { allParents, allTutors } = this.props.auth;

    const { activeLink } = this.state;
    let tableContent =
      activeLink === "tutors" ? (
        <TutorList allTutors={allTutors} />
      ) : (
        <ParentList allParents={allParents} />
      );
    return (
      <div className="dashboard mt-5" style={{ height: "auto", marginBottom:"400px" }}>
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 mb-3">Dashboard</h1>
              <hr/>
              <div className="btn-group mb-4">
                <button
                  onClick={() => this.changeActiveLink("tutors")}
                  className="btn btn-light"
                >
                  <i className="fa fa-user-circle text-info mr-2" />
                  Tutors
                </button>
                <button
                  onClick={() => this.changeActiveLink("parents")}
                  className="btn btn-light"
                >
                  <i className="fa fa-user-circle text-info mr-2" />
                  Parents
                </button>
              </div>
              {tableContent}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Dashboard.propTypes = {
  auth: PropTypes.object.isRequired,
  getAllTutors: PropTypes.func.isRequired,
  getAllParents: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  auth: state.auth,
  errors: state.errors,
});
export default connect(mapStateToProps, { getAllTutors, getAllParents })(
  Dashboard
);
