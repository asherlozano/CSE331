/*
 * Copyright (C) 2022 Kevin Zatloukal and James Wilcox.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Autumn Quarter 2022 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

import React, { Component } from "react";
import EdgeList from "./EdgeList";
import Map from "./Map";

// Allows us to write CSS styles inside App.css, any styles will apply to all components inside <App />
import "./App.css";
import edgeList from "./EdgeList";

interface AppState {
    edges: Array<Array<any>>
}

class App extends Component<{}, AppState> { // <- {} means no props.

  constructor(props: any) {
    super(props);
    this.state = {
      // TODO: store edges in this state

        edges: [],
    };
  }

  beforeRequest = (value:string)=> {
      if(value.length == 0) {
          this.setState({
              edges: []
          })
          return ;
      }
      this.makeRequest(value)
  }

    makeRequest = async (value: string) => {
        let val = value.split("\n");
        let start = val[0];
        let end = val[1];
        let color = val[2];
        try {
            let tempResponse = fetch("http://localhost:4567/route-A-to-B?start=" + start + "&end=" + end);
            let response = await tempResponse;
            if (!response.ok) {
                //alert("The status is wrong! Expected: 200, Was: " + response.status);
                alert("Please select buildings!");
                return;
            }
            if (color == "") {
                alert("Please select color!");
                return;
            }
            let text = await response.json();
            let paths = text.path;
            let edge = [];
            // iterate through the paths and store in the edge
            for (let i = 0; i < paths.length; i++) {
                let x1 = paths[i].start.x;
                let y1 = paths[i].start.y;
                let x2 = paths[i].end.x;
                let y2 = paths[i].end.y;
                let path = [x1, y1, x2, y2, color];
                edge.push(path);
            }
            // update the route with edge
            this.setState({
                edges: edge
            });
        } catch (e) {
            alert("There was an error contacting the server.");
        }
    }

    // a function that can reset all elements
    refreshPage() {
        window.location.reload(false);
    }


  render() {
    return (
      <div>
        <h1 id="app-title">University of Washington Campus Path App</h1>
        <div>
          {/* TODO: define props in the Map component and pass them in here */}
          <Map
              input = {this.state.edges}
          />
        </div>
        <EdgeList
          onChange={(value) => {
              this.beforeRequest(value)
            // TODO: Modify this onChange callback to store the edges in the state
            console.log("EdgeList onChange", value);
          }}

        />
          <div id = "reset" style = {{display: 'flex',
              alignItems: 'center',
              justifyContent: 'center'
          }}>
              <button onClick={this.refreshPage}>Refresh the page!</button>
          </div>
      </div>
    );
  }
}

export default App;
