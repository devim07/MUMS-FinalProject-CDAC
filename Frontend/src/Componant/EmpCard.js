import { Avatar } from "@mui/material";
import { useEffect, useState } from "react";
import React from "react";
import { Link, NavLink } from "react-router-dom";
import "../../src/Style/AdminEmp.css"
import Dropdown from 'react-bootstrap/Dropdown';
import { GrEdit, GrImage} from "react-icons/gr";
import { IoTrashSharp } from "react-icons/io5";
import axios from "axios";


function EmpCard() {
  const [responseData, setResponseData] = useState([]);
  let config={
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwt")}`,
    },
  }

  const EmployeeList = () => {
    
    axios
      .get("http://localhost:9090/employee",config)
      .then((response) => {
        setResponseData(response.data);
      })
      .catch((error) => {
        alert(error);
      });
  };

  useEffect(() => {
    EmployeeList();
  }, []);

  const EmployeeDelete = (evnt) => {
    console.log(evnt);
    axios
      .delete("http://localhost:9090/employee/" + evnt.target.value, config)
      .then((response) => {
        EmployeeList();
      })
      .catch((error) => {
        alert(error);
      });
  };

  const handleSelect=(e)=>{
    axios
      .get("http://localhost:9090/employee/dept/"+e, config)
      .then((response) => {
        setResponseData(response.data);
      })
      .catch((error) => {
        alert(error);
      });
  };


  //=============================================================
    return (
        <>
            <div className="row" align="center" mt-5 mb-5>
                <div className="col-12 col-md-6 mb-1 mt-5" align="center">
                    <Dropdown onSelect={handleSelect}>
                        <Dropdown.Toggle variant="info" id="dropdown-basic" >
                            Sort By Department
                        </Dropdown.Toggle>

                        <Dropdown.Menu>
                            <Dropdown.Item eventKey="P" value="P">Production</Dropdown.Item>
                            <Dropdown.Item eventKey="F" value="F">Finnace</Dropdown.Item>
                            <Dropdown.Item eventKey="R" value="R">R & D</Dropdown.Item>
                            <Dropdown.Item eventKey="S" value="S">Sale</Dropdown.Item>
                        </Dropdown.Menu>
                    </Dropdown>
                </div>
                
                
                {/* <div className="col-12 col-md-4 mb-5 mt-5  text-Align-center">
                    <input type="text" placeholder="Emp No." className="textbox p-2" name="empno" />
                    <button type="button" className="mx-1 icon" style={{border: "none"}}>
                        <MdPersonSearch style={{color:"rgba(48, 60, 108, 1) ", fontSize: "2.75em"}}/>
                    </button>
                </div> */}

                
                <div className="col-12 col-md-6 mb-5 mt-5" align="center">
                    <button type="button" className="btn mx-1 icon" style={{border: "none"}}>
                        <Link to="/admin/Employee/add" className="">
                            Add New Employee
                        </Link>
                    </button>
                </div>
            </div>


            <div className="row mx-2">
                {responseData.map((item, index) => (
                    <div key={index} className="col-12 col-md-6 col-lg-4 md-2">
                        <div className="card my-3">
                            <div align="center" className="mt-2">
                                <Avatar
                                src={"https://picsum.photos/200/300?random=1"+item.empno}
                                    //src={"http://localhost:9090/images/"+item.photo}
                                    sx={{ width: 150, height: 150 }}
                                    style={{
                                        border: '4px solid rgba(251, 232, 166, 1) '
                                     }}
                                />
                            </div>
                            <div className="card-body mb-2" align="center">
                                <Link
                                    to="/admin/employee/details/card"
                                    id={item.empno}
                                    value={item.empno}
                                    state={item}
                                >
                                    <h5>{item.ename}</h5>
                                </Link>{" "}

                                <div className="row m-2">
                                    <div className="col-7 float-right">
                                        <b className="float-left">Emp. No.: </b><span className="float-left">{item.empno}</span>
                                    </div>
                                    <div className="col-5">
                                        <b>DEPT:</b><span>{item.deptno}</span>
                                    </div>
                                </div>
                                <div className="row m-2">
                                    <div className="col-12">
                                        <b className="float-left">EMAIL: </b><span className="float-left">{item.email}</span>
                                    </div>
                                </div>
                                <div className="row m-2">
                                    <div className="col-12 ">
                                        <b className="float-left">CITY: </b><span className="float-left">{item.city}</span>
                                    </div>
                                </div>
                                <div className="row m-2">
                                    <div className="col-12 ">
                                    <span>
                                            <button
                                                type="button"
                                                id={item.empno}
                                                value={item.empno}
                                                className="btn icon float-right"
                                                onClick={EmployeeDelete}
                                                style={{backgroundColor:"white", color:"red"}}
                                            >X
                                                {/* <IoTrashSharp value={item.empno} style={{color:"rgba(48, 60, 108, 1) ", fontSize: "1em"}}/> */}
                                            </button>{" "}
                                        </span>
                                    <span>
                                        <Link
                                            to="/admin/employee/photo-upload"
                                            state={item}
                                            className="btn icon float-right"
                                            style={{backgroundColor:"white"}}
                                        >
                                            <GrImage style={{color:"rgba(48, 60, 108, 1) ", fontSize: "1em"}}/>
                                        </Link>
                                        </span>
                                        <span>
                                        <Link
                                            to="/admin/employee/update"
                                            state={item}
                                            className="btn icon float-right"
                                            style={{backgroundColor:"white"}}
                                        >
                                            <GrEdit style={{color:"rgba(48, 60, 108, 1) ", fontSize: "1em"}}/>
                                        </Link>
                                        </span>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </>
    );
}

export default EmpCard;





















// import { Avatar } from "@mui/material";
// import { useEffect, useState } from "react";
// import React from "react";
// import { Link, NavLink } from "react-router-dom";
// import { MdPersonSearch } from "react-icons/md";
// import "../../src/Style/AdminEmp.css"
// import Dropdown from 'react-bootstrap/Dropdown';
// import { GrEdit, GrImage} from "react-icons/gr";

// import { IoTrashSharp } from "react-icons/io5";
// import axios from "axios";
// import { color } from "@mui/system";
// import EmpDetailsCard from "../Componant/EmpDetailsCard";
// import Button from 'react-bootstrap/Button';
// import Modal from 'react-bootstrap/Modal';


// function EmpCard() {
//   const [responseData, setResponseData] = useState([]);
//   const [empNo, setempNo] = useState("");
//   const [emp, setEmp]=useState({});


//   const EmployeeList = () => {
//     axios
//       .get("http://localhost:9090/employee")
//       .then((response) => {
//         setResponseData(response.data);
//       })
//       .catch((error) => {
//         alert(error);
//       });
//   };

//   useEffect(() => {
//     EmployeeList();
//   }, []);








// //   const GetEmployee = (event) => {
// //     alert(empNo);
// //     axios
// //       .get("http://localhost:9090/employee/"+empNo)
// //       .then((response) => {
// //         setEmp(response.data);
// //         // <EmpDetailsCard value={empNo} state={emp} />;
// //         console.log(response.data);
// //       })
// //       .catch((error) => {
// //         alert(error);
// //       });
// //   };





// //   useEffect(() => {
// //     EmployeeList();
// //   }, []);

//   const EmployeeDelete = (evnt) => {
//     alert(evnt.target.value);
//     axios
//       .delete("http://localhost:9090/employee/" + evnt.target.value)
//       .then((response) => {
//         EmployeeList();
//       })
//       .catch((error) => {
//         alert(error);
//       });
//   };

//   const handleSelect=(e)=>{
//     axios
//       .get("http://localhost:9090/employee/dept/"+e)
//       .then((response) => {
//         setResponseData(response.data);
//       })
//       .catch((error) => {
//         alert(error);
//       });
//   };


// //   const handlePhotoUpload=(e)=>{
// //     modal();
// //     axios
// //       .get()
// //       .then((response) => {
// //         setResponseData(response.data);
// //       })
// //       .catch((error) => {
// //         alert(error);
// //       });
// //   };


//   //=============================================================
//     return (
//         <>
//             <div className="row" align="center" mt-5 mb-5>
//                 <div className="col-12 col-md-6 mb-1 mt-5" align="center">
//                     <Dropdown onSelect={handleSelect}>
//                         <Dropdown.Toggle variant="info" id="dropdown-basic" >
//                             Sort By Department
//                         </Dropdown.Toggle>

//                         <Dropdown.Menu>
//                             <Dropdown.Item eventKey="P" value="P">Production</Dropdown.Item>
//                             <Dropdown.Item eventKey="F" value="F">Finnace</Dropdown.Item>
//                             <Dropdown.Item eventKey="R" value="R">R & D</Dropdown.Item>
//                             <Dropdown.Item eventKey="S" value="S">Sale</Dropdown.Item>
//                         </Dropdown.Menu>
//                     </Dropdown>
//                 </div>
                
                
//                 {/* <div className="col-12 col-md-4 mb-5 mt-5  text-Align-center">
//                     <input type="text" placeholder="Emp No." className="textbox p-2" name="empno" onChange={e => setempNo(e.target.value)}/>
//                     <button type="button" className="mx-1 icon" style={{border: "none"}} onClick={GetEmployee}>
//                         <MdPersonSearch style={{color:"rgba(48, 60, 108, 1) ", fontSize: "2.75em"}}/>
//                     </button>
//                 </div> */}

                
//                 <div className="col-12 col-md-6 mb-5 mt-5" align="center">
//                     <button type="button" className="btn mx-1 icon" style={{border: "none"}}>
//                         <Link to="/admin/Employee/add" className="">
//                             Add New Employee
//                         </Link>
//                     </button>
//                 </div>
//             </div>


//             <div className="row mx-2">
//                 {responseData.map((item, index) => (
//                     <div key={index} className="col-12 col-md-4 md-2">
//                         <div className="card my-3">
//                             <div align="center" className="mt-2">
//                                 <Avatar
//                                     src={"http://localhost:9090/images/"+item.photo}
//                                     sx={{ width: 150, height: 150 }}
//                                     style={{
//                                         border: '4px solid rgba(251, 232, 166, 1) '
//                                      }}
//                                     //  onClick={handlePhotoUpload}
//                                 />
//                             </div>
//                             <div className="card-body mb-2" align="center">
//                                 <Link
//                                     to="/admin/employee/details/card"
//                                     id={item.empno}
//                                     value={item.empno}
//                                     state={item}
//                                 >
//                                     <h5>{item.ename}</h5>
//                                 </Link>

//                                 <div className="row m-2">
//                                     <div className="col-7 float-right">
//                                         <b className="float-left">Emp. No.: </b><span className="float-left">{item.empno}</span>
//                                     </div>
//                                     <div className="col-5">
//                                         <b>DEPT:</b><span>{item.deptno}</span>
//                                     </div>
//                                 </div>
//                                 <div className="row m-2">
//                                     <div className="col-12">
//                                         <b className="float-left">EMAIL: </b><span className="float-left">{item.email}</span>
//                                     </div>
//                                 </div>
//                                 <div className="row m-2">
//                                     <div className="col-6 ">
//                                         <b className="float-left">CITY: </b><span className="float-left">{item.city}</span>
//                                     </div>
//                                     <div className="col-6 ">
//                                     {/* <span>
//                                         <Link
//                                             to="/admin/employee/update"
//                                             state={item}
//                                             className="btn icon float-right"
//                                             style={{backgroundColor:"white"}}
//                                         >
//                                             <GrImage style={{color:"rgba(48, 60, 108, 1) ", fontSize: "1em"}}/>
//                                         </Link>
//                                         </span> */}
//                                         <span>
//                                         <Link
//                                             to="/admin/employee/update"
//                                             state={item}
//                                             className="btn icon float-right"
//                                             style={{backgroundColor:"white"}}
//                                         >
//                                             <GrEdit style={{color:"rgba(48, 60, 108, 1) ", fontSize: "1em"}}/>
//                                         </Link>
//                                         </span>
//                                         <span>
//                                             <button
//                                                 type="button"
//                                                 id={item.empno}
//                                                 value={item.empno}
//                                                 className="btn icon float-right"
//                                                 onClick={EmployeeDelete}
//                                                 style={{backgroundColor:"white"}}
//                                             >
//                                                 <IoTrashSharp style={{color:"rgba(48, 60, 108, 1) ", fontSize: "1em"}}/>
//                                             </button>{" "}
//                                         </span>
//                                     </div>
//                                 </div>
//                             </div>
//                         </div>
//                     </div>
//                 ))}
//             </div>
//         </>
//     );
// }

// export default EmpCard;