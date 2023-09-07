import { Link, Outlet, useNavigate } from "react-router-dom";
import React from "react";
import "../Style/header.css"
import { RiLogoutCircleRFill } from "react-icons/ri";



function AfteLoginNav() {

  let navigate=useNavigate();

  const handleLogout=()=>{
    localStorage.clear();
    navigate("/home", {replace:true});
  };

  return (
    <>
     <div className="row sticky-wrapper">
        <div className="col-12 sticky">
          <div className="row" >
            <div className="col-2">
              <Link to="/home">
                <img
                    src={require("../Assets/images/home/logo_no_name.png")}
                    width="72"
                    className="d-inline-block align-top mx-5"
                    alt="MUMS logo"
                />
              </Link>
            </div>
            <div className="col-9 text-center my-auto">
              <h3 className="my-auto mx-auto">Welcome {localStorage.getItem("name")}  </h3>        
            </div>
            <div  className="col-1 my-auto" >
              <RiLogoutCircleRFill  
                style={{color:"rgba(48, 60, 108, 1) ", fontSize: "3em"}} 
                onClick={handleLogout}
                title="Log Out"
              />
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
export default AfteLoginNav;


