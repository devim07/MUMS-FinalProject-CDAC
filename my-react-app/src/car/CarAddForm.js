import { useState } from "react";
import axios from "axios";

function CarAddFrom(){

 const [inputs, setInputs ] = useState({});

 const handleChange = evnt =>{
  let paramName = evnt.target.name;
  let paramValue = evnt.target.value;

  setInputs(
   values => ({...values,[paramName]:paramValue })
  );
 };

 const handleSubmit = evnt => {
  evnt.preventDefault();
  // alert(JSON.stringify(inputs));

  axios.post('http://localhost:8080/car',inputs)
  .then(response => {
   alert(response.data);
  })
  .catch(error => {
   alert(error);
  });


 }; 

 return <>
 <div class="container mt-3">
  <h2>car add form</h2>
 <form action="" onSubmit={handleSubmit} >
  <div class="mb-3 mt-3">
    <label for="model" class="form-label">Model:</label>
  <input type="text" name="model" value={inputs.model} onChange={handleChange} />
 </div>
 <div class="mb-3">
    <label for="price" class="form-label">Price:</label>
   <input type="text" name="price" value={inputs.price} onChange={handleChange} />
 </div>
 <div class="mb-3">
    <label for="make" class="form-label">Make:</label>
   <input type="text" name="make" value={inputs.make} onChange={handleChange} />
 </div>
 <div class="mb-3">
    <label for="year" class="form-label">Year:</label>
   <input type="text" name="year" value={inputs.year} onChange={handleChange} />
 </div>
 <button type="submit" class="btn btn-primary" >Add</button>
 </form>
 </div>
 </>;
};
export default CarAddFrom;