import React, {Fragment, useState} from 'react';
import axios from "axios"
import { Doughnut } from 'react-chartjs-2';
import {Chart as ChartJS} from "chart.js/auto"
import { useEffect } from 'react';


function DonutChart() {

  let [salesman, setsalesman]=useState([]);
  let [unit, setUnit]=useState([]);


  const makeAxiosCall=async ()=>{  
    var lab=[];
    var val=[];
    try{
      let config={
        headers: {
          Authorization: `Bearer ${localStorage.getItem("jwt")}`,
        },
      }
      const url="http://localhost:9090/order/salesman/all";
      let res=await axios.get(url, config);
      console.log(res);
      for(var property in res.data) {
        lab.push(property);
        val.push(res.data[property]);
    }
        setsalesman(lab);
        setUnit(val);
      }catch(err){
      // <Link to="/error-page"></Link>
      console.log(err);
    };
  }



  useEffect(()=>{
    makeAxiosCall();
  },[])

  
  console.log("salesman"+salesman);
  console.log("unit"+unit);

  const data = {
  labels: salesman,
  datasets: [
    {
      label: 'Units Sold',
      data:unit,
      backgroundColor: [
        'rgba(210, 253, 255, 0.7) ',
        'rgba(48, 60, 108, 0.5)'
      ],
      hoverBackgroundColor:'rgba(251, 232, 166, 0.2) ',
      borderWidth:6,

    },
  ],
};
 
  return (
    <div style={{width:"100%"}}>
      <Doughnut  data={data}/>
    </div>
  );
}

export default DonutChart;