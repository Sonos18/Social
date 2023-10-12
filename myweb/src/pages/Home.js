import { useEffect, useState } from "react";
import Apis, { authApi, endpoints } from "../configs/APIS";
import PostPopup from "../component/PostPopup";
import { Link, useNavigate } from "react-router-dom";
import { MyUserContext } from "../App";
import { useContext } from "react";
import Like from "../component/Like";
import Comment from "../component/Comment";
import { toast } from "react-toastify";
import PostOption from "../component/PostOption";
import { useDispatch, useSelector } from "react-redux";
import { FunctionPosts, updatePost } from "../reducers/PostActions";
import Loading from "../component/Loading";
import NavPage from "../component/NavPage";

const Home = ({ searchTerm }) => {
  const [username, setUserName] = useState(null);
  const [user, dispatch] = useContext(MyUserContext);
  const [comments, setComments] = useState([]);
  const [content, setContent] = useState(null);
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);
  const dispatchh = useDispatch();
  const [isLoading, setIsLoading] = useState(false);
  const posts = useSelector((state) => state.posts.posts);
  const totalPage = useSelector((state) => state.posts.totalPage);
  const [page, setPage] = useState(0);
  useEffect(() => {
    if (user === null) navigate("/login");
    else {
      dispatchh(FunctionPosts(page));
      setIsLoading(false);
    }
  }, [dispatchh, page]);
  const onButtonClick=(i)=>{
    setPage(i-1);
  }
  if (isLoading) {
    return <div>Loading...</div>;
  }
  // useEffect(() => {
  //   if (user !== null) setUserName(user.username);
  //   const loadPosts = async () => {
  //     try {
  //       let res = await authApi().get(endpoints["posts"]);
  //       const formattedPosts = res.data.map((post) => ({
  //         ...post,
  //         createAt: new Date(post.createAt).toLocaleString(),
  //         showComment: false,
  //         showPostSide: false,
  //       }));
  //       setPosts(formattedPosts);
  //     } catch (ex) {
  //       console.error(ex);
  //     }
  //   };
  //   loadPosts();
  // }, []);
  //Require
  //ShowComment && LoadComment
  //posts{list(post=>showcomment)}
  const handleShowComment = (postId, isShow) => {
    console.info(postId);
    const loadComment = async (postId) => {
      try {
        let res = await authApi().get(endpoints[`comment`](postId));
        setComments(res.data);
      } catch (ex) {
        console.error(ex);
      }
    };
    dispatchh(updatePost(postId, { showComment: !isShow }));
    // setPosts(com);
    loadComment(postId);
  };
  const handleShowOption = (postId, isShow) => {
    dispatchh(updatePost(postId, { showPostSide: !isShow }));
  };
  // setPosts(com);
  //PostPopUp
  const handleOpen = () => {
    setIsOpen(true);
  };
  const handleLoading = () => {
    setIsLoading(!isLoading);
  };
  const handleClose = () => setIsOpen(false);

  const handleUpdatePost = (postId, isShow) => {
    dispatchh(
      updatePost(postId, { showUpdatePost: isShow, showPostSide: false })
    );
  };
  //Submit Comment
  const handleSubmit = (postId) => {
    const process = async (postId) => {
      try {
        let formData = new FormData();
        formData.append("postId", postId);
        formData.append("content", content);
        let { data } = await authApi().post(
          endpoints[`comment`](postId),
          formData
        );
        console.info(data);
        setComments([data, ...comments]);
        setContent("");
      } catch (ex) {
        console.error(ex);
      }
    };
    process(postId);
  };

  //Filter Posts

  // useEffect(() => {
  //   if (!searchTerm) {
  //     setFilteredPosts(posts);
  //   } else {
  //     const lowercaseSearchValue = searchTerm.trim().toLowerCase();
  //     const filtered = posts.filter((post) =>
  //       post.hashtags.some(
  //         (h) =>
  //           h.toLowerCase().includes(lowercaseSearchValue) ||
  //           post.usersDto.username.toLowerCase().includes(lowercaseSearchValue)
  //       )
  //     );
  //     setFilteredPosts(filtered);
  //   }
  // }, [searchTerm, posts]);
  return (
    <>
      {posts &&
        posts.map((p) => {
          return (
            <>
              <div className="mt-4 mb-1 m-auto overflow-hidden rounded-lg shadow-lg cursor-pointer h-90 w-60 md:w-1/2">
                <div to="#" className="block w-full h-full" key={p.id}>
                  <div className="w-full p-4 bg-white dark:bg-gray-600 relative">
                    <div className="flex items-center mt-1">
                      <div to="#" className="relative block">
                        <img
                          alt="profil"
                          src={p.usersDto.avatar}
                          className="mx-auto object-cover rounded-full h-10 w-10 "
                        />
                      </div>
                      <div className="flex flex-col justify-between ml-4 text-sm">
                        <p className="text-gray-800 dark:text-white">
                          {p.usersDto.username}
                        </p>
                        <p className="text-gray-400 dark:text-gray-300 ">
                          {p.createAt}
                        </p>
                      </div>
                      <div className="flex items-center absolute top-2 right-4">
                        <div
                          className="mr-2"
                          onClick={() => handleShowOption(p.id, p.showPostSide)}
                        >
                          <svg
                            xmlns="http://www.w3.org/2000/svg"
                            width="20"
                            height="20"
                            fill="currentColor"
                            className="bi bi-three-dots text-dark hover:text-gray-400"
                            viewBox="0 0 16 16"
                          >
                            <path d="M3 9.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3z" />
                          </svg>
                        </div>
                        {p.showPostSide && (
                          <div className="bottom-0 absolute bg-blue-100 dark:bg-gray-800 top-8 right-8 transform translate-x-1/2 -translate-y-full">
                            <PostOption
                              id={p.id}
                              handleUpdatePost={handleUpdatePost}
                            />
                          </div>
                        )}
                        {p.showUpdatePost && (
                          <PostPopup
                            id={p.id}
                            handleUpdatePost={handleUpdatePost}
                          />
                        )}
                      </div>
                    </div>

                    <p className="font-light text-gray-400 dark:text-gray-300 text-md">
                      {p.content}
                    </p>
                    <div className="flex flex-wrap items-center mt-4 justify-starts">
                      {p.hashtags.map((h) => (
                        <div className="text-xs mr-2 py-1.5 px-4 text-gray-600 bg-blue-100 rounded-xl">
                          {h}
                        </div>
                      ))}
                    </div>
                  </div>
                  <img
                    alt="blog photo"
                    src={p.file}
                    className="object-cover w-full max-h-50"
                  />
                  <div className="flex border-t border-gray-200 justify-center">
                    <div className="flex w-full items-center text-xl border-b-2">
                      <Like
                        like={p.usernameLike}
                        id={p.id}
                        username={username}
                      />
                      <Comment
                        handleShowComment={() =>
                          handleShowComment(p.id, p.showComment)
                        }
                      />
                    </div>
                  </div>
                  {p.showComment && (
                    <div className="border-2 border-t-0 mb-2">
                      {comments &&
                        comments.map((c) => (
                          <div
                            key={c.id}
                            className="mt-2 h-24 rounded-md w-60 overflow-hidden border-1"
                          >
                            <div className="flex flex-row items-center justify-center h-full space-x-5 ">
                              <img
                                src={c.user.avatar}
                                alt="avatar_user"
                                className="w-12 h-12 rounded-full"
                              ></img>
                              <div className="flex flex-col space-y-1">
                                <div className="text-blue-400 rounded-md">
                                  {c.user.username}
                                </div>
                                <div className="h-6 rounded-md w-36">
                                  {c.content}
                                </div>
                              </div>
                            </div>
                          </div>
                        ))}
                      <form className="mb-1 flex flex-col justify-center w-100 max-w-md space-y-3 md:flex-row md:w-full md:space-x-3 md:space-y-0">
                        <div className="relative">
                          <input
                            type="text"
                            id="form-subscribe-Subscribe"
                            className="rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-sm text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                            placeholder="Enter comment"
                            value={content}
                            onChange={(e) => setContent(e.target.value)}
                          />
                        </div>
                        <button
                          className="flex-shrink-0 px-4 py-2 text-base font-semibold text-white bg-purple-600 rounded-lg shadow-md hover:bg-purple-700 focus:outline-none focus:ring-2 focus:ring-purple-500 focus:ring-offset-2 focus:ring-offset-purple-200"
                          type="submit"
                          onClick={() => handleSubmit(p.id)}
                        >
                          Comment
                        </button>
                      </form>
                    </div>
                  )}
                </div>
              </div>
            </>
          );
        })}
      <NavPage totalPage={totalPage} onButtonClick={onButtonClick}/>
      <button
        onClick={handleOpen}
        className="fixed bottom-0 right-0 bg-blue-500 text-white px-4 py-2 rounded"
      >
        Đăng bài
      </button>
      {isOpen && (
        <PostPopup handleLoading={handleLoading} handleClose={handleClose} />
      )}
      {isLoading && <Loading />}
    </>
  );
};
export default Home;
