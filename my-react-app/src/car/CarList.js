import axios from "axios";
import { useEffect, useState } from "react";
import {Link} from "react-router-dom";

function CarList(){

 const [responseData, setResponseData] = useState([]);

 const carList = () => {
  axios.get('http://localhost:8080/car')
  .then(response =>{
   setResponseData(response.data);
  } )
  .catch(error => {
   alert(error);
  });
 };


 useEffect( ()=>{

  carList();

 },[]);

const carDelete = evnt =>{
 axios.delete('http://localhost:8080/car/'+evnt.target.value)
 .then( response => {
  carList();
 } )
 .catch(error => {
  alert(error);
 })
};




 return <>

<div class="container mt-3">
  <h2>Car Information</h2>
            
  <table class="table table-striped">
  <thead>
   <tr>
    <th>Model</th>
    <th>Price</th>
    <th>Make</th>
    <th>Year</th>
    <th>Update</th>
    <th>Image</th>
    <th>Delete</th>
   </tr>
  </thead>
  <tbody>
   {
    responseData.map(
     val => <tr key="{val.id}"  >
      <td>{val.model}</td>
      <td>{val.price}</td>
      <td>{val.make}</td>
      <td>{val.year}</td>
      <td><Link to="/car/update" state={val} class="btn btn-primary" >Update</Link> </td>
      <td><Link to="/car/upload" state={val.id} class="btn btn-primary" >
       <img src={"http://localhost:8080/images/"+val.image}  width="70" height="70" alt="no image" ></img>
       </Link></td>
      <td><button type="button" id={val.id} value={val.id} onClick={carDelete} >X</button> </td>
     </tr>
    )
   }
  </tbody>
 </table>
 </div>
 </>;
};
export default CarList;