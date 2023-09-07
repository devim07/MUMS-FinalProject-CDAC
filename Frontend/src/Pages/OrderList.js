import axios from "axios";
import { useEffect, useState } from "react";
import { Link, } from "react-router-dom";
import React from "react";
import Table from 'react-bootstrap/Table'
import { GrEdit} from "react-icons/gr";
import "../Style/AdminEmp.css"
// import OrdersTable from "./Orders/OrdersTable.js"

function OrderList() {

  let config={
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwt")}`,
    },
  }
  

  let [list, setList] = useState([]);

 
  const getAllUserCall = async (item) => {
    const url = `http://localhost:9090/order`;

    const response = await axios.get(url, config);

    const newlist = response.data;
    setList(newlist);
  };

  useEffect(() => {
    getAllUserCall();
  }, []);


  return (
    <div>
      {/* <OrdersTable item={list}/> */}
      <div className="text-center">
        <h2>All Orders</h2>
        {/* <span
          className="badge bg-primary"
          role="button"
          onClick={() => AddUser()}
        >
          AddOrder
        </span> */}
      </div>
      <div className="mx-3">
        <Table striped bordered hover size="sm" style={{width:"1000px"}} className="mx-auto">
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
  );
}

export default OrderList;
