import { useState } from "react";
import axios from "axios";
import Form from "react-bootstrap/Form";
import React from "react";
import {useNavigate} from "react-router"

function CustAddForm() {
  let config={
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwt")}`,
    },
  }

  const [inputs, setInputs] = useState({});
  let navigate=useNavigate();

  const handleChange = (evnt) => {
    let paramName = evnt.target.name;
    let paramValue = evnt.target.value;

    setInputs((values) => ({ ...values, [paramName]: paramValue }));
  };

  const handleSubmit = (evnt) => {
    evnt.preventDefault();

    axios
      .post("http://localhost:9090/customer/", inputs, config)
      .then((response) => {
        navigate("/admin/customer",{replace:true});
      })
      .catch((error) => {
        navigate("/*",{replace:true});
      });
  };
  return (
    <div className="align-items-center">
      <form action="" onSubmit={handleSubmit}>
        <br/>
        <h2 className="text-center">Customer Add Form</h2><br/>
          <div class="container" style={{width:"500px"}}>
            <div className="mb-3" style={{ marginBottom: 20 }}>
              <label className="form-label">Customer Name:</label>
              <input
                type="text"
                name="custname"
                value={inputs.custname}
                onChange={handleChange}
                className="form-control"
                required
                maxLength="25"
                minLength="4"
              />
            </div>

            <div className="mb-3" style={{ marginBottom: 20 }}>
              <label className="form-label">Customer City:</label>
              <input
                type="text"
                name="city"
                value={inputs.city}
                onChange={handleChange}
                className="form-control"
                required
                maxLength="4"
                minLength="4"
              />
            </div>

            <div className="mb-3" style={{ marginBottom: 20 }}>
              <label className="form-label">Mobile Number:</label>
              <input
                type="text"
                name="mobileNumber"
                value={inputs.mobileNumber}
                onChange={handleChange}
                className="form-control"
                required
                maxLength="10"
                minLength="10"
              />
            </div>

            <div className="mb-3" style={{ marginBottom: 20 }}>
              <label className="form-label">Rating:</label>
              <input
                type="number"
                name="rating"
                value={inputs.ratingr}
                onChange={handleChange}
                className="form-control"
                required
                max="10"
                min="1"
              />
            </div>
            <br/>

            <button
                id={inputs.empno}
                value={inputs.empno}
                type="submit"
                className="btn btn-primary float-right"
            >
              Add Customer
            </button>  
            </div>
            <br/><br/>     
        </form>
    </div>
  );
}
export default CustAddForm;
