import { Link, useNavigate } from "react-router-dom";
import React, { useState } from "react";
import axios from "axios";



const SignIn = () => {
  const [user, setUser] = useState({username:"", password:""});
  const [isError, setIsError]=useState(false);
  let navigate=useNavigate();

  const handleChange = (evnt) => {
    let paramName = evnt.target.name;
    let paramValue = evnt.target.value;
    setUser((values) => ({ ...values, [paramName]: paramValue }));
  };

  const processLogin = async(evnt) => {
    try{
      evnt.preventDefault();
      evnt.stopPropagation();
      const url="http://localhost:9090/login";
      const response= await axios.post(url, user);
      if(response.status>=200 && response.status<300){
        localStorage.setItem("jwt",response.data.token);
      }
      
    }
    
    catch(error){
      console.log(error);
      setIsError(true);
      setTimeout(()=>setIsError(false), 2500);
    }   

    let config={
      headers: {
        Authorization: `Bearer ${localStorage.getItem("jwt")}`,
      },
    }
    console.log(localStorage.getItem("jwt"));
    const resp=await axios.get("http://localhost:9090/loginService/"+user.username, config);
    console.log(resp.data);
    localStorage.setItem("user", resp.data.id);
    localStorage.setItem("role", resp.data.role);
    
    if(resp.data.role=="ROLE_CUSTOMER"){
      navigate("/customer", {replace:true});
      const r=await axios.get("http://localhost:9090/customer/"+localStorage.getItem("user"), config);
      localStorage.setItem("name", r.data.custname);
      

    }else{
      const r=await axios.get("http://localhost:9090/employee/"+localStorage.getItem("user"), config);
      console.log(r);
      localStorage.setItem("name", r.data.ename);
      if(resp.data.role=="ROLE_ADMIN")
        navigate("/admin",{replace:true});
      else if(resp.data.role=="ROLE_EMPLOYEE")
        navigate("/employee",{replace:true});
    }
  };

  return (
    <>
    <div className="container" style={{height:"75vh"}}>
      <div className="row">
        <div className="col-6 border-right" >
        <img src={require("../Assets/images/home/login.jpg")} alt="Page Not Found" style={{width:"31vw"}}/>
      </div>
      <div className="col-6" style={{marginTop:"100px"}}>
      <div className="align-items-center">
        <form >
          <div style={styles.container}>
            <div className="mb-3" style={{ marginBottom: 20 }}>
              <label className="form-label">Username </label>
              <input
                type="text"
                name="username"
                className="form-control"
                placeholder="Enter username"
                onChange={handleChange}
                required
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Password </label>
              <input
                type="password"
                name="password"
                className="form-control"
                placeholder="Enter password"
                onChange={handleChange}
                required
              ></input>
            </div>
            {/* <div class="checkbox">
              <label>
                <input type="checkbox" /> Remember me
              </label>
            </div> */}
            
            {isError && 
              <div className="alert alert-warning">
                Invalid Credentials or Network Error!
              </div>
            }

            <div>
              <button
                type="submit"
                class="btn btn-primary"
                onClick={processLogin}
              >
                Login
              </button>
              <div>
                Don't have an account? <Link to="/contact"> Contact us</Link> to register your business with MUMS!
              </div>
            </div>
          </div>
        </form>
      </div>



         
        </div>
      </div>
      </div>

    </>
  );
};

const styles = {
  container: {
    width: 400,
    height: 300,
    padding: 20,
    position: "relative",
    top: 0,
    left: 0,
    right: 0,
    marginBottom: 20,
    marginTop: 20,
    margin: "auto",
    borderColor: "#db0f62",
    borderRadius: 10,
  },

  signinButton: {
    position: "relative",
    width: "50%",
    height: 40,
    backgroundColor: "#db0f62",
    color: "white",
    borderRadius: 5,
    border: "none",
    marginBottom: 20,
  },
  // container: {
  //   width: 500px;
  //   height: 250px;
  //   margin: 50px;
  //   outline: solid 1px black;
  // },

  // circle: {
  //   width: 50px;
  //   height: 50px;
  //   border-radius: 50%;
  //   background-color: black;
  // },
};

export default SignIn;
