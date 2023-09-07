import React from "react";
import Common from "../Shared/Common";
import web from "../Assets/images/home/image1.jpeg";
import NavBarNew from "../Componant/NavBarNew";

function About() {
  return (
    <>
    <NavBarNew/>
      <Common
        name="Welcome to the world of"
        imgsrc={web}
        visit="/contact"
        btname="Contact Us"
      />
      <div className="container">
        <div className="row border-box">
          <div className="col col-12 col-md-5"> 
            <div className="video-responsive align-center">
              <iframe
                width="100%"
                height="400"
                src={`https://www.youtube.com/embed/TtdHWBoCU4I?autoplay=1&mute=1`}
                
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                allowFullScreen
                title="Embedded youtube"
              />
            </div>
          </div>
          <div className="col col-12 col-md-7">
          <div className="box" style={{width: "auto",  textAlign: "justify"}}>
                
                <p>"Production management software" - this is a phrase you probably typed in Google searching for a
                    solution to your production problems but soon were surprised to find thousands of search results.
                    Names such as ERP for small manufacturers, manufacturing ERP software, and production scheduling
                    software free popped out in hundreds of searches.
                </p>
                <p>That’s why here, we will help you to pick <b>the most suitable tool for production planning</b>, for
                    your
                    shop floor. You will register work with ease, get rid of paper orders, and last but not least,
                    improve efficiency and gain control over processes on your production floor.
                </p>
                <p>
                    If your business software doesn’t provide the transparency you need to manage holistically across
                    the organization and take advantage of Web-based and other technology innovations like these, it’s
                    time to consider a change.
                </p>
                <p>
                    <b>Manufacturing Unit Monitoring System (MUMS)</b> will enable you to view and
                    control events throughout your entire business process chain – from suppliers to the production
                    processes to delivery and customer service. To maximize efficiency and speed, MUMS can further
                    connect and share data with other systems in your production environment.
                </p>
            </div> </div>
            
          </div>
        </div>
      
    </>
  );
}

export default About;
