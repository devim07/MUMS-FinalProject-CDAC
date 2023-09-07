import { useState } from "react";
import axios from "axios";
import Form from "react-bootstrap/Form";
import React from "react";         
import {useNavigate } from "react-router";


function EmployeeAddFrom() {
  let config={
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwt")}`,
    },
  }
  const [inputs, setInputs] = useState({});
  const navigate=useNavigate();

  const handleChange = (evnt) => {
    let paramName = evnt.target.name;
    let paramValue = evnt.target.value;

    setInputs((values) => ({ ...values, [paramName]: paramValue }));
  };

  const handleSubmit = (evnt) => {
    evnt.preventDefault();

    axios
      .post("http://localhost:9090/employee/", inputs, config)
      .then((response) => {
        alert("Employee added successfuly");
        navigate("/admin/employee/details/add", {replace:true});
      })
      .catch((error) => {
        alert(error+JSON.stringify(error.response.data));
        console.log(error);
      });
  };

  return (
    <div classNameName="container mt-3 ">
      <h2 className="text-center">Employee Add form</h2><br/>
      <div
        className="container mt-3 "
        style={{ width: "500px", textAlign: "right" }}
      >
        <Form action="" onSubmit={handleSubmit}>
          

          <div className="mb-3 mt-3">
            <label forname="empno" className="form-label float-left">
              Employee Number:
            </label>
            <input
              style={{width:"300px" , padding:"3px 3px"}}
              type="text"
              name="empno"
              value={inputs.empno}
              onChange={handleChange}
            
            />
          </div>

          <div className="mb-3 mt-3">
            <label forname="ename" className="form-label float-left">
              Employee Name:
            </label>
            <input
              style={{width:"300px" , padding:"3px 3px"}}
              type="text"
              name="ename"
              value={inputs.ename}
              onChange={handleChange} 
              required
              maxLength="25"
              minLength="4"             
            />
          </div>

          <div className="mb-3">
            <label forname="basicSal" className="form-label float-left">
              Basic Salary:
            </label>
            <input
              style={{width:"300px" , padding:"3px 3px"}}
              type="number"
              name="basicSal"
              value={inputs.basicSal}
              onChange={handleChange}
              required
              max="50000"
              min="15000"
            />
          </div>
          <div className="mb-3">
            <label forname="deptno" className="form-label float-left">
              Dept no:
            </label>
            <input
              style={{width:"300px" , padding:"3px 3px"}}
              type="text"
              name="deptno"
              value={inputs.deptno}
              onChange={handleChange}
              required
              maxLength="1"
              minLength="1"
            />
          </div>
          <div className="mb-3">
            <label forname="job" className="form-label float-left">
              Job:
            </label>
            <input
              style={{width:"300px" , padding:"3px 3px"}}
              type="text"
              name="job"
              value={inputs.job}
              onChange={handleChange}
              required
              maxLength="1"
              minLength="1"
            />
          </div>

          <div className="mb-3">
            <label forname="city" className="form-label float-left">
              City:
            </label>
            <input
              style={{width:"300px" , padding:"3px 3px"}}
              type="text"
              name="city"
              value={inputs.city}
              onChange={handleChange}
              required
              maxLength="4"
              minLength="4"
            />
          </div>

          <div className="mb-3">
            <label forname="email" className="form-label float-left">
              Email:
            </label>
            <input
              style={{width:"300px" , padding:"3px 3px"}}
              type="email"
              name="email"
              value={inputs.email}
              onChange={handleChange}
              required
            />
          </div>
          <div className="mb-3">
            <label forname="hiredate" className="form-label float-left">
              Hiredate:
            </label>
            <input
              style={{width:"300px", padding:"3px 3px"}}
              type="date"
              name="hiredate"
              value={inputs.hiredate}
              onChange={handleChange}
              required
            />
          </div>
          <button 
            style={{width:"200px"}}
            type="submit" 
            className="btn btn-primary mx-auto">
              Add New Employee
          </button>
          <br/><br/><br/>
        </Form>
      </div>
    </div>
  );
}
export default EmployeeAddFrom;


