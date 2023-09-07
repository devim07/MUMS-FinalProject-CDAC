import { useEffect, useState } from "react";
import React from "react";
import "../../src/Style/AdminEmp.css"
import Dropdown from 'react-bootstrap/Dropdown';
import axios from "axios";
import Table from 'react-bootstrap/Table'



function IncExp() {
  let config={
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwt")}`,
    },
  }
  const [responseData, setResponseData] = useState([]);

  const EmployeeList = () => {
    axios
      .get("http://localhost:9090/incmexptally", config)
      .then((response) => {
        console.log(response.data);
        setResponseData(response.data);
      })
      .catch((error) => {
        alert(error);
      });
  };

  useEffect((event) => {
    EmployeeList();
  }, []);

  let [month, setMonth] = useState(0);
  let [year, setYear] = useState(0);


  const handleSubmit=(e)=>{
    axios
      .get("http://localhost:9090/incmexptally/"+year+"/"+month, config)
      .then((response) => {
        setResponseData(response.data);
      })
      .catch((error) => {
        alert(error);
      });
  };

  const handleSelectMonth=(e)=>{
    setMonth(e);
  };

  const handleSelectYear=(e)=>{
    setYear(e);
  };


  //=============================================================
    return (
        
        <>
        <div class="container" style={{width:"1000px"}}>
            <div className="row" align="center" mt-5 mb-5>
                <span >
                    <Dropdown onSelect={handleSelectMonth}>
                        <Dropdown.Toggle variant="info" id="dropdown-basic" >
                            Select Month
                        </Dropdown.Toggle>

                        <Dropdown.Menu>
                            <Dropdown.Item eventKey="1" value="1">JANUARY</Dropdown.Item>
                            <Dropdown.Item eventKey="2" value="2">FEBRUARY</Dropdown.Item>
                            <Dropdown.Item eventKey="3" value="3">MARCH</Dropdown.Item>
                            <Dropdown.Item eventKey="4" value="4">APRIL</Dropdown.Item>
                            <Dropdown.Item eventKey="5" value="5">MAY</Dropdown.Item>
                            <Dropdown.Item eventKey="6" value="6">JUNE</Dropdown.Item>
                            <Dropdown.Item eventKey="7" value="7">JULY</Dropdown.Item>
                            <Dropdown.Item eventKey="8" value="8">AUGUST</Dropdown.Item>
                            <Dropdown.Item eventKey="9" value="9">SEPTEMBER</Dropdown.Item>
                            <Dropdown.Item eventKey="10" value="10">OCTOBER</Dropdown.Item>
                            <Dropdown.Item eventKey="11" value="11">NOVEMBER</Dropdown.Item>
                            <Dropdown.Item eventKey="12" value="12">DECEMBER</Dropdown.Item>
                        </Dropdown.Menu>
                    </Dropdown>
                </span>

                <span >
                    <Dropdown onSelect={handleSelectYear}>
                        <Dropdown.Toggle variant="info" id="dropdown-basic" >
                            Select Year
                        </Dropdown.Toggle>

                        <Dropdown.Menu>
                            <Dropdown.Item eventKey="2023" value="2022">2023</Dropdown.Item>
                            <Dropdown.Item eventKey="2022" value="2021">2022</Dropdown.Item>
                            <Dropdown.Item eventKey="2021" value="2020">2021</Dropdown.Item>
                        </Dropdown.Menu>
                    </Dropdown>
                </span>


                
                <span >
                    <button type="button" className="btn mx-1 icon" style={{border: "none"}} onClick={handleSubmit}>
                            Submit
                    </button>
                </span>
            </div>
            </div>
            <br/>


            <div className="row mx-2">
                <Table striped bordered hover size="sm" style={{width:"700px"}} className="mx-auto">
                    <thead>
                      <tr>
                        <th scope="col">Head</th>
                        <th scope="col">Amount</th>
                        <th scope="col">Entery Date</th>
                        <th scope="col">Remark</th>
                      </tr>
                    </thead>
                    <tbody>
                      {responseData.map((item, index) => (
                        <tr key={index}>
                          <td>{item.head}</td>
                          <td>{item.amount}</td>
                          <td>{item.entrydate}</td>
                          <td>{item.remark}</td>
                        </tr>
                      ))}
                    </tbody>
                  </Table>
            </div>
        </>
    );
}

export default IncExp;