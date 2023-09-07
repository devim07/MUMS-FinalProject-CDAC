import React from "react";

import { Container, Row, Col, ListGroup, ListGroupItem } from "reactstrap";
import { Link } from "react-router-dom";


const Footer = () => {
  const date = new Date();
  const year = date.getFullYear();
  return (
    <footer className="footer my-0" style={{height:"60px", backgroundColor:"rgb(255, 242, 196)", zIndex:"10"}}>
            <div className="footer__bottom " >
              <p className="section__description d-flex text-center justify-content-center">
                <i class="ri-copyright-line"></i>Copyright @{year}, Developed by
                MUMS. All rights reserved.<br/> Developed by Devi Dinesh, Manoj Deweda, Shubham Rane, Aniket Pattiwar, and Akshay Jadhav
              </p>
            </div>     
    </footer>
  );
};

export default Footer;
