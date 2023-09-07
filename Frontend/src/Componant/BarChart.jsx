import React, {Fragment, useState} from 'react';
import axios from "axios"
import { Bar } from 'react-chartjs-2';
import {Chart as ChartJS} from "chart.js/auto"
import { useEffect } from 'react';




function BarChart() {
  let config={
    headers: {
      Authorization: `Bearer ${localStorage.getItem("jwt")}`,
    },
  }

  let [month, setMonth]=useState([]);
  let [income, setIncome]=useState([]);
  

  const makeAxiosCall=async ()=>{  
    var mon=[];
    var inc=[];
    try{
      
      const url="http://localhost:9090/incmexptally/past";
      let res=await axios.get(url,config);
      console.log(res);
        for(const dataObj of res.data){
          mon.push(dataObj.remark.substring(0,6));
          inc.push(parseInt(dataObj.amount));
        }
      setMonth(mon);
      setIncome(inc);
      }catch(err){
      // <Link to="/error-page"></Link>
      console.log(err);
    };
  }

  let [predict,setPredict]=useState(0);


  const Prediction = () => {    
    axios
      .get("http://localhost:9090/incmexptally/prediction",config)
      .then((response) => {
        setPredict(response.data);
        
      })
      .catch((error) => {
        alert(error);
      });
  };

  
  console.log("prediction");
  console.log(predict);



  useEffect(()=>{
    makeAxiosCall();
    Prediction();
  },[])

  
  console.log("month"+month);
  console.log("income"+income);

  const data = {
  labels: month,
  datasets: [
    {
      type:'bar',
      label: 'income',
      data:income,
      backgroundColor: 'rgba(251, 232, 166, 0.5) ',
      borderColor: 'rgba(251, 232, 166, 1)',
      borderWidth: 1,
      barThickness:50,
      hoverBackgroundColor:'rgba(48, 60, 108, 0.3) '
    },{
      type:'line',
      label:'Income Prediction',
      data:[predict, predict, predict,predict],
      backgroundColor: 'rgba(244, 151, 108, 1) ',
      borderColor: 'rgba(244, 151, 108, 1)',
    }
  ],
};
 
  return (
    <div style={{width:"100%"}}>
      <h3 className="text-center" style={{color:"rgba(244, 151, 108, 0.9)"}}>Income prediction for this month = {predict}</h3>
      <h4 className="text-center"> Units to be sold = {predict/500}</h4><br/><br/>
      <Bar data={data}/>
    </div>
  );
}

export default BarChart;