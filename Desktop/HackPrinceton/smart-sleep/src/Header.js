import React, { Component } from 'react';



class Header extends React.Component {
  render() {
    return (
      <div class="header" id="header">
        <header className="container">
          <h1 > Let's get you sleeping right!</h1> 
          <br/>
          <br/>
          <p>
            ... or keep scrolling to learn more about the science of sleep!
          </p>
          <br/>
          <br/>
          <button className="btnName"><a href="#main" className="link">When should I Zzzzz</a></button>
          <br/>
          <br/>
        <button className="btnName"><a href="#main" className="link">Let me see how I did</a></button>
        </header>
      </div>
    );
  }
}

export default Header;
