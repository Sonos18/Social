import { combineReducers } from "redux";
import postReducer from "./PostReducer";
import { configureStore } from "@reduxjs/toolkit";
import thunk from "redux-thunk";
import logger from "redux-logger";

const rootreducer=combineReducers({posts:postReducer});
const Store=configureStore({reducer:rootreducer,middleware:[thunk,logger]})
export default Store;