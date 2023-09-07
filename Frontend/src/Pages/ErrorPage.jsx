import React from "react";

//import image4 from "../Assets/images/home/error.jpg";


function ErrorPage() {
    return(
        <>
            <div className="container">
                <div className="row mx-auto">
                    <div className="col-6  mx-auto">
                        <img src={require("../Assets/images/home/error.jpg")} alt="Page Not Found" style={{width:"600px"}}/>
                        <h1 className="text-center">Page Not Found</h1>
                    </div>
                </div>
            </div>
        </>
    );
}

export default ErrorPage;