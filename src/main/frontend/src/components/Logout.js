import React, { Component } from 'react';
import {Link} from 'react-router-dom'
class Logout extends Component {
    render() {
        return (
            <div>
                you have Logout
                <br/>
                <Link to = "/">Login</Link>
            </div>
        );
    }
}

export default Logout;