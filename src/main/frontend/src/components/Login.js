import React from 'react';
import {Redirect} from 'react-router-dom';
class Login extends React.Component {
    constructor(props) {
        super(props)
        
        this.state = {
            username:"",
            password:"",
            loggedIn:false,
        }
    }

    onChange = (e)=> {
         this.setState({
                [e.target.name]:[e.target.value]
            })
    }

    submitForm =(e)=> {
        e.preventDefault();
        const {username, password} = this.state
        console.log(username)
        console.log(password)
        if(username=='a' && password=='b') {
            console.log("hello")
            this.setState({
                loggedIn:true
            })
        }

    }
    render() {
        if(this.state.loggedIn) {
            return <Redirect to = "/admin" />
        }
        return (
            <div style = {{textAlign:"center"}}>
                 <h1> Login Page</h1>
                 <form onSubmit = {this.submitForm}>
                     User:
                     <input type = "text" 
                     name="username"
                     placeholder = "username" 
                     value = {this.state.username} 
                     onChange = {(e)=>this.onChange(e)}/>
                     <br/>
                     Password: <input type = "password" placeholder = "password"
                     name = "password"
                        value = {this.password}
                        onChange = {(e)=>this.onChange(e)} />
                        <br/>
                        <input type = "submit"/>
                 </form>
            </div>
        );
    }
}

export default Login;