import React from "react";
import { View, Button } from "react-native";
import NavBarNew from "../Componant/NavBarNew";

// function onClickButton(event) {
//   event.preventDefault();
// }

// function Contact() {
//   return (
//     <>
//     <NavBarNew/>
//       <div className="my-5">
//         <h1 className="text-center">Contact Us</h1>
//       </div>

//       <div className="container contact-div">
//         <div className="row">
//           <div className="col-md-6 col-10 mx-auto">
//             <form>
//               <div className="form-group">
//                 <label for="exampleFormControlInput1">Enter Full Name</label>
//                 <input
//                   type="text"
//                   className="form-control"
//                   id="exampleFormControlInput1"
//                   placeholder="Enter Your Name"
//                 />
//               </div>
//               <div className="form-group">
//                 <label for="exampleFormControlInput1">Enter Company Name</label>
//                 <input
//                   type="text"
//                   className="form-control"
//                   id="exampleFormControlInput1"
//                   placeholder="Enter Your Name"
//                   required
//                 />
//               </div>
//               <div className="form-group pt-3">
//                 <label for="exampleFormControlInput1">Contact Number</label>
//                 <input
//                   type="text"
//                   className="form-control"
//                   id="exampleFormControlInput1"
//                   placeholder="+91-"
//                   required
//                 />
//               </div>
//               <div className="form-group pt-3">
//                 <label for="exampleFormControlInput1">Email address</label>
//                 <input
//                   type="email"
//                   className="form-control"
//                   id="exampleFormControlInput1"
//                   placeholder="name@example.com"
//                   required
//                 />
//               </div>

//               <div className="form-group pt-3">
//                 <label for="exampleFormControlTextarea1">
//                   Type your Message Here
//                 </label>
//                 <textarea
//                   className="form-control"
//                   id="exampleFormControlTextarea1"
//                   rows="3"
//                   placeholder="Message"
//                   required
//                 ></textarea>
//               </div>

//               <View style={{ width: "80%", margin: 20}}>
//                 <button 
//                   onClick={onClickButton}
//                   className="btn btn-lg ml-5 block"
//                   type="submit"
//                 >
//                   Submit form
//                 </button>
//               </View>
//             </form>
//           </div>
//         </div>
//       </div>
//     </>
//   );
// }

// export default Contact;


import { useRef, useState } from "react";
import { EmailJSResponseStatus } from "emailjs-com";
import * as emailjs from "emailjs-com";
import { useEffect } from "react";
import { useNavigate } from "react-router";
const SpeechRecognition =
  window.SpeechRecognition || window.webkitSpeechRecognition;
const mic = new SpeechRecognition();

mic.continuous = true;
mic.interimResults = true;
mic.lang = "en-US";

function Contact() {
  let formRef = useRef();
  let navigate=useNavigate();

  const [isListening, setIsListening] = useState(false);
  const [note, setNote] = useState(null);
  const [savedNotes, setSavedNotes] = useState([]);

  useEffect(() => {
    handleListen();
  }, [isListening]);

  const handleListen = () => {
    if (isListening) {
      mic.start();
      mic.onend = () => {
        console.log("continue..");
        mic.start();
      };
    } else {
      mic.stop();
      mic.onend = () => {
        console.log("Stopped Mic on Click");
      };
    }
    mic.onstart = () => {
      console.log("Mics on");
    };

    mic.onresult = (event) => {
      const transcript = Array.from(event.results)
        .map((result) => result[0])
        .map((result) => result.transcript)
        .join("");
      console.log(transcript);
      setNote(transcript);
      mic.onerror = (event) => {
        console.log(event.error);
      };
    };
  };

  const handleSaveNote = () => {
    setSavedNotes([...savedNotes, note]);
    setNote("");
  };
  const sendEmail = (e) => {
    e.preventDefault();

    emailjs
      .sendForm(
        "service_c3uy1gi",
        "template_oru64up",
        formRef.current,

        "1RXwECgNzFgwAGBEf"
      )
      .then(
        (result) => {
          console.log(result);
        },
        (error) => {
          //navigate("*",{replace:true});
        }
      );
  };

  return (
    <>
    <NavBarNew/>
    <div className="container" style={{height:"70vh"}}>
      <div className="row">
        <div className="col-7 border-right" style={{marginTop:"100px"}}>
        <form
          name="contact-form"
          id="contact-form"
          ref={formRef}
          onSubmit={sendEmail}
        >
          <div className="row">
            <div className="col-lg-6">
              <div className="form-group mt-2">
                <input
                  name="name"
                  id="name"
                  type="text"
                  className="form-control"
                  placeholder="Your name* "
                  style={{ border: "2px solid lightGray" }}
                  required
                />
              </div>
            </div>
            <div className="col-lg-6">
              <div className="form-group mt-2">
                <input
                  name="email"
                  id="email"
                  type="email"
                  className="form-control"
                  placeholder="Your email*"
                  style={{ border: "2px solid lightGray" }}
                  required
                />
              </div>
            </div>
          </div>

          <div className="row">
            <div className="col-lg-12">
              <div className="form-group mt-2">
                <input
                  type="text"
                  className="form-control"
                  id="subject"
                  name="subject"
                  placeholder="Your Subject.."
                  style={{ border: "2px solid lightGray" }}
                  required
                />
              </div>
            </div>
          </div>

          <div className="row">
            <div className="col-lg-12">
              <div className="form-group mt-2">
                <textarea
                  name="message"
                  id="message"
                  rows="4"
                  value={note}
                  className="form-control"
                  placeholder="Your Message..."
                  style={{ border: "2px solid lightGray" }}
                />
                {isListening ? <span>🎙️</span> : <span>🛑🎙️</span>}
                <button className="btn" onClick={() => setIsListening((prevState) => !prevState)}>
                  Start/Stop
                </button>
              </div>
            </div>
          </div>

          <div className="row">
            <div className="col-lg-12 text-right">
              <input
                type="submit"
                id="submit"
                name="send"
                className="submitBnt btn btn-custom"
                value="Send Message" 
              />
              <div id="simple-msg" />
            </div>
          </div>
        </form>
        </div>
        <div className="col-5">
          <img src={require("../Assets/images/home/contactus.jpg")} alt="Page Not Found" style={{width:"30vw"}}/>
        </div>
      </div>
    </div>
    <div>
          <p className="text-center">Contact Number: 9685601935</p>  
          <p className="text-center">E-mail: mums7407@gmail.com</p>             
    </div>
    </>
  );
}

export default Contact;
