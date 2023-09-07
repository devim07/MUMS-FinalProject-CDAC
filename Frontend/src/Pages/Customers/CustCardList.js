import { Avatar } from "@mui/material";
import { useEffect, useState } from "react";
import React from "react";
import { Link } from "react-router-dom";
import { useLocation } from "react-router-dom";
import axios from "axios";

import "../../../src/Style/AdminEmp.css"
import { GrEdit} from "react-icons/gr";

function CustCardList() {
  let config={
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwt")}`,
    },
  }

  const [responseData, setResponseData] = useState([]);
  const CustState = useLocation();

  const CustList = () => {
    axios
      .get("http://localhost:9090/customer", config)
      .then((response) => {
        setResponseData(response.data);
      })
      .catch((error) => {
        alert(error);
      });
  };

  useEffect(() => {
    CustList();
  }, []);

  //=============================================================
  return (
    <div className="row" align="center" mt-5 mb-5>
      <div className="col-12 col-md-12 mb-1 mt-5" align="center">
        <button type="button" className="btn mx-1 icon" style={{border: "none"}}>
          <Link to="/admin/customer/add" className="">
            Add New Customer
          </Link>
        </button>
      </div> 
      
      <div className="row mx-2">
        {responseData.map((item, index) => (
          <div key={index} className="col-12 col-md-4 col-lg-3 md-2">
            <div className="card my-3">
              <div align="center" className="mt-2">
                <Avatar
                  src={`https://picsum.photos/200/300?random=${index}`}
                  sx={{  width: 150, height: 150 }}
                  style={{
                    border: '4px solid rgba(251, 232, 166, 1) '
                 }}
                />
              </div>
              <div className="card-body mb-2" align="center">
                <Link
                  to="/admin/customer/details"
                  id={item.custno}
                  value={item.custno}
                  state={item}
                  // className="btn bg-primary"
                >
                  <h5>{item.custname}</h5>
                </Link>{" "}                 

                <div className="row m-2">
                  <div className="col-12 float-right">
                    <b className="float-left">Cust. No.: </b><span className="float-left">{item.custno}</span>
                      <span>
                        <Link
                          to="/admin/customer/update"
                          state={item}
                          className="btn icon float-right"
                          style={{backgroundColor:"white"}}
                        >
                          <GrEdit style={{color:"rgba(48, 60, 108, 1) ", fontSize: "1.5em"}}/>
                        </Link>
                      </span>
                    </div>
                  </div>
                  <div className="row m-2">
                    <div className="col-12">
                      <b>City:</b><span>{item.city}</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          )
        )}
      </div>
    </div>
  );
}

export default CustCardList;
