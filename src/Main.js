import React from 'react';
import Time from 'react-time';
import Clock from 'react-live-clock';

class Main extends React.Component {

    constructor(){
        super()
        this.state={
            buttonPressed:false,
            alarmTimes: [],
            string
        }
    }
    handleClickSleep = () =>{
        this.setState({buttonPressed:!this.state.buttonPressed})
        var times = []
        var sleep = new Date()
        for(var i=1; i<=5; i++){
            times.push(addMinutes(sleep,105*i));
        }
        this.setState({alarmTimes: times})
    }

    handleClickAlarm =() =>{
        this.setState({buttonPressed:!this.state.buttonPressed})
        var wake = new Date()
        this.setState({string:"Good Night!"})
    }

  
    render() {
      return (
          <div class="header" id="main">
              <div class="container">
              <h1>Pick an alarm time.</h1>
                    <button className="btnName" onClick={this.handleClickSleep}>Click before you sleep</button>
                          {/* { this.state.buttonPressed ? <Clock format={'HH:mm:ss'} ticking={true} timezone={'US/Eastern'} /> : null } */}
                          
                          <button onClick={this.handleClickAlarm}>{this.state.alarmTimes[0]}</button>
                         
                          <button onClick={this.handleClickAlarm}>{this.state.alarmTimes[1]}</button>
                          <button onClick={this.handleClickAlarm}>{this.state.alarmTimes[2]}</button>
                          <button onClick={this.handleClickAlarm}>{this.state.alarmTimes[3]}</button>
                          <button onClick={this.handleClickAlarm}>{this.state.alarmTimes[4]}</button>
                      </div>
                  </div>                  
      );
    }
}

var addMinutes = function(d, minutes){
    return (new Date(d.getTime() + minutes*60000)).toLocaleTimeString()
}

export default Main;