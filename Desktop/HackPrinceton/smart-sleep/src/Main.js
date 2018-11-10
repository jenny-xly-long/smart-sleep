import React from 'react';
import Clock from 'react-live-clock';

class Main extends React.Component {

    constructor(){
        super()
        this.state={
            buttonPressed:false
        }
    }
    handleClick = () =>{
        this.setState({buttonPressed:!this.state.buttonPressed})
    }
    render() {
      return (
          <div class="header" id="main">
              <div class="container">
                          <h1>When should I sleep</h1>
                          <button className="btnName" onClick={this.handleClick}>Click before you sleep</button>
                          { this.state.buttonPressed ? <Clock format={'HH:mm:ss'} ticking={true} timezone={'US/Eastern'} /> : null }
                          {/* <Clock format={'HH:mm:ss'} ticking={true} timezone={'US/Eastern'} /> */}
                      </div>
                  </div>                  
      );
    }
}

export default Main;