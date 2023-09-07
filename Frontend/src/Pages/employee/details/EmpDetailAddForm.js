import { useState } from "react";
import axios from "axios";
import React from "react";
import {useLocation, useNavigate } from "react-router";


function EmpDetailAddFrom() {
  let config={
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwt")}`,
    },
  }
  let navigate=useNavigate();
  const loc=useLocation();
  const [inputs, setInputs] = useState({});
  console.log("inputs");
  console.log(loc.search);
  console.log(inputs);

  const handleChange = (evnt) => {
    let paramName = evnt.target.name;
    let paramValue = evnt.target.value;

    setInputs((values) => ({ ...values, [paramName]: paramValue }));
  };

  const handleSubmit = (evnt) => {
    evnt.preventDefault();
    // alert(JSON.stringify(inputs));

    axios
      .post("http://localhost:9090/employee/details/"+inputs.empno, inputs, config)
      .then((response) => {
        //alert(response.data);
        navigate("/admin/employee", {replace:true});
      })
      .catch((error) => {
        alert(error);
        navigate("*", {replace:true});
      });
  };

  return (
    <>
      <div className="align-items-center">
      <form action="" onSubmit={handleSubmit}>
        <h2 className="text-center">Employee Details Update Form</h2>
          <div class="container" style={{width:"500px"}}>
            <div className="mb-3" style={{ marginBottom: 20 }}>
              <label className="form-label">Employee Number:</label>
              <input
                type="text"
                name="empno"
                value={inputs.empno}
                onChange={handleChange}
                className="form-control"
                required
                maxLength="4"
                minLength="4"
              />
            </div>

            <div className="mb-3">
              <label className="form-label">Mobile Number:</label>
              <input
                type="tel"
                name="mobileNumber"
                value={inputs.mobileNumber}
                onChange={handleChange}
                className="form-control"
                required
              ></input>
            </div>

            <div className="mb-3">
              <label className="form-label">Gender:</label>
              <input
                type="text"
                name="gender"
                value={inputs.gender}
                onChange={handleChange}
                className="form-control"
                required 
                maxLength="1"
                minLength="1"
              ></input>
            </div>

            <div className="mb-3">
              <label className="form-label">City:</label>
              <input
                type="text"
                name="city"
                value={inputs.city}
                onChange={handleChange}
                className="form-control"
                required 
                maxLength="4"
                minLength="4"
              ></input>
            </div>

            <div className="mb-3">
              <label className="form-label">Aadhar Card Number:</label>
              <input
                type="text"
                name="aadhar"
                value={inputs.aadhar}
                onChange={handleChange}
                className="form-control"
                required 
                maxLength="12"
                minLength="12"
              ></input>
            </div>

            <div className="mb-3">
              <label className="form-label">Date of Birth:</label>
              <input
                type="date"
                name="dob"
                value={inputs.dob}
                onChange={handleChange}
                className="form-control"
                required
              ></input>
            </div>

            <div className="mb-3">
              <label className="form-label">Pin Code:</label>
              <input
                type="number"
                name="pinCode"
                value={inputs.pinCode}
                onChange={handleChange}
                className="form-control"
                required 
                maxLength="999999"
                minLength="100000"
              ></input>
            </div>

            <div className="mb-3">
              <label className="form-label">Address:</label>
              <input
                type="text"
                name="address"
                value={inputs.address}
                onChange={handleChange}
                className="form-control"
                required 
                minLength="10"
              ></input>
            </div>

            <button
                id={inputs.empno}
                value={inputs.empno}
                type="submit"
                className="btn btn-primary float-right"
            >
              Update Employee Details
            </button>  
            <br/><br/>          
          </div>
        </form>
    </div>
    </>
  );
}
export default EmpDetailAddFrom;
