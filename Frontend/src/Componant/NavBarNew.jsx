import React, { Fragment } from "react";
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import { Link } from "react-router-dom";
import { Navbar } from "reactstrap";
import { BiAlignRight } from "react-icons/bi";
import '../App.css'


function NavBarNew() {
  return (
    <>
      <div className="header">
        <nav className="navbar-expand-lg" style={{backgroundColor:"rgba(180, 223, 229, 0.5)", width:"100%"}}>
          <div className="container-fluid" >
            <div className="row">
              <div className="col-2 mx-auto">
                <img
                  src={require("../Assets/images/home/logo.png")}
                  width="100"
                  path="/home"
                  className="d-inline-block align-top mx-5"
                  alt="MUMS logo"
                />
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                  <span className="navbar-toggler-icon"> 
                    <BiAlignRight size={40}/>
                  </span>
                </button>
              </div>
            <div className="col-10 mx-auto my-auto">
              <div className="collapse navbar-collapse " id="navbarNav">
                <ul className="navbar-nav" style={{fontSize:"30px"}}>
                  <li className="nav-item mx-3">
                    <Nav.Link className="nav-item" as={Link} to="/"><h3>Home</h3></Nav.Link>
                  </li>
                  <li className="nav-item  mx-3">
                    <Nav.Link as={Link} to="/service"><h3>Services</h3></Nav.Link>
                  </li>
                  <li className="nav-item  mx-3">
                    <Nav.Link as={Link} to="/about"><h3>About Us</h3></Nav.Link>
                  </li>
                  <li className="nav-item mx-3">
                    <Nav.Link as={Link} to="/contact"><h3>Contact Us</h3></Nav.Link>
                  </li>
                  <li className="nav-item mx-3">
                    <Nav.Link as={Link} to="/login"><h3>Login</h3></Nav.Link>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </nav>
    </div>
  </>
  );
}

export default NavBarNew;