import React from "react";

const TutorList = (props) => {
  return (
    <table class="table table-striped">
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
                  {tutor.profileState === "EMPTY"
                    ? "No profile"
                    : tutor.profileState === "BEGINNING"
                    ? "Waiting for request"
                    : tutor.profileState}
                </td>{" "}
                <button
                  className="btn btn-success mt-3 ml-2"
                  disabled={tutor.profileState === "PENDING" ? false : true}
                  onClick={(e) => {
                    e.preventDefault();
                    alert("Approved");
                  }}
                  style={{width:"90px"}}

                >
                  Approve
                </button>
                <button
                  className="btn btn-danger mt-3 ml-2"
                  disabled={tutor.profileState === "APPROVED" ? false : true}
                  onClick={(e) => {
                    e.preventDefault();
                    alert("Blocked");
                  }}
                  style={{width:"90px"}}
                >
                  Block
                </button>
              </tr>
            );
          })}
      </tbody>
    </table>
  );
};

export default TutorList;
