import React from "react";

const Footer = () => {
  return (
    <div id="container" style={{ marginTop: "30px" }}>
      <div id="part1">
        <div id="companyinfo">
          {" "}
          <a id="sitelink" href="/#">
            WeTutor
          </a>
          <p id="title">You can get the best tutors </p>
          <p id="detail">Detail goes here. </p>
        </div>
        <div id="explore">
          <p id="txt1">Why WeTutor</p>{" "}
          <a className="link" href="/#">
            Quick
          </a>{" "}
          <a className="link" href="/#">
            Reliable
          </a>{" "}
          <a className="link" href="/#">
            Cheap
          </a>{" "}
          <a className="link" href="/#">
            Secure
          </a>
        </div>
        <div id="visit">
          <p id="txt2">Visit</p>
          <p className="text">1000 N, 4th Street </p>
          <p className="text">Fairfield, Iowa </p>
          <p className="text">Phone: +(1) 6411-234 </p>
        </div>
        <div id="legal">
          <p id="txt2">Legal</p>{" "}
          <a className="link1" href="/#">
            Terms and Conditions
          </a>{" "}
          <a className="link1" href="/#">
            Private Policy
          </a>
        </div>
        <div id="subscribe">
          {/* <p id="txt4">Subscribe to US</p>
          <form>
            {" "}
            <input id="email" type="email" placeholder="Email" />{" "}
          </form>{" "}
          <a className="waves-effect waves-light btn">Subscribe</a> */}
          <p id="txt5">Connect With US</p>{" "}
          <i className="fab fa-facebook-square social fa-2x"></i>{" "}
          <i className="fab fa-linkedin social fa-2x"></i>{" "}
          <i className="fab fa-twitter-square social fa-2x"></i>
        </div>
      </div>
      <div id="part2">
        <p id="txt6">
          copyright &copy; {new Date().getFullYear() + " "}
          App Name - All right reserved
        </p>
      </div>
    </div>
  );
};

export default Footer;
