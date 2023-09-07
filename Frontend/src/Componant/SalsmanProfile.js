import { useEffect, useState } from "react";
import React from "react";
import { Link, NavLink } from "react-router-dom";
import { useLocation } from "react-router-dom";
import axios from "axios";
import EmpCard from "./EmpCard";
import { Avatar } from "@mui/material";

function SalsmanProfile() {
  var config = {
    headers: { "Access-Control-Allow-Origin": "*" },
  };
  //const EmployeeState = useLocation();
  const [responseData, setResponseData] = useState([]);
  //const [inputs, setInputs] = useState(EmployeeState.state);
  let state = {
    empno: "F001",
    ename: "ARYANAK",
    basicSal: 17000.0,
    deptno: "P",
    job: "M",
    city: "BOMB",
    email: "ARYANAK@GMAIL.COM",
    hiredate: "2020-02-15",
    photo: null,
  };
  //console.log(id);
  //console.log(EmployeeState.state.empno);

  let config={
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwt")}`,
    },
  }

  const EmpDetailsCard = () => {
    axios
      .get("http://localhost:9090/employee/details/" + state.empno, config)
      .then((response) => {
        setResponseData(response.data);
      })
      .catch((error) => {
        alert(error);
      });
  };
  console.log(responseData);
  useEffect(() => {
    EmpDetailsCard();
  }, []);

  return (
    <>
      <div className="container mt-3"></div>

      <div className="row">
        <div className="col-12 col-md-10 md-2 mx-auto ">
          <div className="card">
            <h2 className="text-center">Salsman Profile Page</h2>
            <div>
              <div align="center" className="mt-2">
                <Avatar
                  src={require("./manoj.jpg")}
                  sx={{ width: 300, height: 300 }}
                />
                <h3>{state.ename}</h3>
              </div>
            </div>
            <div className="row">
              <div className="col-12 col-md-6 md-2">
                <div className="card-body"></div>
                <table className="table mt-2">
                  <thead>
                    <tr>
                      <th>Employee Number</th>
                      <td>{state.empno}</td>
                    </tr>
                    {/* <tr>
                      <th>Employee Name</th>
                      <td>{EmployeeState.state.ename}</td>
                    </tr> */}
                    <tr>
                      <th>EMAIL</th>
                      <td>{state.email}</td>
                    </tr>
                    <tr>
                      <th>HIREDATE</th>
                      <td>{state.hiredate}</td>
                    </tr>
                    <tr>
                      <th>DEPT</th>
                      <td>{state.deptno}</td>
                    </tr>
                    <tr>
                      <th>JOB</th>
                      <td>{state.job}</td>
                    </tr>
                    <tr>
                      <th>CITY</th>
                      <td>{state.city}</td>
                    </tr>
                  </thead>
                </table>
              </div>
              <div className="col-12 col-md-6 md-2">
                <div className="card-body"></div>
                <table className="table table-striped">
                  <thead>
                    <tr>
                      <th>Employee Number</th>
                      <td>{responseData.empno}</td>
                    </tr>
                    {/* <tr>
                   <th>Employee Name</th>
                   <td>{EmployeeState.state.ename}</td>
                </tr> */}

                    <tr>
                      <th>Mobile No.</th>
                      <td>{responseData.mobileNumber}</td>
                    </tr>

                    <tr>
                      <th>Gender</th>
                      <td>{responseData.gender}</td>
                    </tr>

                    <tr>
                      <th>Aadhar No.</th>
                      <td>{responseData.aadhar}</td>
                    </tr>

                    <tr>
                      <th>DOB</th>
                      <td>{responseData.dob}</td>
                    </tr>
                    <tr>
                      <th>CITY</th>
                      <td>{responseData.city}</td>
                    </tr>

                    <tr>
                      <th>pin Code</th>
                      <td>{responseData.pinCode}</td>
                    </tr>

                    <tr>
                      <th>Address</th>
                      <td>{responseData.address}</td>
                    </tr>

                    <tr>
                      <td>
                        <Link
                          to="/employee/details/update"
                          state={responseData}
                          className="btn bg-primary"
                        >
                          Update
                        </Link>{" "}
                      </td>
                    </tr>
                  </thead>

                  {/* {responseData.map((val) => ( */}
                  <tr key="{val.empno}"></tr>
                  {/* ))} */}
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="row" align="center" mt-5 mb-5>
        <div className="col-12 col-md-6 mb-4 mt-2  text-Align-center">
          <input type="text" placeholder="Enter Month" name="month" />
          <input type="text" placeholder=" Enter year" name="year" />
          <button type="button" className="btn bg-primary mx-1">
            {" "}
            view Current Month Salary{" "}
          </button>
          {""}
        </div>
        <div className="col-12 col-md-6 mb-4 mt-2  text-Align-center">
        <Link to="/customer/add" className="btn bg-primary">
          Add Customer
        </Link>{" "}
        </div>
        <div className="col-12 col-md-6 mb-4 mt-2  text-Align-center">
          from
          <input type="date" placeholder="from" name="month" />
          to
          <input type="date" placeholder="to" name="year" />
          <button type="button" className="btn bg-primary mx-1">
            {" "}
            Apply for leave{" "}
          </button>
          {""}
        </div>
      </div>
    </>
  );
}

export default SalsmanProfile;

// empno: 'F002', mobileNumber: 9869989658, gender: 'M', aadhar: 113456788765, dob: '1975-01-30', …}
// aadhar
// :
// 113456788765
// address
// :
// "12/SAI SADAN, KISSAN NAGAR, BHATWADI, THANE"
// city
// :
// "BOMB"
// dob
// :
// "1975-01-30"
// empno
// :
// "F002"
// gender
// :
// "M"
// mobileNumber
// :
// 9869989658
// pinCode
// :
// 400612
