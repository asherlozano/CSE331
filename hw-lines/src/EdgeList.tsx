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

import React, {Component} from 'react';

interface EdgeListProps {
    onChange(edges: string): any;
}

interface EdgeListState{
    text: []
    start: string
    end: string
    color: string
    selected: string

}

/**
 * A text field that allows the user to enter the list of edges.
 * Also contains the buttons that the user will use to interact with the app.
 */
class EdgeList extends Component<EdgeListProps, EdgeListState> {
    constructor(props: EdgeListProps) {
        super(props);
        this.state = {text: [], start: '', end: '', color: '', selected: ''}
    }


    // function that


    render() {

        //text
        let options: any[] = []
        let build: string
        for(let i = 0; i < this.state.text.length; i++){
            build = this.state.text[i]
            options.push(<option key = {i} value = {build}>{build} </option>)
        }

        //colors
        let colors: any[] = []
        let cl: string
        let c = ['red', 'blue', 'pink', 'green', 'yellow', 'black', 'brown', 'grey']
        for (let i = 0; i < c.length; i++) {
            cl = c[i]
            colors.push(
                <option key = {i} value = {cl}>{cl}</option>
            )
        }
        return (
            <div id="edge-list">
                Edges <br/>
                <textarea
                    rows={5}
                    cols={30}
                    onChange={(event) => {this.setState({start: event.target.value})}}
                    value = {this.state.start}
                /> <br/>
                <select
                    value = {this.state.end}
                    onChange = {(event) => {this.setState({end: event.target.value})}}>
            </select>
                <button onClick={() => {this.props.onChange(this.state.start + "\n" + this.state.end + "\n" + this.state.color);
                }}>Draw</button>
                <button onClick={() => {this.props.onChange("")
                }}>Clear</button>
                <button type="button" onClick={this.buttonClear}>
                    Reset</button>
            </div>
        );
    }
    buttonClear = () => {
        this.setState({start: "", end: "", color: ""});
        this.props.onChange("");
    }
}

export default EdgeList;
