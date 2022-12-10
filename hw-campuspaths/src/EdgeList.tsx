import React, {Component} from 'react';

interface EdgeListProps {
    onChange(edges: string): any;
}


interface EdgeState {
    buildings: []
    start: string
    end: string
    color: string
    selected: string
}

/**
 * A field that allows the user to enter all the edges.
 */
class EdgeList extends Component<EdgeListProps, EdgeState> {
    constructor(props: EdgeListProps) {
        super(props);
        this.state = {buildings: [], start: '', end: '', color: '', selected: ''}
    }

    // function that request server and return the building list
    makeRequest = async () => {
        try {
            let tempResponse = fetch("http://localhost:4567/buildings);
            let response = await tempResponse;
            if (!response.ok) {
                alert("The status is wrong! Expected: 200, Was: " + response.status);
                return;
            }
            let bds = await response.json();
            this.setState({
                buildings: bds
            })
        } catch (e) {
            alert("There was an error contacting the server.");
        }
    }

    componentDidMount() {
        this.makeRequest();
    }

//     render() {
//         // buildings option
//         let options: any[] = []
//         let bd: string
//         for (let i = 0; i < this.state.buildings.length; i++) {
//             bd = this.state.buildings[i]
//             options.push(
//                 <option key={i} value={bd}>{bd}</option>
//             )
//         }
//
//         let colors: any[] = []
//         let cl: string
//         let c = ['red', 'blue', 'pink', 'green', 'yellow', 'black', 'brown', 'grey']
//         for (let i = 0; i < c.length; i++) {
//             cl = c[i]
//             colors.push(
//                 <option key = {i} value = {cl}>{cl}</option>
//             )
//         }
//
//         return (
//             <div id="edge-list" >
//                     {/first building dropdown/}
//                         <p><strong>First building</strong></p>
//                         <select
//                             value = {this.state.start}
//                             onChange={(event)=>{this.setState({start: event.target.value})}}>
//                             <option>Please select a building</option> {this.options}
//                         </select> <br/>
//                     {/second building dropdown/}
//                         <p><strong>Second building</strong></p>
//                         <select
//                             value = {this.state.end}
//                             onChange={(event)=>{this.setState({end: event.target.value})}}>
//                             <option>Please select a building</option> {options}
//                         </select>
//                     {/color dropdown/}
//                         <p><strong>Color</strong></p>
//                         <select
//                             value = {this.state.color}
//                             onChange={(event)=>{this.setState({color: event.target.value})}}>
//                             <option>Please select a color</option> {colors}
//                         </select><br/>
//                     {/draw and clear button/}
//                         <button onClick={() => {
//                             this.props.onChange(this.state.start + "\n" + this.state.end + "\n" + this.state.color);
//                         }}>Draw</button>
//                         <button onClick={() => {
//                             this.props.onChange("");
//                         }}>Clear</button>
//                         <button type="button" onClick={this.handleClear}>Reset</button>
//             </div>
//         );
//     }
//
//     handleClear = () => {
//         this.setState({start: "", end: "", color: ""});
//         this.props.onChange("");
//     }
// }
    render() {

        //text
        let options: any[] = []
        let build: string
        for(let i = 0; i < this.state.buildings.length; i++){
            build = this.state.buildings[i]
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