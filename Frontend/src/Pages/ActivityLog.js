import axios from "axios";
import { useEffect, useState } from "react";
import { Link,useLocation, useNavigate } from "react-router-dom";
import React from "react";
import Table from 'react-bootstrap/Table'
import { GrEdit} from "react-icons/gr";



function ActivityLog() {
  let config={
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwt")}`,
    },
  }
  let navigate = useNavigate();
  let [list, setList] = useState([]);

//   const editUser = (item, index) => {
//     navigate(`/simpleform?edit=true&id=${item.custno}`);
//   };
//   const AddUser = () => {
//     navigate(`/simpleform`);
//   };
  const getAllCall = async (item) => {
    const url = `http://localhost:9090/employeeAudit`;

    const response = await axios.get(url, config);

    const newlist = response.data;
    setList(newlist);
  };

  useEffect(() => {
    getAllCall();
  }, []);

  return (
    <div>
      {/* <OrdersTable item={list}/> */}
      <div className="text-center">
        <h2>Activity Logs</h2>
      </div>
      <div className="mx-3">
        <Table striped bordered hover size="sm" style={{width:"1200px"}} className="mx-auto">
          <thead>
            <tr>
              <th scope="col">Serial No.</th>
              <th scope="col">Emp. No.</th>
              <th scope="col">User</th>
              <th scope="col">Time Stamp</th>
              <th scope="col">Remark</th>
            </tr>
          </thead>
          <tbody>
            {list.map((item, index) => (
              <tr key={index}>
                <td>{item.serialno}</td>
                <td>{item.empno}</td>
                <td>{item.user}</td>
                <td>{item.timestamp}</td>
                <td>{item.remark}</td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
    </div>
  );
}

export default ActivityLog;