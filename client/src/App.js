import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import jwt_decode from "jwt-decode";
import setAuthToken from "./utils/setAuthToken";
import {
  setCurrentUser,
  logoutUser,
  getUserInfo,
  clearFullUserInfo,
  clearAllParents,
  clearAllTutors,
} from "./actions/authActions";
import { clearCurrentProfile } from "./actions/profileActions";

import { Provider } from "react-redux";
import AdminProtected from "./components/common/AdminProtected";
import ParentProtected from "./components/common/ParentProtected";
import TutorProtected from "./components/common/TutorProtected";
import PrivateRoute from "./components/common/PrivateRoute";
import store from "./store";

import NotFound from "./components/not-found/NotFound";
import Navbar from "./components/layout/Navbar";
import Landing from "./components/layout/Landing";
import CreateProfile from "./components/create-profile/CreateProfile";
import Profile from "./components/profile/Profile";
import EditProfile from "./components/edit-profile/EditProfile";
import SubscriptionForm from "./components/parent/SubscriptionForm";
import Footer from "./components/layout/Footer";
import Login from "./components/auth/Login";
import Register from "./components/auth/Register";
import AdminDashboard from "./components/admin/Dashboard";
import TutorDashboard from "./components/tutor/Dashboard";
import ParentDashboard from "./components/parent/Dashboard";
import { ToastProvider } from "react-toast-notifications";
import LandingProfiles from "./components/landing-profiles/LandingProfiles";
import "./App.css";

// Check for token
if (localStorage.jwtToken) {
  // Set auth token header auth
  setAuthToken(localStorage.jwtToken);
  // Decode token and get user info and expiration
  const decoded = jwt_decode(localStorage.jwtToken);

  // Get full user info
  store.dispatch(getUserInfo());
  // Set user and isAuthenticated
  store.dispatch(setCurrentUser(decoded));
  // Check for expired token
  const CurrentTime = Date.now() / 1000;
  if (decoded.exp < CurrentTime) {
    // Logout user
    store.dispatch(logoutUser());
    // Clear current profile
    store.dispatch(clearCurrentProfile());
    // Clear current user info
    store.dispatch(clearFullUserInfo());
    //Clear all parents
    store.dispatch(clearAllParents());
    // Clear all tutors
    store.dispatch(clearAllTutors());
    // Redirect to login
    window.location.href = "/login";
  }
}

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <div className="App">
            <Navbar />
            <Route exact path="/" component={Landing} />
            <div className="container">
              <Route exact path="/register" component={Register} />
              <Route exact path="/login" component={Login} />
              <Route exact path="/tutors" component={LandingProfiles} />
              {/* <Route exact path ="/profile/:handle" component={ Profile }/> */}
              <Switch>
                <AdminProtected
                  exact
                  path="/admin-dashboard"
                  component={AdminDashboard}
                />
              </Switch>
              <Switch>
                <ParentProtected
                  exact
                  path="/parent-dashboard"
                  component={ParentDashboard}
                />
              </Switch>
              <Switch>
                <TutorProtected
                  exact
                  path="/dashboard"
                  component={TutorDashboard}
                />
              </Switch>

              <Switch>
                <TutorProtected
                  exact
                  path="/create-profile"
                  component={CreateProfile}
                />
              </Switch>
              <Switch>
                <TutorProtected
                  exact
                  path="/edit-profile"
                  component={EditProfile}
                />
              </Switch>
              <ToastProvider>
                <Switch>
                  <ParentProtected
                    exact
                    path="/subscribe"
                    component={SubscriptionForm}
                  />
                </Switch>
              </ToastProvider>
              <ToastProvider>
                <Switch>
                  <PrivateRoute
                    exact
                    path="/profile/:tutorId"
                    component={Profile}
                  />
                </Switch>
              </ToastProvider>
              <Route exact path="/not-found" component={NotFound} />
            </div>
            <Footer />
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
