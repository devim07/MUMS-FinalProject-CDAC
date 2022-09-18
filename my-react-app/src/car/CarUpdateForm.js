import { useState } from "react";
import axios from "axios";
import {useLocation} from "react-router-dom";

function CarUpdateForm(){

  const carState = useLocation();

 const [inputs, setInputs ] = useState(carState.state);

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

  axios.put('http://localhost:8080/car',inputs)
  .then(response => {
   alert(response.data);
  })
  .catch(error => {
   alert(error);
  });


 }; 

 return <>
 <div class="container mt-3">
  <h2>car Update form</h2>
 <form action="" onSubmit={handleSubmit} >
 <div class="mb-3 mt-3">
    
  <input type="hidden" name="id" value={inputs.id} onChange={handleChange} />
 </div>
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
 <button type="submit" class="btn btn-primary" >Update</button>
 </form>
 </div>
 </>;
};
export default CarUpdateForm;