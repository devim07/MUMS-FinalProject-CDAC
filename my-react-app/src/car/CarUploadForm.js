import { useState } from "react";
import axios from "axios";
import { useLocation } from "react-router-dom";

function CarUploadForm(){

  // alert("ok");

 const carImg = useLocation();
 
//  alert("ok");

 const [selectedFile, setSelectedFile] = new useState({carImage: null});


 const handleChange = evnt =>{
  setSelectedFile({carImage: evnt.target.files[0]});
 };

 
 const handleSubmit = evnt => {
  
  
  evnt.preventDefault();

  const formData = new FormData();
 
  formData.append(
    'carImage',
    selectedFile.carImage,
    selectedFile.name
  );

  
  const config = {
    Headers: {
      'content-type': 'multipart/form-data'
    }
  }

  axios.post('http://localhost:8080/car/upload/'+carImg.state,formData,config)
  .then(response => {
   alert(response.data);
  })
  .catch(error => {
   alert(error);
  });


 }; 

 return <>
 <div class="container mt-3">
  <h2>car update form</h2>
 <form action="" onSubmit={handleSubmit} >
  <div class="mb-3 mt-3">
    <label for="carImage" class="form-label">Model:</label>
  <input type="file" name="carImage" onChange={handleChange} />
 </div>
 
 <button type="submit" class="btn btn-primary" >Upload</button>
 </form>
 </div>
 </>;
};
export default CarUploadForm;