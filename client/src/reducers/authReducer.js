import { SET_CURRENT_USER, SET_CURRENT_USER_INFO } from "../actions/types";
import isEmpty from "../validation/is-empty";

const initialState = {
  isAuthenticated: false,
  user: {},
  userInfo: {}
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
    // case SET_CURRENT_USER:
    default:
      return state;
  }
}
