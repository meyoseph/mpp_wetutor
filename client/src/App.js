import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import jwt_decode from 'jwt-decode';
import setAuthToken from './utils/setAuthToken';
import { setCurrentUser, logoutUser, getUserInfo } from './actions/authActions';
import { clearCurrentProfile } from './actions/profileActions';

import { Provider } from 'react-redux';
import PrivateRoute from './components/common/PrivateRoute';
import store from './store';


import Navbar from "./components/layout/Navbar";
import Landing from "./components/layout/Landing";
import CreateProfile from "./components/create-profile/CreateProfile";
import Profiles from './components/profiles/Profiles';
// import Profile from './components/profile/Profile';
import Dashboard from './components/dashboard/Dashboard';
import Footer from "./components/layout/Footer";
import Login from "./components/auth/Login";
import Register from "./components/auth/Register";
import "./App.css";


// Check for token 
if(localStorage.jwtToken){
  // Set auth token header auth
  setAuthToken(localStorage.jwtToken);
  // Decode token and get user info and expiration
  const decoded = jwt_decode(localStorage.jwtToken);

  // Get full user info
  store.dispatch(getUserInfo(decoded.sub));
  // Set user and isAuthenticated 
  store.dispatch(setCurrentUser(decoded));
  // Check for expired token
  const CurrentTime = Date.now() / 1000;
  if(decoded.exp < CurrentTime){
    // Logout user
    store.dispatch(logoutUser());
    // Clear current profile 
    store.dispatch(clearCurrentProfile());
    // Redirect to login
    window.location.href = '/login';
  }

}

class App extends Component {
  render() {
    return (
      <Provider store= { store }>
        <Router>
          <div className="App">
            <Navbar/>
            <Route exact path ="/" component={ Landing }/>
            <div className="container">
              <Route exact path ="/register" component={ Register }/>
              <Route exact path ="/login" component={ Login }/>
              <Route exact path ="/profiles" component={ Profiles }/>
              {/* <Route exact path ="/profile/:handle" component={ Profile }/> */}
              <Switch>
                <PrivateRoute exact path ="/dashboard" component={ Dashboard }/>
              </Switch>
              <Switch>
                <PrivateRoute exact path ="/create-profile" component={ CreateProfile }/>
              </Switch>
              {/* <Switch>
                <PrivateRoute exact path ="/edit-profile" component={ EditProfile }/>
              </Switch> */}
              {/* <Switch>
                <PrivateRoute exact path ="/add-experience" component={ AddExperience }/>
              </Switch> */}
              {/* <Switch>
                <PrivateRoute exact path ="/add-education" component={ AddEducation }/>
              </Switch> */}
              {/* <Switch>
                <PrivateRoute exact path ="/post/:id" component={ Post }/>
              </Switch> */}
              {/* <Switch>
                <PrivateRoute exact path ="/feed" component={ Posts }/>
              </Switch> */}
              {/* <Route exact path ="/not-found" component={ NotFound }/> */}
            </div>
            <Footer/>
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
