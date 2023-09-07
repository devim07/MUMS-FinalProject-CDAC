import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import React from "react";
function EmployeeList() {
  let config={
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwt")}`,
    },
  }
  const [responseData, setResponseData] = useState([]);

  const EmployeeList = () => {
    axios
      .get("http://localhost:9090/employee", config)
      .then((response) => {
        setResponseData(response.data);
      })
      .catch((error) => {
        alert(error);
      });
  };

  useEffect(() => {
    EmployeeList();
  }, []);

  const EmployeeDelete = (evnt) => {
    axios
      .delete("http://localhost:9090/employee/" + evnt.target.value, config)
      .then((response) => {
        EmployeeList();
      })
      .catch((error) => {
        alert(error);
      });
  };

  // "empno": "F001",
  //       "basicSal": 34000.0,
  //       "deptno": "F",
  //       "job": "M",
  //       "city": "BOMB",
  //       "email": "ABHISHEK@GMAIL.COM",
  //       "hiredate": "1988-09-21"

  return (
    <>
      <div className="container mt-1">
        <h2>Employee Information</h2>

        <table className="table table-striped">
          <thead>
            <tr>
              <th>EmployeeNumber</th>
              <th>Basic</th>
              <th>DEPT</th>
              <th>JOB</th>
              <th>CITY</th>
              <th>EMAIL</th>
              <th>HIREDATE</th>

              <th>Update</th>
              <th>Details</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            {responseData.map((val) => (
              <tr key="{val.empno}">
                <td>{val.empno}</td>
                <td>{val.basicSal}</td>
                <td>{val.deptno}</td>
                <td>{val.job}</td>
                <td>{val.city}</td>
                <td>{val.email}</td>
                <td>{val.hiredate}</td>
                <td>
                  <Link
                    to="/employee/update"
                    state={val}
                    className="btn btn-primary"
                  >
                    Update
                  </Link>
                </td>
                <td>
                  <Link
                    to="/employee/details/list"
                    id={val.empno}
                    value={val.empno}
                    state={val.empno}
                    className="btn btn-primary"
                  >
                    DETAILS
                  </Link>{" "}
                </td>
                <td>
                  <button
                    type="button"
                    id={val.empno}
                    value={val.empno}
                    className="btn btn-danger"
                    onClick={EmployeeDelete}
                  >
                    X
                  </button>{" "}
                </td>
                <td>
                  {/* <Link
                    to="/Employee/upload"
                    state={val.id}
                    className="btn btn-primary"
                  >
                     <img
                      src={"http://localhost:9092/images/" + val.image}
                      width="70"
                      height="70"
                      alt="no image"
                    ></img> 
                  </Link>  */}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
}
export default EmployeeList;
