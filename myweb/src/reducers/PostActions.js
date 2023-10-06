import { toast } from "react-toastify";
import { authApi, endpoints } from "../configs/APIS";

export const makeRequest = () => {
  return {
    type: "MAKE_REQUEST",
  };
};
export const setPosts = (posts) => ({
  type: "POSTS",
  payload: posts,
});
export const createPost = (post) => ({
  type: "CREATE_POST",
  payload: post,
});

export const readPost = (postId) => ({
  type: "READ_POST",
  payload: postId,
});

export const updatePost = (postId, updatedPost) => ({
  type: "UPDATE_POST",
  payload: { postId, updatedPost },
});
export const lockPost = (postId) => ({
  type: "LOCK_POST",
  payload: postId,
});
export const deletePost = (postId) => ({
  type: "DELETE_POST",
  payload: postId,
});

export const FunctionPosts = () => {
  return (dispatch) => {
    dispatch(makeRequest);
    authApi()
      .get(endpoints[`posts`])
      .then((res) => {
        const formattedPosts = res.data.map((post) => ({
          ...post,
          createAt: new Date(post.createAt).toLocaleString(),
          showComment: false,
          showPostSide: false,
          showUpdatePost: false,
        }));
        dispatch(setPosts(formattedPosts));
      })
      .catch((ex) => {
        console.info(ex);
      });
  };
};
export const FunctionUpdatePost = (id, formData) => {
  return (dispatch) => {
    authApi()
      .put(endpoints["post-detail"](id), formData)
      .then((res) => {
        const updatedPost = {
          ...res.data,
          createAt: new Date(res.data.createAt).toLocaleString(),
          showComment: false,
          showPostSide: false,
          showUpdatePost: false,
        };
        dispatch(updatePost(id, updatedPost));
      })
      .catch((ex) => {
        console.error(ex);
      });
  };
};
export const FunctionDeletePost = (id) => {
  return (dispatch) => {
    dispatch(makeRequest);
    authApi()
      .delete(endpoints["post-detail"](id))
      .then((res) => {
        if (res.status === 204) {
          dispatch(deletePost(id));
          toast.success("Delete Post successfull");
        }
      })
      .catch((error) => {
        if (error.response) {
          const status = error.response.status;
          if (status === 401)
            toast.error("You have not permission to delete this post");
          if (status === 400) toast.error("Post is not exits");
        } else console.error(error);
      });
  };
};
export const FunctionCreatePost = (formData) => {
  return (dispatch) => {
    dispatch(makeRequest);
    try {
      authApi()
        .post(endpoints["posts"], formData)
        .then((res) => {
          if (res.status === 201) {
            const newPost = {
              ...res.data,
              createAt: new Date(res.data.createAt).toLocaleString(),
              showComment: false,
              showPostSide: false,
              showUpdatePost: false,
            };
            dispatch(createPost(newPost));
          }
          toast.success("Create Post Successful");
        });
    } catch (error) {
      if (error.response) {
        const status = error.response.status;
        if (status === 401) {
          toast.error("You do not have permission to delete this post");
        } else if (status === 400) {
          toast.error("Post does not exist");
        }
      } else {
        console.error(error);
      }
    }
  };
};
export const FunctionLockPost = (postId) => {
  return (dispatch) => {
    dispatch(makeRequest);
    authApi()
      .put(endpoints[`lock`](postId))
      .then((res) => {
        if (res.status === 200) {
          dispatch(deletePost(postId));
          toast.success("Locked Post");
        }
      })
      .catch((error) => {
        if (error.response) {
          const status = error.response.status;
          if (status === 401)
            toast.error("You have not permission to delete this post");
          if (status === 400) toast.error("Post is not exits");
        } else console.error(error);
      });
  };
};
