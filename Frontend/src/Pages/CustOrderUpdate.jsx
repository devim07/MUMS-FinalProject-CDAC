import { useEffect, useState } from "react";
import axios from "axios";
import {useLocation, useNavigate } from "react-router-dom";
import React from "react";
import Form from "react-bootstrap/Form";
import Dropdown from 'react-bootstrap/Dropdown';
import AfteLoginNav from "../Componant/AfterLoginNav";



function CustOrderUpdate() {
  let config={
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwt")}`,
    },
  }

  let navigate=useNavigate();
  const OrderState = useLocation();
  const [inputs, setInputs] = useState(OrderState.state);
  console.log(OrderState.state);

  const handleChange = (evnt) => {
    let paramName = evnt.target.name;
    let paramValue = evnt.target.value;

    setInputs((values) => ({ ...values, [paramName]: paramValue }));
  };

  // useEffect((event)=>{
  //   event.preventDefault();
  //   event.stopPropagation();
  // },[]);

  const handleSubmit = (evnt) => {
    evnt.preventDefault();

    axios
      .put("http://localhost:9090/order/" + inputs.orderno, inputs,config)
      .then((response) => {
        alert("Order Updated successfully");
        navigate("/customer", {replace:true})
      })
      .catch((error) => {
        alert(JSON.stringify(error.response.data));
        // setIsError(true);
        // setTimeout(() => setIsError(false), 2500);
      });
    // history("/custlist");
  };

  const [responseData, setResponseData] = useState([]);

  const handleSelect=(e)=>{
    console.log(e.target);
    setInputs();
  };

  return (
    <>
     <AfteLoginNav/>
      <div
        className="container mt-3">
        <h2 className="text-center">Order Update Form</h2><br /><br />
        <div
        className="container mt-3 "
        style={{ width: "500px", textAlign: "right" }}
      >
        <Form action="" onSubmit={handleSubmit}>

          <div className="mb-3 mt-3">
            <label forname="orderno" className="form-label float-left">
              Order Number:
            </label>
            <input
              style={{width:"300px" , padding:"3px 3px"}}
              type="text"
              name="orderno"
              value={inputs.orderno}
              onChange={handleChange}
              disabled={true}
            />
          </div>
          <div className="mb-3">
            <label forname="custno" className="form-label float-left">
              Customer No.:
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
            <label forname="orderUnit" className="form-label float-left">
              Order Unit:
            </label>
            <input
              style={{width:"300px" , padding:"3px 3px"}}
              type="text"
              name="orderUnit"
              value={inputs.orderUnit}
              onChange={handleChange}
              disabled={true}
            />
          </div>
          <div className="mb-3">
            <label forname="orderDate" className="form-label float-left">
              Order Date:
            </label>
            <input
              style={{width:"300px" , padding:"3px 3px"}}
              type="text"
              name="orderDate"
              value={inputs.orderDate}
              onChange={handleChange}
              disabled={true}
            />
          </div>
          <div className="mb-3">
            <label forname="batchno" className="form-label float-left">
              Batch Number:
            </label>
            <input
              style={{width:"300px" , padding:"3px 3px"}}
              type="text"
              name="batchno"
              value={inputs.batchno}
              onChange={handleChange}
            />
          </div>
          <div className="mb-3">
            <label forname="status" className="form-label float-left">
              Status:
            </label>
            <Dropdown onSelect={handleSelect} >
                <Dropdown.Toggle variant="info" id="dropdown-basic" style={{width:"300px" , padding:"3px 3px"}} >
                    STATUS
                </Dropdown.Toggle>

                <Dropdown.Menu>
                    <Dropdown.Item eventKey="PROCESSING" value="PROCESSING">PROCESSING</Dropdown.Item>
                    <Dropdown.Item eventKey="IN_PRODUCTION" value="IN_PRODUCTION">IN_PRODUCTION</Dropdown.Item>
                    <Dropdown.Item eventKey="IN_TRANSIT" value="IN_TRANSIT">IN_TRANSIT</Dropdown.Item>
                    <Dropdown.Item eventKey="DELIVERED" value="DELIVERED">DELIVERED</Dropdown.Item>
                </Dropdown.Menu>
            </Dropdown>
            </div>

          <button
            style={{width:"300px"}}
            id={inputs.custno}
            value={inputs.custno}
            type="submit"
            className="btn btn-primary mx-auto"
            state={inputs}
          >
            Update Order
          </button>
        </Form>
        </div>
      </div>
      <br /><br /><br /><br />
      <br />
    </>
  );
}

export default CustOrderUpdate;
