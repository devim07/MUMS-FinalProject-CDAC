import { useState } from "react";
import axios from "axios";
import { useLocation } from "react-router-dom";
import React from "react";
function EmployeeUploadForm() {
  // alert("ok");

  const EmployeeImg = useLocation();

  //  alert("ok");

  const [selectedFile, setSelectedFile] = new useState({ EmployeeImage: null });

  const handleChange = (evnt) => {
    setSelectedFile({ employeeImage: evnt.target.files[0] });
  };

  const handleSubmit = (evnt) => {
    evnt.preventDefault();

    const formData = new FormData();

    formData.append(
      "EmployeeImage",
      selectedFile.EmployeeImage,
      selectedFile.name
    );

    const config = {
      Headers: {
        "content-type": "multipart/form-data",
        Authorization: `Bearer ${localStorage.getItem("jwt")}`,
      },
    };

    axios
      .post(
        "http://localhost:9090/employee/upload/" + EmployeeImg.state,
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
      <div className="container mt-3">
        <h2>Employee update form</h2>
        <form action="" onSubmit={handleSubmit}>
          <div className="mb-3 mt-3">
            <label forname="EmployeeImage" className="form-label">
              Model:
            </label>
            <input type="file" name="EmployeeImage" onChange={handleChange} />
          </div>

          <button type="submit" className="btn btn-primary">
            Upload
          </button>
        </form>
      </div>
    </>
  );
}
export default EmployeeUploadForm;
