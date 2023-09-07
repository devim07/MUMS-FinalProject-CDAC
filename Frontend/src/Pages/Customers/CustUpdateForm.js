import { useState } from "react";
import axios from "axios";
import { Link,useLocation, useNavigate } from "react-router-dom";
import React from "react";
import Form from "react-bootstrap/Form";



function CustUpdateForm() {
  let config={
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwt")}`,
    },
  }

  let navigate=useNavigate();
  const CustomerState = useLocation();
  const [inputs, setInputs] = useState(CustomerState.state);

  const handleChange = (evnt) => {
    let paramName = evnt.target.name;
    let paramValue = evnt.target.value;

    setInputs((values) => ({ ...values, [paramName]: paramValue }));
  };

  const handleSubmit = (evnt) => {
    evnt.preventDefault();

    axios
      .put("http://localhost:9090/customer/" + inputs.custno, inputs, config)
      .then((response) => {
        navigate("/admin/customer", {replace:true});
      })
      .catch((error) => {
        navigate("*", {replace:true})
      }); 
  };

  return (
    <>
      <div
        className="container mt-3">
        <h2 className="text-center">Customer Update Form</h2>
        <div
        className="container mt-3 "
        style={{ width: "500px", textAlign: "right" }}
      >
        <Form action="" onSubmit={handleSubmit}>

          <div className="mb-3 mt-3">
            <label forname="custno" className="form-label float-left">
              Customer Number:
            </label>
            <input
              style={{width:"300px" , padding:"3px 3px"}}
              type="text"
              name="custno"
              value={inputs.custno}
              onChange={handleChange}
              disabled={true}
            />
          </div>
          <div className="mb-3">
            <label forname="salesno" className="form-label float-left">
              Salesman Number:
            </label>
            <input
              style={{width:"300px" , padding:"3px 3px"}}
              type="text"
              name="salesno"
              value={inputs.salesno}
              onChange={handleChange}
              disabled={true}
            />
          </div>
          <div className="mb-3">
            <label forname="custname" className="form-label float-left">
              Customer Name:
            </label>
            <input
              style={{width:"300px" , padding:"3px 3px"}}
              type="text"
              name="custname"
              value={inputs.custname}
              onChange={handleChange}
              disabled={true}
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
              disabled={true}
            />
          </div>
          <div className="mb-3">
            <label forname="mobileNumber" className="form-label float-left">
              Mobile Number:
            </label>
            <input
              style={{width:"300px" , padding:"3px 3px"}}
              type="text"
              name="mobileNumber"
              value={inputs.mobileNumber}
              onChange={handleChange}
              required
                maxLength="10"
                minLength="10"
            />
          </div>
          <div className="mb-3">
            <label forname="rating" className="form-label float-left">
              Rating:
            </label>
            <input
              style={{width:"300px" , padding:"3px 3px"}}
              type="text"
              name="rating"
              value={inputs.rating}
              onChange={handleChange}
              required
                max="10"
                min="1"
            />
          </div>
          <button
            style={{width:"300px"}}
            id={inputs.custno}
            value={inputs.custno}
            type="submit"
            className="btn btn-primary mx-auto"
          >
            {/* <Link to="/admin/customer/" className=""> */}
              Update Customer Information
            {/* </Link> */}
          </button>

          {/* {isSuccess && (
            <div className="alert alert-success">Updated Successfully</div>
          )}
          {isError && <div className="alert alert-danger">Not Updated</div>} */}
        </Form>
        </div>
      </div>
      <br />
      <br />
    </>
  );
}
export default CustUpdateForm;
