import { useEffect, useState } from "react";
import React from "react";
import axios from "axios";
import { Avatar } from "@mui/material";
import Table from 'react-bootstrap/Table'
import { Link, useNavigate} from "react-router-dom";
import AfteLoginNav from "../Componant/AfterLoginNav";



function CustMainPage() {
  let config={
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwt")}`,
    },
  }

  let navigate=useNavigate();
  const role="ROLE_CUSTOMER"

  if(role!=localStorage.getItem("role"))
    navigate=("/error", {replace:true});


      const [responseData, setResponseData] = useState([]);
      
      const cnum=localStorage.getItem("user");
      const CustDetailsCard = () => {
        axios
          .get("http://localhost:9090/customer/"+cnum, config)
          .then((response) => {
            setResponseData(response.data);
          })
          .catch((error) => {
            navigate=("/error", {replace:true});
          });
      };
      console.log(responseData);


      useEffect(() => {
        CustDetailsCard();
        getUserById();
      }, []);


      const[input, setInput]=useState({});
      const handleChange=(evnt)=>{
        let paramName = evnt.target.name;
        let paramValue = evnt.target.value;
        setInput((values) => ({ [paramName]: paramValue , "custno":cnum, "orderDate":new Date()}));
      }


      const handleSubmit=(e)=>{
        axios
          .post("http://localhost:9090/order/", input, config)
          .then((response) => {
            setResponseData(response.data);
          })
          .catch((error) => {
            alert(error);
            navigate("/error",{replace:true});
          });
      };

      const [list, setList] = useState([]);
        const getUserById = async (item) => {
            const url = "http://localhost:9090/order/customer/"+cnum;
            const response = await axios.get(url, config);
            console.log(response.data);
            const newlist = response.data;
            setList(newlist);
        };

    
      return (
        <>
        <AfteLoginNav/>
          <div className="mt-3"></div>
          <div className="row">
            <div className="col-10 md-2 mx-auto " >
              <div className="card">
              <h2 className="text-center">Customer Detail Information</h2>
                <div className="row">
                  <div align="center" className="col-4 mt-2">
                    <Avatar
                      src={"https://picsum.photos/200/300?random=1"}
                      sx={{ width: 200, height: 200 }}
                    /><br/>
                    <h3>{responseData.custname}</h3>
                  </div>

                  <div className="col-8 md-2 mx-auto">
                    <div className="card-body" align="center"></div>
                    <div className="text-center container">
                      <table
                        className="table mt-2"
                        style={{ textAlign: "right" }}
                      >
                        <thead align="center">
                          <tr>
                            <th>Customer Number</th>
                            <td>{responseData.custno}</td>
                          </tr>
    
                          <tr>
                            <th>Salesman Number</th>
                            <td>{responseData.salesno}</td>
                          </tr>
                          <tr>
                            <th>CITY</th>
                            <td>{responseData.city}</td>
                          </tr>
                          <tr>
                            <th>Mobile Number</th>
                            <td>{responseData.mobileNumber}</td>
                          </tr>
                          <tr>
                            <th>Rating</th>
                            <td>{responseData.rating}</td>
                          </tr>
                        </thead>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <br />
          <br />
   
                  

          <div className="container">
            <div className="row mx-auto my-4">
            <div className="col-3"></div>
                <div className="col-3">
                    <input
                        type="number"
                        name="orderUnit"
                        className="form-control"
                        placeholder="Units of Product"
                        onChange={handleChange}
                        style={{width:"200px"}}
                    />
                </div>
                <div className="col-3">
                    <a target="_blank" href="https://rzp.io/l/k3vPzopV">
                        <button type="button" className="btn mx-1 icon" style={{border: "none"}} onClick={handleSubmit} style={{width:"200px"}}> 
                            Place Order
                        </button>
                    </a>
                </div>        
            </div>
        
        </div>
    
          <div>
            <br/>
          <div className="text-center">
            <h2>All Orders</h2>
            <br/>
          </div>
          <div className="mx-3">
            <Table striped bordered hover size="sm" style={{width:"900px"}} className="mx-auto">
              <thead>
                <tr>
                  <th scope="col">Order No.</th>
                  <th scope="col">Cust No.</th>
    
                  <th scope="col">Units</th>
    
                  <th scope="col">Order Date</th>
                  <th scope="col">Status</th>
                  <th scope="col">Batch No.</th>
                </tr>
              </thead>
              <tbody>
                {list.map((item, index) => (
                  <tr key={index}>
                    <td>{item.orderno}</td>
                    <td>{item.custno}</td>
                    <td>{item.orderUnit}</td>
                    <td>{item.orderDate}</td>
                    <td>{item.status}</td>
                    <td>{item.batchno}</td>
                  </tr>
                ))}
              </tbody>
            </Table>
          </div>
        </div>
        </>
      );
    }
    
    export default CustMainPage;