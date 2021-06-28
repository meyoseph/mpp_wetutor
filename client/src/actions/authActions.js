import axios from 'axios';
import { CLEAR_ERRORS, GET_ERRORS, SET_CURRENT_USER, SET_CURRENT_USER_INFO, CLEAR_CURRENT_USER_INFO } from './types';
import setAuthToken from '../utils/setAuthToken';
import jwt_decode from 'jwt-decode';
// Register User
export const registerUser = ( userData, history ) => dispatch => {
    axios.post('api/registration', userData)
        .then(res => history.push('/login'))
        .catch(err => dispatch ({
            type: GET_ERRORS,
            payload: err.response.data
        })
    ) ;
};

// Login - Get User Token 
export const loginUser = userData => dispatch => {
    axios.post('api/authenticate', userData)
    .then(res =>{
        // Save to localstorage 
        const { token } = res.data;
        localStorage.setItem('jwtToken', token);
        // Set token to Auth  header 
        setAuthToken(token);
        // Decode token to get user data
        const decoded = jwt_decode(token);
        // Set current user 
        dispatch(setCurrentUser(decoded));
        dispatch({
            type: CLEAR_ERRORS
        })
    })
    .catch(err => dispatch ({
        type: GET_ERRORS,
        payload: err.response.data
    }));
};
// Set logged in user 
export const setCurrentUser = ( decoded ) => {
    return {
        type: SET_CURRENT_USER,
        payload: decoded
    }
}

// Get full logged in user info
export const getUserInfo  = userEmail => dispatch => {
    axios.post('/api/current', {email: userEmail})
        .then(res => dispatch({
            type: SET_CURRENT_USER_INFO,
            payload: res.data
        })
        )
        .catch(err => dispatch ({
            type: SET_CURRENT_USER_INFO,
            payload: null
        })
    ) ;
};


// Log user out 
export const logoutUser = () => dispatch => {
    // Remove token from localStorage 
    localStorage.removeItem('jwtToken');
    // Remove auth header for future requests
    setAuthToken(false);
    // Set current user to {} which will set isAuthenticated to false 
    dispatch(setCurrentUser({}))

}

// Clear Profile
export const clearFullUserInfo = () => {
    return {
      type: CLEAR_CURRENT_USER_INFO,
    };
  };