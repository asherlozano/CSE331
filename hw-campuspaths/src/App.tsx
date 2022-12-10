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

// Allows us to write CSS styles inside App.css, any styles will apply to all components inside <App />
import "./App.css";

interface AppState {
    routes: Array<Array<any>>,
}

/**
 * UW Campus Finding App
 */
class App extends Component<{}, AppState> {
    setState(arg0: { routes: any[][]; }) {
        throw new Error("Method not implemented.");
    } // <- {} means no props.
    constructor(props: any) {
        super(props);
        this.state = {
            routes: [],
        };
    }

    // if the query is empty, set the routes to be empty without asking the server
    beforeRequest = (value: string) => {
        if (value.length == 0) {
            this.setState({
                routes: []
            })
            return ;
        }
        this.makeRequest(value)
    }

// request to the server and ask for shortest path information
    makeRequest = async (value: string) => {
        let val = value.split("\n");
        let start = val[0];
        let end = val[1];
        let color = val[2];
        try {
            let tempResponse = fetch("http://localhost:4567/route-AB?start=" + start + "&end=" + end);
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
                routes: edge
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
            <div style={{
                backgroundImage: "url(" + "https://cdn.uconnectlabs.com/wp-content/uploads/sites/25/2021/01/UWCampus-BronzeW.jpg" + ")",
                backgroundPosition: 'center',
                backgroundSize: 'cover',
                backgroundRepeat: 'no-repeat'
            }}>
                <br/>
                <br/>

                    <h1 id="app-title">UW Campus Path Finding App</h1>
                <br/>
                <br/>
                        {/reset button/}
                        <div id = "reset" style = {{display: 'flex',
                            alignItems: 'center',
                            justifyContent: 'center'
                        }}>
                            <button onClick={this.refreshPage}>Click to Refresh!</button>
                        </div>
            </div>
        );
    }
}

export default App;