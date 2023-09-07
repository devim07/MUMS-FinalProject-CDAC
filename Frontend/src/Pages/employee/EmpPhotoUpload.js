import { useEffect, useState } from "react";
import axios from "axios";
import {useLocation } from "react-router-dom";
import React from "react";


function EmpPhotoUpload() {
  const EmpDetailImg = useLocation();
  const[selectedFile, setSelectedFile]=new useState({img:null});
  
  const handleChange = (evnt) => {
    alert("change");
    setSelectedFile({img:evnt.target.files[0]})
  };
 
  const handleSubmit = (evnt) => {
    let formData = new FormData();
  formData.append(
    'image', 
    selectedFile.img, 
    selectedFile.name
  );  
    alert("submit");
    evnt.preventDefault();  
    const config = {
      Headers: {
        "content-type": "multipart/form-data",
        Authorization: `Bearer ${localStorage.getItem("jwt")}`,
      },
    };
        
    axios
    .post(
      "http://localhost:9090/upload/employee/" + EmpDetailImg.state.empno,
      formData,
      config
    )
    .then((response) => {
      alert(response.data);
    })
    .catch((error) => {
      alert(error);
    });
};

  return (
     <>
      <div className="container" style={{height:"80vh"}}>
        <h2>EmpDetail update form</h2>
        <form action="" onSubmit={handleSubmit}>
          <div className="mb-3 mt-3">
            <label forName="image" className="form-label">
              Model:
            </label>
            <input type="file" name="image" onChange={handleChange} />
          </div>

          <button type="submit" className="btn btn-primary">
            Upload
          </button>
        </form>
      </div>
    </>
  );
}

export default EmpPhotoUpload;