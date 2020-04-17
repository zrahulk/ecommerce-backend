import React, { Component } from 'react';
import {Link} from 'react-router-dom'
class Admin extends Component {
    render() {
        return (
            <div>
                <h1>you are in admin page</h1>
                <Link to ='to/logout'>Logout</Link>

            </div>
        );
    }
}

export default Admin;