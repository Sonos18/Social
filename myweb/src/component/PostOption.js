import { useDispatch } from "react-redux";
import { FunctionDeletePost, FunctionLockPost } from "../reducers/PostActions";
// import {  useState } from "react";


const PostOption = (props) => {
  const dispatch = useDispatch();
  const handleUpdate = () => {
    console.info(props.id);
    props.handleUpdatePost(props.id,true);
  };
  const handleDelete = () => {
    console.info(props.id);
    const process = async () => {
      try {
        const confirm = window.confirm("Are you sure delete this Post");
        if (confirm) {
          // setIsOpen(true);
          dispatch(FunctionDeletePost(props.id));
          // setIsLoading(false);
        }
      } catch (error) {
        console.error(error);
      }
    };
    process();
  };
  const handleLock = () => {
    console.info(props.id);
    const process = async () => {
      try {
        const confirm = window.confirm("Are you sure lock this Post");
        if (confirm) {
          // setIsLoading(true);
          dispatch(FunctionLockPost(props.id));
          // setIsLoading(false);
        }
      } catch (error) {
        console.error(error);
      }
    };
    process();
  };
  return (
    <>
      <div className="bg-cyan-100 dark:bg-gray-800">
        <div className="flex flex-col sm:flex-row sm:justify-around">
          <div className="h-1/3 ">
            <nav className="mt-1">
              {props.isAuth?
              <>
              <div
                onClick={handleUpdate}
                className="w-full hover:text-white-800 hover:bg-gray-100 inline-block items-center p-2 transition-colors dark:hover:text-gray-600 dark:hover:bg-gray-600 duration-200 text-gray-600 dark:text-gray-400 rounded-lg"
              >
                <span className="px-1 text-lg font-normal">Update</span>
                <span className="flex-grow text-right"></span>
              </div>
              <div
                className="w-full hover:text-gray-800 hover:bg-gray-100 inline-block items-center p-2 transition-colors dark:hover:text-white dark:hover:bg-gray-600 duration-200 text-gray-600 dark:text-gray-400 rounded-lg"
                onClick={() => handleLock()}
              >
                <span className="px-1 text-lg font-normal">Lock</span>
                <span className="flex-grow text-right"></span>
              </div>
              <div
                onClick={() => handleDelete()}
                className="w-full hover:text-gray-800 hover:bg-gray-100 inline-block items-center p-2 transition-colors dark:hover:text-white dark:hover:bg-gray-600 duration-200 text-gray-600 dark:text-gray-400 rounded-lg"
                to="#"
              >
                <span className="px-1 text-lg font-normal">Delete</span>
                <span className="flex-grow text-right"></span>
              </div>
              </>:
              <>
               <div
                onClick={() => props.handleReport()}
                className="w-full hover:text-gray-800 hover:bg-gray-100 inline-block items-center p-2 transition-colors dark:hover:text-white dark:hover:bg-gray-600 duration-200 text-gray-600 dark:text-gray-400 rounded-lg"
                to="#"
              >
                <span className="px-1 text-lg font-normal">Report</span>
                <span className="flex-grow text-right"></span>
              </div>
              </>
              }
            </nav>
          </div>
        </div>
      </div>
    </>
  );
};

export default PostOption;
