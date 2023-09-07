import React from "react";
import BarChart from "../Componant/BarChart";
import DonutChart from "../Componant/DonutChart";


function AdminDashBoard() {
  return (
    <>
      <div className="row">
        <div className="col col-8 mx-auto">
          <BarChart/>
          <h3 className="text-center">Last 4 Months Income</h3>
          <br/><br/><br/>
        </div>
      </div>
      <div className="row">
        <div className="col col-5 mx-auto">
          <div  className="align-items-center">
            <DonutChart/>
            <h3 className="text-center">Total Orders of All Salesmen</h3>
            <br/><br/><br/>
          </div>
        </div>
      </div>
    </>
  );
}

export default AdminDashBoard;
