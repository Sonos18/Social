import { useContext, useEffect, useState } from "react"
import { Link, useNavigate } from "react-router-dom"
import { authApi, endpoints } from "../configs/APIS";
import Clock from "../component/Clock";
import { MyUserContext } from "../App";
const Auction = () => {
    const now = new Date().getTime();
    const [auctions, setAuctions] = useState([]);
    const [showPrice, setShowPrice] = useState(false);
    const [myAuction, setMyAuction] = useState([]);
    const [showMyAuction, setShowMyAuction] = useState(true);
    const [user, dispatch] = useContext(MyUserContext);
    const nav=useNavigate();
    useEffect(() => {
        const loadAuctions = async () => {
            try {
                let res = await authApi().get(endpoints[`auction`]);
                const formattedAuction = res.data.map(auction => ({
                    ...auction,
                    startTimeTime: new Date(auction.startTime).toLocaleString(),
                    endTimeTime: new Date(auction.endTime).toLocaleString(),
                }));
                setAuctions(formattedAuction);
                setMyAuction(formattedAuction);
            } catch (ex) {
                console.error(ex);
            }
        };
        loadAuctions();
    }, []);
    const [showInfo, setShowInfo] = useState(false);
    //
    const handleMyAuction = () => {
        if (showMyAuction) {
            const filtered = auctions.filter((auction) =>
                auction.postDto.usersDto.username === user.username
            );
            setMyAuction(filtered);
        } else {
            setMyAuction(auctions);
        }
        setShowMyAuction(!showMyAuction);
    }
    //PopUp Price
    const handlePrice = () => {
        setShowPrice(!showPrice);
    }

    const toggleInfo = () => {
        setShowInfo(!showInfo);
    };
    if(user===null) nav("/login");else{
    return (
        <>
            <button
                type="button"
                onClick={handleMyAuction}
                className=" w-30 py-2 px-4 bg-gradient-to-r from-green-400 to-blue-500 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2"
            >
                MyAuction
            </button>
            {myAuction &&
                myAuction.map(auction => {
                    let url = `/auctions/${auction.auctionId}/winningBid/`;
                    let url2 = `/auctions/${auction.auctionId}/choseWinner`;
                    return (
                        <div key={auction.auctionId} className="mb-2 mt-2 m-auto overflow-hidden rounded-lg shadow-lg cursor-pointer h-90 w-60 md:w-1/2">
                            <Link to="#" className="block w-full h-full">
                                <div className="w-full p-4 bg-white dark:bg-gray-600">
                                    <div className="flex items-center mt-4 relative">
                                        <Link to="#" className="relative block">
                                            <img
                                                alt="username"
                                                src={auction.postDto.usersDto.avatar}
                                                className="mx-auto object-cover rounded-full h-10 w-10"
                                            />
                                        </Link>
                                        <div className="flex flex-col justify-between ml-4 text-sm">
                                            <p className="text-gray-800 dark:text-white">{auction.postDto.usersDto.username}</p>
                                            <p className="text-gray-400 dark:text-gray-300">
                                                Start time : {auction.startTimeTime}
                                            </p>
                                            <p className="text-gray-400 dark:text-gray-300">
                                                End time : {auction.endTimeTime}
                                            </p>
                                        </div>
                                        <div className="absolute top-4 right-4 text-indigo-500">
                                            {auction.endTime - now > 0 && auction.postDto.usersDto.username !== user.username ? (
                                                <>
                                                    <Clock endTime={auction.endTime} />
                                                    <Link to={url}>
                                                        <button
                                                            type="button"
                                                            className="mt-4 py-2 px-4 bg-gradient-to-r from-green-400 to-blue-500 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2"
                                                        >
                                                            Auction
                                                        </button>
                                                    </Link>
                                                </>
                                            ) : (
                                                <>
                                                    {auction.endTime - now > 0 && auction.postDto.usersDto.username === user.username ? (
                                                        <Clock endTime={auction.endTime} />
                                                    ) : (
                                                        <>
                                                            {auction.postDto.usersDto.username === user.username ? (
                                                                <Link to={url2}>
                                                                    <button
                                                                        type="button"
                                                                        className="mt-4 py-2 px-4 bg-gradient-to-r from-green-400 to-blue-500 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2"
                                                                    >
                                                                        Chose winner
                                                                    </button>
                                                                </Link>
                                                            ) : (
                                                                <></>
                                                            )}
                                                        </>
                                                    )}
                                                </>
                                            )}

                                        </div>
                                    </div>
                                    <p className="font-medium text-indigo-500 text-md">Starting Price: {auction.startingPrice}</p>
                                    <p className="mb-2 text-xl font-medium text-gray-800 dark:text-white">
                                        Buyout Price: {auction.buyoutPrice}
                                    </p>
                                    <p className="font-medium text-indigo-500 text-xl">Current Price: {auction.winningBid}</p>
                                    <p className="font-light text-gray-400 dark:text-gray-300 text-md">
                                        {auction.postDto.content}
                                    </p>
                                    <div className="flex flex-wrap items-center mt-4 justify-starts">
                                        {auction.postDto.hashtags.map((h) => (
                                            <div className="text-xs mr-2 py-1.5 px-4 text-gray-600 bg-blue-100 rounded-2xl">
                                                {h}
                                            </div>
                                        ))}
                                    </div>

                                </div>
                                <div className="relative" onMouseEnter={toggleInfo} onMouseLeave={toggleInfo}>
                                    {/* Ảnh sản phẩm */}
                                    <img
                                        alt="Product photo"
                                        src={auction.productsDto.image}
                                        className="object-cover w-full max-h-45"
                                    />

                                    {/* Thông tin sản phẩm (ẩn hoặc hiển thị dựa trên showInfo) */}
                                    {showInfo && (
                                        <div className="absolute bottom-0 left-0 right-0 p-4 bg-white dark:bg-gray-700 bg-opacity-90 dark:opacity-50 ">
                                            <div class="w-2/3 p-4">
                                                <h1 class="text-2xl font-bold dark:text-gray-100">
                                                    {auction.productsDto.name}
                                                </h1>
                                                <p class="mt-2 text-sm dark:text-gray-200">
                                                    {auction.productsDto.description}
                                                </p>
                                                <div class="flex mt-2 item-center ">
                                                    <svg class="w-5 h-5 dark:text-gray-100 fill-current" viewBox="0 0 24 24">
                                                        <path d="M12 17.27L18.18 21L16.54 13.97L22 9.24L14.81 8.63L12 2L9.19 8.63L2 9.24L7.46 13.97L5.82 21L12 17.27Z">
                                                        </path>
                                                    </svg>
                                                    <svg class="w-5 h-5 dark:text-gray-100 fill-current" viewBox="0 0 24 24">
                                                        <path d="M12 17.27L18.18 21L16.54 13.97L22 9.24L14.81 8.63L12 2L9.19 8.63L2 9.24L7.46 13.97L5.82 21L12 17.27Z">
                                                        </path>
                                                    </svg>
                                                    <svg class="w-5 h-5 dark:text-gray-100 fill-current" viewBox="0 0 24 24">
                                                        <path d="M12 17.27L18.18 21L16.54 13.97L22 9.24L14.81 8.63L12 2L9.19 8.63L2 9.24L7.46 13.97L5.82 21L12 17.27Z">
                                                        </path>
                                                    </svg>
                                                    <svg class="w-5 h-5 dark:text-gray-100 fill-current" viewBox="0 0 24 24">
                                                        <path d="M12 17.27L18.18 21L16.54 13.97L22 9.24L14.81 8.63L12 2L9.19 8.63L2 9.24L7.46 13.97L5.82 21L12 17.27Z">
                                                        </path>
                                                    </svg>
                                                    <svg class="w-5 h-5 text-gray-500 fill-current" viewBox="0 0 24 24">
                                                        <path d="M12 17.27L18.18 21L16.54 13.97L22 9.24L14.81 8.63L12 2L9.19 8.63L2 9.24L7.46 13.97L5.82 21L12 17.27Z">
                                                        </path>
                                                    </svg>
                                                </div>
                                            </div>
                                        </div>
                                    )}
                                </div>
                            </Link>

                        </div>

                    )
                })}
            <Link to="/auction/auctiondetail" className="fixed bottom-8 right-8 text-blue-500">
                <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="40"
                    height="40"
                    fill="currentColor"
                    className="bi bi-patch-plus"
                    viewBox="0 0 16 16"
                >
                    <path
                        fillRule="evenodd"
                        d="M8 5.5a.5.5 0 0 1 .5.5v1.5H10a.5.5 0 0 1 0 1H8.5V10a.5.5 0 0 1-1 0V8.5H6a.5.5 0 0 1 0-1h1.5V6a.5.5.5 0 0 1 .5-.5z"
                    />
                    <path d="m10.273 2.513-.921-.944.715-.698.622.637.89-.011a2.89 2.89 0 0 1 2.924 2.924l-.01.89.636.622a2.89 2.89 0 0 1 0 4.134l-.637.622.011.89a2.89 2.89 0 0 1-2.924 2.924l-.89-.01-.622.636a2.89 2.89 0 0 1-4.134 0l-.622-.637-.89.011a2.89 2.89 0 0 1-2.924-2.924l.01-.89-.636-.622a2.89 2.89 0 0 1 0-4.134l.637-.622-.011-.89a2.89 2.89 0 0 1 2.924-2.924l.89.01.622-.636a2.89 2.89 0 0 1 4.134 0l-.715.698a1.89 1.89 0 0 0-2.704 0l-.92.944-1.32-.016a1.89 1.89 0 0 0-1.911 1.912l.016 1.318-.944.921a1.89 1.89 0 0 0 0 2.704l.944.92-.016 1.32a1.89 1.89 0 0 0 1.912 1.911l1.318-.016.921.944a1.89 1.89 0 0 0 2.704 0l.92-.944 1.32.016a1.89 1.89 0 0 0 1.911-1.912l-.016-1.318.944-.921a1.89 1.89 0 0 0 0-2.704l-.944-.92.016-1.32a1.89 1.89 0 0 0-1.912-1.911l-1.318.016z"
                    />
                </svg>
            </Link>
        </>

    )
}}

export default Auction


