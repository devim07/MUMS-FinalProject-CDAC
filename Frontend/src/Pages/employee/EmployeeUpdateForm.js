import { useState } from "react";
import axios from "axios";
import {useLocation, useNavigate } from "react-router-dom";
import React from "react";
import Form from "react-bootstrap/Form";
import AfteLoginNav from "../../Componant/AfterLoginNav"



function EmployeeUpdateForm() {
  let config={
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwt")}`,
    },
  }
  const EmployeeState = useLocation();

  const [inputs, setInputs] = useState(EmployeeState.state);
  let navigate=useNavigate();

  const handleChange = (evnt) => {
    let paramName = evnt.target.name;
    let paramValue = evnt.target.value;

    setInputs((values) => ({ ...values, [paramName]: paramValue }));
  };

  const handleSubmit = (evnt) => {
    evnt.preventDefault();
    // alert(JSON.stringify(inputs));

    axios
      .put("http://localhost:9090/employee/" + inputs.empno, inputs, config)
      .then((response) => {

      navigate=("/admin/employee",{replace:true});
          })
      .catch((error) => {
        navigate("*", {replace:true});
      });
  };

  return (
    <>
    <AfteLoginNav/> 
    <div classNameName="container ">
      <h2 className="text-center">Employee Update Form</h2><br/>
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
              disabled
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
              Update Employee
          </button>
          <br/><br/><br/>
        </Form>
      </div>
    </div>
    </>
  );
}
export default EmployeeUpdateForm;