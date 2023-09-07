import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import Home from "../Pages/Home";
import About from "../Pages/About";
import Contact from "../Pages/Contact";
import Service from "../Pages/Service";
import AdminDashBoard from "../Pages/AdminDashBoard";
import AdminLayout from "../Pages/employee/AdminLayout";
import EmployeeAddForm from "../Pages/employee/EmployeeAddForm";
import EmployeeUpdateForm from "../Pages/employee/EmployeeUpdateForm";
import EmpPhotoUpload from "../Pages/employee/EmpPhotoUpload";
import EmpDetailAddForm from "../Pages/employee/details/EmpDetailAddForm";
import EmpDetailUpdateForm from "../Pages/employee/details/EmpDetailUpdateForm";
import EmpDetailsCard from "../Componant/EmpDetailsCard";
import EmpCard from "../Componant/EmpCard";
import Emp from "../Componant/Emp";
import Login from "../Pages/Login";
import Cust from "../Pages/Customers/Cust"
import CustCardList from "../Pages/Customers/CustCardList";
import CustAddForm from "../Pages/Customers/CustAddForm";
import CustUpdateForm from "../Pages/Customers/CustUpdateForm";
import CustDetailsCard from "../Pages/Customers/CustDetailsCard";
import OrdersUpdateForm from "../Pages/Orders/OrdersUpdateForm";
import OrderList from "../Pages/OrderList";
import ActivityLog from "../Pages/ActivityLog";
import IncExp from "../Pages/IncExp";
import Appppppp from "../Pages/EmpMainPage";
import SalaryAdmin from "../Pages/SalaryAdmin";
import CustMainPage from "../Pages/CustMainPage";
import ErrorPage from "../Pages/ErrorPage";
import CustOrderUpdate from "../Pages/CustOrderUpdate"

const Routers = () => {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/home" />} />
      <Route path="/home" element={<Home />} />
      <Route path="/about" element={<About />} />
      <Route path="/contact" element={<Contact />} />
      <Route path="/service" element={<Service />} />
      <Route path="/login" element={<Login />} />


      <Route path="/admin/" element={<ProtectedRoute><AdminLayout /></ProtectedRoute>}>
          <Route index element={<ProtectedRoute><AdminDashBoard /></ProtectedRoute>}></Route>
          <Route path="salary/" element={<ProtectedRoute><SalaryAdmin /></ProtectedRoute>}></Route>
          <Route path="employee/" element={<ProtectedRoute><Emp /></ProtectedRoute>}>
              <Route index element={<ProtectedRoute><EmpCard /></ProtectedRoute>}></Route>
              <Route path="add" element={<ProtectedRoute><EmployeeAddForm /></ProtectedRoute>}></Route>
              <Route path="update" element={<ProtectedRoute><EmployeeUpdateForm /></ProtectedRoute>}></Route>
              <Route path="details/card" element={<ProtectedRoute><EmpDetailsCard /></ProtectedRoute>}></Route>
              <Route path="details/add" element={<ProtectedRoute><EmpDetailAddForm /></ProtectedRoute>}></Route>
              <Route path="details/update" element={<ProtectedRoute><EmpDetailUpdateForm /></ProtectedRoute>}></Route>
              <Route path="photo-upload" element={<ProtectedRoute><EmpPhotoUpload /></ProtectedRoute>}></Route>
          </Route>
          <Route path="customer/" element={<ProtectedRoute><Cust /></ProtectedRoute>}>
              <Route index element={<ProtectedRoute><CustCardList /></ProtectedRoute>}></Route>
              <Route path="add" element={<ProtectedRoute><CustAddForm /></ProtectedRoute>}></Route>
              <Route path="details" element={<ProtectedRoute><CustDetailsCard /></ProtectedRoute>}></Route>
              <Route path="update" element={<ProtectedRoute><CustUpdateForm /></ProtectedRoute>}></Route>
              <Route path="photo-upload" element={<ProtectedRoute><EmpPhotoUpload /></ProtectedRoute>}></Route>
          </Route>
          <Route path="orders" element={<ProtectedRoute><OrderList /></ProtectedRoute>}> </Route>
          <Route path="orders/update" element={<ProtectedRoute><OrdersUpdateForm /></ProtectedRoute>}> </Route>
          <Route path="activity" element={<ProtectedRoute><ActivityLog /></ProtectedRoute>}> </Route>
          <Route path="incexp" element={<ProtectedRoute><IncExp /></ProtectedRoute>}> </Route>
      </Route>

      
      <Route path="/employee" element={<ProtectedRoute><Appppppp /></ProtectedRoute>}> </Route>
      <Route path="/employee/update" element={<ProtectedRoute>< EmployeeUpdateForm/></ProtectedRoute>}> </Route>
      <Route path="/customer" element={<ProtectedRoute><CustMainPage /></ProtectedRoute>}> </Route>
      <Route path="*" element={<ErrorPage/>}></Route>
      <Route path="/error" element={<ErrorPage/>}></Route>

    </Routes>
  );
};

function ProtectedRoute({children}){
  let preJwt=localStorage.getItem("jwt");

  //if not logged-in, redirect to login
  if(!preJwt){
    return <Navigate to ="/login" replace={true}/>
  }
  return children;
}

export default Routers;
