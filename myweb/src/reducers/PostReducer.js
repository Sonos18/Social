// postReducer.js
const initialState = {
  posts: [],
  loading: true,
};

const postReducer = (state = initialState, action) => {
  switch (action.type) {
    case "MAKE_REQUEST":
      return {
        ...state,
        loading: true,
      };
    case "POSTS":
      return {
        ...state,
        loading: false,
        posts: action.payload,
      };
    case "CREATE_POST":
      return {
        ...state,
        loading: false,
        posts: [action.payload, ...state.posts],
      };
    case "READ_POST":
      // Xử lý đọc bài viết theo ID
      return state;
      case "LOCK_POST":
        const lockedPostId = action.payload;
        const lockPost = state.posts.map((post) => {
          if (post.id === lockedPostId) {
            return {
              ...post,
              locked: !post.locked,
            };
          }
          return post;
        });
        return {
          ...state,
          posts: lockPost,
        };
    case "UPDATE_POST":
      const { postId, updatedPost } = action.payload;
      const updatedPosts = state.posts.map((post) => {
        if (post.id === postId) {
          return {
            loading: false,
            ...post,
            ...updatedPost,
          };
        }
        return post;
      });
      return {
        loading: false,
        ...state,
        posts: updatedPosts,
      };
    case "DELETE_POST":
      return {
        loading: false,
        ...state,
        posts: state.posts.filter((post) => post.id !== action.payload),
      };
    default:
      return state;
  }
};

export default postReducer;
