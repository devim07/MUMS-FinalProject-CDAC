import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import React from "react";

function EmpDetailList() {
  let config={
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwt")}`,
    },
  }
  
  const [responseData, setResponseData] = useState([]);
  const EmpDetailList = () => {
    axios
      .get("http://localhost:9090/employee/details/", config)
      .then((response) => {
        setResponseData(response.data);
      })
      .catch((error) => {
        alert(error);
      });
  };

  useEffect(() => {
    EmpDetailList();
  }, []);
  
  console.log(responseData);
  return (
    <>
      <div className="container mt-3">
        <h2>EmpDetail Information</h2>

        <table className="table table-striped">
          <thead>
            <tr>
              <th>Employee Number</th>
              <th>Employee Name</th>
              <th>Mobile No.</th>
              <th>Gender</th>
              <th>Aadhar No.</th>
              <th>DOB</th>
              <th>CITY</th>
              <th>pin Code</th>
              <th>Address</th>
              <th>Update</th>
            </tr>
          </thead>
          <tbody>
            {responseData.map((val) => (
              <tr key="{val.empno}">
                <td>{val.empno}</td>
                <td>{val.ename}</td>
                <td>{val.mobileNumber}</td>
                <td>{val.gender}</td>
                <td>{val.aadhar}</td>
                <td>{val.dob}</td>
                <td>{val.city}</td>
                <td>{val.pinCode}</td>
                <td>{val.address}</td>
                <td>
                  <Link
                    to="/employee/details/update"
                    state={val}
                    className="btn btn-primary"
                  >
                    Update
                  </Link>{" "}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
}
export default EmpDetailList;
