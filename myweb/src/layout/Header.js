import { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { MyUserContext } from "../App";
import Notification from "../component/Notification";
import { useEffect } from "react";
import { authApi, endpoints } from "../configs/APIS";


const Header = ({ onSearchChange }) => {
  const [user, dispatch] = useContext(MyUserContext);
  const [activeLink, setActiveLink] = useState(0);
  const [showNotification, setShowNotification] = useState(false);
  const [notificationMessage, setNotificationMessage] = useState("");
  const navigate = useNavigate();
  const handleShowNotification = () => {
    if (user !== null) {
      let show = showNotification;
      setShowNotification(!show);
    } else
      navigate("/login");

  }
  const [amount, setamount] = useState(0)
  // const [notification, setNotification] = useState("");
  // useEffect(() => {
  //   const loadNotification = async () => {
  //     if (user !== null) {
  //       try {
  //         let { data } = await authApi().get(endpoints[`notification`]);
  //         console.log(data);
  //         const formattedNotification = data.map(not => ({
  //           ...not,
  //           createdAt: new Date(not.createdAt).toLocaleString(),
  //         }));
  //         setNotification(formattedNotification);
  //         let count = 0;
  //         data.map((not) => {
  //           if (!not.isRead) {
  //             count++;
  //           }
  //         })
  //         setamount(count);
  //       }
  //       catch (ex) {
  //         console.error(ex);
  //       }
  //     }
  //   };
  //   loadNotification();
  // }, [user,Notification]);
  const handleLinkClick = (index) => {
    setActiveLink(index);
    onSearchChange("");
  };
  const logout = () => {
    setamount(0);
    navigate("/login");
    dispatch({
      "type": "logout"
    })
  }
  const links = [
    { text: "Home", link: "/" },
    { text: "Logs", link: "/admin" },
    { text: "Auctions", link: "/auction" },
    { text: "About", link: "/about" }
  ];

  // Search
  const handleSearchChange = (e) => {
    onSearchChange(e.target.value);
  };
  return (
    <>
      <div className="border-gray-200 border-b py-3 bg-cyan-100">
        <div className="flex items-center justify-between">
          {/* Logo */}
          <div className="text-start flex items-center space-x-2 ml-4">
            <Link to="/">Logo</Link>
            <form className="flex flex-col justify-center w-3/4 max-w-sm space-y-3 md:flex-row md:w-full md:space-x-3 md:space-y-0">
              <div className=" relative ">
                <input
                  type="text"
                  id='"form-subscribe-Search'
                  className=" rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-sm text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                  placeholder="Enter a title"
                  onChange={handleSearchChange}
                />
              </div>
            </form>
          </div>

          {/* Navigation */}
          <div className="text-sm font-medium text-center text-gray-900  border-gray-200">
            <ul className="flex flex-wrap -mb-px">
              {links.map((link, index) => (
                <li className="mr-2" key={index}>
                  <Link
                    to={link.link}
                    className={`inline-block p-4 ${activeLink === index
                      ? "text-purple-600 border-b-2 border-purple-600 rounded-t-lg active"
                      : "border-b-2 border-transparent rounded-t-lg hover:text-gray-600 hover:border-gray-300"
                      }`}
                    onClick={() => handleLinkClick(index)}
                  >
                    {link.text}
                  </Link>
                </li>
              ))}
            </ul>
          </div>
          {/* Notification  */}
          <div className="text-end flex items-center justify-between">
            <button type="button" className="relative text-4xl text-white text-md"
              onClick={handleShowNotification}>
              <span className="absolute w-4 h-4 text-xs bg-red-500 rounded-full right-2 leading">
                {amount}
              </span>
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                fill="currentColor" className="bi bi-bell-fill text-gray-700" viewBox="0 0 16 16">
                <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zm.995-14.901a1 1 0 1 0-1.99 0A5.002 5.002 0 0 0 3 6c0 1.098-.5 6-2 7h14c-1.5-1-2-5.902-2-7 0-2.42-1.72-4.44-4.005-4.901z" />
              </svg>
            </button>
            <div className="max-w-md flex items-center mr-4">
              {/* {showNotification && (
                <Notification
                  notification={notification} setNotification={setNotification} amount={amount} setamount={setamount}
                />)} */}
            </div>
            <div className="text-center text-gray-500 border-gray-200 flex items-center space-x-2 mr-4 ">
              {user === null ? <>
                <Link className="inline-block p-4 border-b-2 border-transparent rounded-t-lg hover:text-gray-600 hover:border-gray-300" to="/login">Đăng nhập</Link>
                <Link className="inline-block p-4 border-b-2 border-transparent rounded-t-lg hover:text-gray-600 hover:border-gray-300" to="/register">Đăng ký</Link>
              </> : <>
                <div className="relative block">
                  <img
                    alt="profil"
                    src={user.avatar}
                    className="mx-auto object-cover rounded-full h-10 w-10"
                  />
                </div>
                <button className="inline-block p-4 border-b-2 border-transparent rounded-t-lg hover:text-gray-600 hover:border-gray-300" onClick={logout}>Đăng xuất</button>
              </>}
            </div>

          </div>

        </div>
      </div >
    </>
  );
};
export default Header;
