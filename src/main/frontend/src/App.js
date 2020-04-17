import React from 'react';

import './App.css';
import {Switch, Route} from 'react-router-dom'
import Login from './components/Login'
import Logout from './components/Logout'
import Admin from './components/Admin'
import Header from './components/header/Header';


function App() {
  return (

  //   <Switch>
  //     <Route exact path = '/' component={Login}/>
  //     <Route exact path ='/admin' component={Admin}/>
  //     <Route exact path = '/logout' component = {Logout}/>

  // </Switch>
  <Header/>
  );
}

export default App;
