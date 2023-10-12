import { toast } from "react-toastify";
import { authApi, endpoints } from "../configs/APIS";

export const fetchUsers = () => {
  return {
    type: "FETCH_USERS",
  };
};
export const getUserList = (users,totalPage) => {
  return {
    type: "GET_USER_LIST",
    payload: {users,totalPage},
  };
};
export const deleteUser = (username) => {
  return {
    type: "DELETE_USER",
    payload: username,
  };
};
export const addUser = (user) => {
  return {
    type: "CREATE_USER",
    payload: user,
  };
};
export const updateUser = (userId, updatedUser) => {
  return {
    type: "UPDATE_USER",
    payload: { userId, updateUser },
  };
};

export const FetchUserList = (page) => {
  return (dispatch) => {
    dispatch(fetchUsers);
    authApi()
      .get(endpoints[`admin-users`],{params:{
        page:page,
      },})
      .then((res) => {
        dispatch(getUserList(res.data.usersDto,res.data.totalPage));
      })
      .catch((ex) => {
        console.info(ex);
      });
  };
};

export const FunctionDeleteUser = (username) => {
    return (dispatch) => {
        dispatch(fetchUsers);
        authApi()
          .delete(endpoints[`admin-users`],{params:{
            username:username,
          },})
          .then((res) => {
            if (res.status === 204) {
                dispatch(deleteUser(username));
                toast.success("Delete User successfull");
              }
          })
          .catch((ex) => {
            console.info(ex);
          });
      };
};

export const FunctionCreateUser = (data) => {
  return (dispatch) => {};
};

export const FunctionUpdateUser = (data, code) => {
  return (dispatch) => {};
};
