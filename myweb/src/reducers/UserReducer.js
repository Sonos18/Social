// userReducer.js
const initialState = {
  users: [],
  totalPage:0,
  loading: false,
};
const userReducer = (state = initialState, action) => {
  switch (action.type) {
    case "FETCH_USERS":
      return {
        ...state,
        loading: true,
      };
    case "GET_USER_LIST":
      return {
        ...state,
        loading: false,
        users: action.payload.users,
        totalPage:action.payload.totalPage,
      };
    case "CREATE_USER":
      return {
        ...state,
        loading: false,
        users: [action.payload, ...state.users],
      };
    case "UPDATE_USER":
      const { userId, updatedUser } = action.payload;
      const updatedUsers = state.users.map((user) => {
        if (user.id === userId) {
          return {
            ...user,
            ...updatedUser,
          };
        }
        return user;
      });
      return {
        ...state,
        users: updatedUsers,
      };
    case "DELETE_USER":
      return {
        ...state,
        users: state.users.filter((user) => user.username !== action.payload),
      };
    default:
      return state;
  }
};
export default userReducer;
