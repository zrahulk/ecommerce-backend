import React from 'react';
import {withStyles} from '@material-ui/core'
import Login from '../Login'
let useStyles  = () => ({
    style : {
        width:"100%",
        height:"15%",
        backgroundColor:"green"
    }
})

class Header extends React.Component {

    render() {
        const {classes} = this.props
        return (
            <div className = {classes.style}>
                this is first class
                <Login />
            </div>
        );
    }
}

export default withStyles(useStyles)(Header);