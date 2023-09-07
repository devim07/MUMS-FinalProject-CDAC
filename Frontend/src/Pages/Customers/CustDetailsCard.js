import { useEffect, useState } from "react";
import React from "react";
import axios from "axios";
import { Avatar } from "@mui/material";
import Table from 'react-bootstrap/Table'
import { Link,useLocation} from "react-router-dom";
import { GrEdit} from "react-icons/gr";

function CustDetailsCard() {
  let config={
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwt")}`,
    },
  }

  const CustState = useLocation();
  const [responseData, setResponseData] = useState([]);
  const [inputs, setInputs] = useState(CustState.state);

  console.log(CustState.state.custno);
  const CustDetailsCard = () => {
    axios
      .get("http://localhost:9090/customer/" + CustState.state.custno, config)
      .then((response) => {
        setResponseData(response.data);
      })
      .catch((error) => {
        alert(error);
      });
  };
  console.log(responseData);
  useEffect(() => {
    CustDetailsCard();
    getUserById();
  }, []);


  const [list, setList] = useState([]);
  const getUserById = async (item) => {
    const url = `http://localhost:9090/order/customer/${CustState.state.custno}`;
    const response = await axios.get(url, config);

    console.log(response.data);
    const newlist = response.data;
    setList(newlist);
  };

  return (
    <>
      <div className="mt-3"></div>
      <div className="row">
        <div className="col-12 col-md-8 md-2 mx-auto " >
          <div className="card" >
            <h2 className="text-center">Customer Detail Information</h2>
            <div>
              <div align="center" className="mt-2">
                <Avatar
                  src={"https://picsum.photos/200/300?random=1"}
                  sx={{ width: 150, height: 150 }}
                /><br/>
                <h3>{CustState.state.custname}</h3>
              </div>
            </div>
            <div className="row">
              <div className="col-12 md-2 mx-auto">
                <div className="card-body" align="center"></div>
                <div className="text-center container">
                  <table
                    className="table mt-2"
                    style={{ width: "700px", textAlign: "right" }}
                  >
                    <thead align="center">
                      <tr>
                        <th>Customer Number</th>
                        <td>{CustState.state.custno}</td>
                      </tr>

                      <tr>
                        <th>Salesman Number</th>
                        <td>{CustState.state.salesno}</td>
                      </tr>
                      <tr>
                        <th>CITY</th>
                        <td>{CustState.state.city}</td>
                      </tr>
                      <tr>
                        <th>Mobile Number</th>
                        <td>{CustState.state.mobileNumber}</td>
                      </tr>
                      <tr>
                        <th>Rating</th>
                        <td>{CustState.state.rating}</td>
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

      <div>
      <div className="text-center">
        <h2>All Orders</h2>
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
              <th scope="col"></th>
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
                <td>
                <Link
                  to="/admin/orders/update"
                  state={item}
                  className="btn icon float-right"
                  style={{backgroundColor:"rgba(48, 60, 108, 0)"}}
                >
                  <GrEdit style={{color:"rgba(48, 60, 108, 1) ", fontSize: "1em"}}/>
                </Link>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
    </div>
    </>
  );
}

export default CustDetailsCard;
