import React from "react";
import Card from "../Componant/Card";
import production from "../Assets/images/home/production.jpg";
import incexptally from "../Assets/images/home/incexptally.jpg";
import prediction from "../Assets/images/home/prediction.jpg";      
import easyui from "../Assets/images/home/easyui.jpg";
import salarycalc from "../Assets/images/home/salarycalc.jpg";
import NavBarNew from "../Componant/NavBarNew";
import image4 from "../Assets/images/home/image4.jpeg"

function Service() {
  return (
    <>
    <NavBarNew/>
      <div className="my-5">
        <h1 className="text-center">Our Services</h1>
      </div>

      <div className="container-fluid mb-5">
        <div className="row">
          <div className="col-10 mx-auto">
            <div className="row gy-4">
              <Card title="Production Scheduling" imgsrc={production} 
              content="Automated, fixed-unit production request on order basis. Reduces the need for extensive inventories for storage due to unplanned routine production. A complete log is maintained future reference."/>
              <Card title="Tally Income /Expenditure" imgsrc={incexptally} 
              content="MUMS allows you to calculate total salary of employee based on the given value of basic salary, HRA and sales commission at the click of a button. The monthly expense of each month can be add to find the net profit/loss"/>
              <Card title="Income Prediction" imgsrc={prediction} 
              content="Income prediction for the coming year is made for a proper goal-based approach for growing. Prediction is based on the automatic targets given to salesmen, to have a proper quantified goal for the organisation"/>
              <Card title="Business Analytics" imgsrc={image4} 
              content="Rich graphical representation for better understanding.  Admins can see the chars of income to track growth. Performance of salesmen can be tracked as well"/>
              <Card title="Easy To Use User Interface" imgsrc={easyui} 
              content="Easy to use interface design focuses on anticipating what users might need to do and ensuring that the interface has elements that are easy to access, understand, and use"/>
              <Card title="Salary Calculation" imgsrc={salarycalc}
              content="Salary of employees are made automatically based on their basic salary, city and holidays taken. Commission for salesman is calculated based on their work experience and orders  "/>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default Service;
