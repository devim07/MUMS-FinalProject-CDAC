import React from "react";
import { NavLink } from "react-router-dom";
import "../index.css";

function Card({ title, imgsrc, content}) {
  return (
    <>
      <div className="col-md-4 col-10 mx-auto my-3" >
        <div className="card card-hover">
          <div style={{height:"250px"}} >
          <img src={imgsrc} className="card-img-top my-auto" alt="..." />
          </div>
          
          <div className="card-body">
            <h5 className="card-title text-center">{title}</h5>
            <p className="card-text">{content}
            </p>
          </div>
        </div>
      </div>
    </>
  );
}

export default Card;
