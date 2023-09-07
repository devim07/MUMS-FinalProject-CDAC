import { useEffect, useState } from "react";
import React from "react";
import { Link, useNavigate } from "react-router-dom";

import axios from "axios";
import { Avatar } from "@mui/material";
import Table from "react-bootstrap/Table";
import AfteLoginNav from "../Componant/AfterLoginNav"


function EmpMainPage() {
  let config={
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwt")}`,
    },
  }
  const empno=localStorage.getItem("user");
  const [EmployeeState,setEmployeeState] =useState({});
  let navigate=useNavigate();
  const getEmp = () => {
  axios
      .get(
        "http://localhost:9090/employee/" +empno,config)
      .then((response) => {
        setEmployeeState(response.data);
        //console.log("get call for emp"+response.data);
      })
      .catch((error) => {
        navigate=("/error", {replace:true});
      });
    };


  const [responseData, setResponseData] = useState([]);
  console.log(EmployeeState.empno);
  const EmpDetailsCard = () => {
    axios
      .get(
        "http://localhost:9090/employee/details/" + localStorage.getItem("user"), config)
      .then((response) => {
        setResponseData(response.data);
      })
      .catch((error) => {
        //alert(error);
        navigate=("/error", {replace:true});
      });
  };


  console.log(responseData);
  useEffect(() => {
    getEmp();
    EmpDetailsCard();
    
  }, []);

  const [inputs, setInputs] = useState({});

  const handleChange = (evnt) => {
    let paramName = evnt.target.name;
    let paramValue = evnt.target.value;

    setInputs((values) => ({ ...values, [paramName]: paramValue }));
  };
  useEffect(() => {
    MonthSalary();
  }, []);

  const MonthSalary = (evnt) => {
    axios
      .get(
        "http://localhost:9090/oldsalary/" +localStorage.getItem("user") +"/" +inputs.year +"/" + inputs.month, config)
      .then((response) => {
        // alert(typeof response.data);

        setInputs(response.data);
      })
      .catch((error) => {
        //alert(error);
        navigate=("/error", {replace:true});
      });
  };

  console.log(inputs);
  let [from, setFrom]=useState();
  let [to, setTo]=useState()

  const placeLeave= ()=>{
    let days=(to.getTime()-from.getTime())/(1000*60*60*24);
    axios
      .get(
        "http://localhost:9090/employee/holiday/" +localStorage.getItem("user") +"/"+days, config)
      .then((response) => {
        alert("holiday request placed for "+days+" days");
        setInputs(response.data);
      })
      .catch((error) => {
        //alert(error);
        navigate=("/error", {replace:true});
      });

  };



  return (
    <>
    <AfteLoginNav/> 
    <div class="container" style={{width:"1400px"}}>
      <div id="pdf-view">
        <div className="container mt-3"></div>

        <div className="row">
          <div className="col-12 col-md-10 md-2 mx-auto ">
            <div className="card">
              <h2 className="text-center">EmpDetail Information</h2>
              <div>
                <div align="center" className="mt-2">
                  <Avatar
                    src={"https://picsum.photos/200/300?random=1"}
                    sx={{ width: 300, height: 300 }}
                  />
                  <h3>{EmployeeState.ename}</h3>
                </div>
              </div>
              <div className="row">
                <div className="col-12 col-md-6 md-2">
                  <div className="card-body"></div>
                  <table className="table mt-2">
                    <thead>
                      <tr>
                        <th>EMPLOYEE No.</th>
                        <td>{EmployeeState.empno}</td>
                      </tr>
                      {/* <tr>
                      <th>Employee Name</th>
                      <td>{EmployeeState.state.ename}</td>
                    </tr> */}
                      <tr>
                        <th>EMAIL</th>
                        <td>{EmployeeState.email}</td>
                      </tr>
                      <tr>
                        <th>HIREDATE</th>
                        <td>{EmployeeState.hiredate}</td>
                      </tr>
                      <tr>
                        <th>DEPT</th>
                        <td>{EmployeeState.deptno}</td>
                      </tr>
                      <tr>
                        <th>JOB</th>
                        <td>{EmployeeState.job}</td>
                      </tr>
                      <tr>
                        <th>CITY</th>
                        <td>{EmployeeState.city}</td>
                      </tr>
                      <tr>
                        <th>Gender</th>
                        <td>{responseData.gender}</td>
                      </tr>
                    </thead>
                  </table>
                </div>
                <div className="col-12 col-md-6 md-2">
                  <div className="card-body"></div>
                  <table className="table table-striped mt-2">
                    <thead>
                      {/* <tr>
                   <th>Employee Name</th>
                   <td>{EmployeeState.state.ename}</td>
                </tr> */}

                      <tr>
                        <th>Mobile No.</th>
                        <td>{responseData.mobileNumber}</td>
                      </tr>

                      <tr>
                        <th>Aadhar No.</th>
                        <td>{responseData.aadhar}</td>
                      </tr>

                      <tr>
                        <th>DOB</th>
                        <td>{responseData.dob}</td>
                      </tr>

                      <tr>
                        <th>Pin Code</th>
                        <td>{responseData.pinCode}</td>
                      </tr>

                      <tr>
                        <th>Address</th>
                        <td>{responseData.address}</td>
                      </tr>
                      <br />
                      <br />
                      <tr>
                        <td>
                          <Link
                            to="/employee/update"
                            state={responseData}
                            className="btn bg-primary"
                          >
                            Update
                          </Link>{" "}
                        </td>
                      </tr>
                    </thead>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className="row" align="center" mt-5 mb-5>
          <div className="col-12 col-md-4 my-5  text-Align-center">
            <input
              type="text"
              placeholder="Enter Month"
              name="month"
              onChange={handleChange}
              style={{width:"100px", margin:"2px 2px"}}
            />
            <input
              type="text"
              placeholder=" Enter year"
              name="year"
              onChange={handleChange}
              style={{width:"100px", margin:"2px, 2px"}}
            />
            <button
              type="button"
              className="btn bg-primary mx-1 my-2"
              // state={responseData}
              onClick={() => MonthSalary()}
            >
              View Salary Slip
            </button>
          </div>
        </div>
        

        <div className="m-6">
          <Table className="table mb-3" bordered hover>
            <thead>
              <tr>
                <th scope="col">Emp. No.</th>
                <th scope="col">Dept. No</th>
                <th scope="col">Month</th>
                <th scope="col">Year</th>
                <th scope="col">Basic Sal</th>
              </tr>
            </thead>
            <tbody className="m-5">
              <tr>
                <td>{inputs.empno}</td>
                <td>{inputs.deptno}</td>
                <td>{inputs.month}</td>
                <td>{inputs.year}</td>
                <td>{inputs.basicsal}</td>
              </tr>
            </tbody>
          </Table>
          </div>
          <div className="m-5">
          <Table className="table mb-3" bordered hover>
            <thead>
              <tr>
                <th scope="col">Holidays</th>
                <th scope="col">HRA</th>
                <th scope="col">Comm.</th>
                <th scope="col">Total Salary</th>
              </tr>
            </thead>
            <tbody className="m-5">
              <tr>
                <td>{inputs.holidays}</td>
                <td>{inputs.hra}</td>
                <td>{inputs.comm}</td>
                <td>{inputs.totalsal}</td>
              </tr>
            </tbody>
          </Table>
        </div>
        <br />
        <br />
      </div>
      <button onClick={()=>{
        window.print();
    }}>Download as pdf</button>
      <br />
      <br />
      </div>
    </>
  );
}
// export default EmpDetailsCard;

const Appppppp = () => {
  return (
    <div>
      <div id="pdf-view">
        <EmpMainPage />
        {/* <h1 style={{ color: "#33959a" }}>Testing html to pdf converter</h1> */}
      </div>
      {/* <button onClick={pdfDownload}>Download as pdf</button> */}
    </div>
  );
};

export default Appppppp;
/////////////////////////////////////
// class DataComponent extends React.Component {
//   render() {
//     return <EmpDetailsCard />;
//   }
// }
// //////////////////

// class PdfComponent extends React.Component {
//   render() {
//     return (
//       <div>
//         <ReactToPrint
//           content={() => this.componentRef}
//           trigger={() => (
//             <button className="btn btn-primary">Print to PDF!</button>
//           )}
//         />
//         <DataComponent ref={(response) => (this.componentRef = response)} />
//       </div>
//     );
//   }
// }

// export default PdfComponent;

// .react-pdf_Page_canvas{
