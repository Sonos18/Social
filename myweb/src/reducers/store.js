import { combineReducers } from "redux";
import postReducer from "./PostReducer";
import { configureStore } from "@reduxjs/toolkit";
import thunk from "redux-thunk";
import logger from "redux-logger";
import userReducer from "./UserReducer";

const rootreducer = combineReducers({ posts: postReducer, users: userReducer });
const Store = configureStore({
  reducer: rootreducer,
  middleware: [thunk, logger],
});
export default Store;
