import {
  SET_CURRENT_USER,
  SET_CURRENT_USER_INFO,
  CLEAR_CURRENT_USER_INFO,
  GET_ALL_TUTORS,
  GET_ALL_PARENTS,
  CLEAR_ALL_TUTORS,
  CLEAR_ALL_PARENTS,
} from "../actions/types";
import isEmpty from "../validation/is-empty";

const initialState = {
  isAuthenticated: false,
  user: {},
  userInfo: {},
  allTutors: null,
  allParents: null,
};

export default function (state = initialState, action) {
  switch (action.type) {
    case SET_CURRENT_USER:
      return {
        ...state,
        isAuthenticated: !isEmpty(action.payload),
        user: action.payload,
      };
    case SET_CURRENT_USER_INFO:
      return {
        ...state,
        userInfo: action.payload,
      };
    case GET_ALL_TUTORS:
      return {
        ...state,
        allTutors: action.payload,
      };
    case GET_ALL_PARENTS:
      return {
        ...state,
        allParents: action.payload,
      };
    case CLEAR_ALL_TUTORS:
      return {
        ...state,
        allTutors: null,
      };
    case CLEAR_ALL_PARENTS:
      return {
        ...state,
        allParents: null,
      };
    case CLEAR_CURRENT_USER_INFO:
      return {
        ...state,
        userInfo: null,
      };
    // case SET_CURRENT_USER:
    default:
      return state;
  }
}
