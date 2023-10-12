import { useDispatch, useSelector } from "react-redux";
import { FetchUserList, FunctionDeleteUser } from "../reducers/UserAction";
import { useEffect, useState } from "react";
import NavPage from "./NavPage";

const AccountUser = () => {
  const users = useSelector((state) => state.users.users);
  const totalPage = useSelector((state) => state.users.totalPage);
  const [page, setPage] = useState(0);
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(FetchUserList(page));
  }, [dispatch, page]);
  const onButtonClick=(i)=>{
    setPage(i-1);
  }
  const handleDeleteUser=(username)=>{
    const result = window.confirm('Are you sure you want to perform this action?');
    if(result){
      dispatch(FunctionDeleteUser(username));
    }
  }
  return (
    <div className="w-full container max-w-3xl px-4 mx-auto sm:px-8">
      <div className="py-8">
        <div className="px-4 py-4 overflow-x-auto sm:-mx-8 sm:px-8">
          <div className="inline-block min-w-full overflow-hidden rounded-lg shadow">
            <table className="min-w-full leading-normal">
              <thead>
                <tr>
                  <th
                    scope="col"
                    className="px-8 py-3 text-sm font-normal text-left text-gray-800 uppercase bg-white border-b border-gray-200"
                  >
                    User
                  </th>
                  <th
                    scope="col"
                    className="px-5 py-3 text-sm font-normal text-left text-gray-800 uppercase bg-white border-b border-gray-200"
                  >
                    Role
                  </th>
                  <th
                    scope="col"
                    className="px-5 py-3 text-sm font-normal text-left text-gray-800 uppercase bg-white border-b border-gray-200"
                  >
                    Created at
                  </th>
                  <th
                    scope="col"
                    className="px-5 py-3 text-sm font-normal text-left text-gray-800 uppercase bg-white border-b border-gray-200"
                  >
                    status
                  </th>
                  <th
                    scope="col"
                    className="px-5 py-3 text-sm font-normal text-left text-gray-800 uppercase bg-white border-b border-gray-200"
                  ></th>
                  <th
                    scope="col"
                    className="px-5 py-3 text-sm font-normal text-left text-gray-800 uppercase bg-white border-b border-gray-200"
                  ></th>
                </tr>
              </thead>
              <tbody>
                {users &&
                  users.map((user) => {
                    return (
                      <tr>
                        <td className="px-5 py-5 text-sm bg-white border-b border-gray-200">
                          <div className="flex items-center">
                            <div className="flex-shrink-0">
                              <div href="#" className="relative block">
                                <img
                                  alt="profil"
                                  src={user.avatar}
                                  className="mx-auto object-cover rounded-full h-10 w-10 "
                                />
                              </div>
                            </div>
                            <div className="ml-3">
                              <p className="text-gray-900 whitespace-no-wrap">
                                {user.username}
                              </p>
                            </div>
                          </div>
                        </td>
                        <td className="px-5 py-5 text-sm bg-white border-b border-gray-200">
                          <p className="text-gray-900 whitespace-no-wrap">
                            {user.role}
                          </p>
                        </td>
                        <td className="px-5 py-5 text-sm bg-white border-b border-gray-200">
                          <p className="text-gray-900 whitespace-no-wrap">
                            12/09/2020
                          </p>
                        </td>
                        <td className="px-5 py-5 text-sm bg-white border-b border-gray-200">
                          <span className="relative inline-block px-3 py-1 font-semibold leading-tight text-green-900">
                            <span
                              aria-hidden="true"
                              className="absolute inset-0 bg-green-200 rounded-full opacity-50"
                            ></span>
                            <span className="relative">active</span>
                          </span>
                        </td>
                        <td className="px-5 py-5 text-sm bg-white border-b border-gray-200">
                          <div
                            href="#"
                            className="text-indigo-600 hover:text-indigo-900"
                          >
                            Update
                          </div>
                        </td>
                        <td className="px-5 py-5 text-sm bg-white border-b border-gray-200 cursor-pointer">
                          <div
                            onClick={()=>handleDeleteUser(user.username)}
                            className="text-red-700 hover:text-red-900"
                          >
                            Delete
                          </div>
                        </td>
                      </tr>
                    );
                  })}
              </tbody>
            </table>
            <NavPage totalPage={totalPage} onButtonClick={onButtonClick}/>
            {/* <div className="flex flex-col items-center px-5 py-5 bg-white xs:flex-row xs:justify-between">
              <div className="flex items-center">
                <button
                  type="button"
                  className="w-full p-4 text-base text-gray-600 bg-white border rounded-l-xl hover:bg-gray-100"
                >
                  <svg
                    width="9"
                    fill="currentColor"
                    height="8"
                    className=""
                    viewBox="0 0 1792 1792"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path d="M1427 301l-531 531 531 531q19 19 19 45t-19 45l-166 166q-19 19-45 19t-45-19l-742-742q-19-19-19-45t19-45l742-742q19-19 45-19t45 19l166 166q19 19 19 45t-19 45z"></path>
                  </svg>
                </button>
                {numbers.map((number) => (
                  <button
                    type="button"
                    className="w-full px-4 py-2 text-base text-indigo-500 bg-white border-t border-b hover:bg-gray-100 "
                  >
                    {number}
                  </button>
                ))}

                
                <button
                  type="button"
                  className="w-full p-4 text-base text-gray-600 bg-white border-t border-b border-r rounded-r-xl hover:bg-gray-100"
                >
                  <svg
                    width="9"
                    fill="currentColor"
                    height="8"
                    className=""
                    viewBox="0 0 1792 1792"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path d="M1363 877l-742 742q-19 19-45 19t-45-19l-166-166q-19-19-19-45t19-45l531-531-531-531q-19-19-19-45t19-45l166-166q19-19 45-19t45 19l742 742q19 19 19 45t-19 45z"></path>
                  </svg>
                </button>
              </div>
            </div> */}
          </div>
        </div>
      </div>
    </div>
  );
};

export default AccountUser;
