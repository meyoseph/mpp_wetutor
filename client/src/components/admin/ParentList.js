import React from "react";

const ParentList = (props) => {
  return (
    <table className="table table-striped">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">User name</th>
          <th scope="col">Email</th>
          <th scope="col">Status</th>
        </tr>
      </thead>
      <tbody>
        {props.allParents &&
          props.allParents.map((parent, key) => {
            return (
              <tr>
                <th scope="row">{key + 1}</th>
                <td>{parent.userName}</td>
                <td>{parent.email}</td>
                <td>{parent.roles[0].active ? "Active" : "Inactive"}</td>{" "}
              </tr>
            );
          })}
      </tbody>
    </table>
  );
};

export default ParentList;
