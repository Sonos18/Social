import { useRef, useState } from 'react';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { authApi, endpoints } from '../configs/APIS';
import { toFormData } from 'axios';
import { useNavigate } from 'react-router-dom';
import Loading from '../component/Loading';


export default function AuctionDetail() {
    const imgFile = useRef();
    const [selectedDateStart, setSelectedDateStart] = useState(null);
    const [selectedDateEnd, setSelectedDateEnd] = useState(null);
    const [startingPrice, setStartingPrice] = useState(0);
    const [buyoutPricePrice, setBuyoutPrice] = useState(0);
    const [content, setContent] = useState("");
    const [nameProduct, setNameProduct] = useState("");
    const [descriptionProduct, setDescriptionProduct] = useState("");
    const [isLoading, setIsLoading] = useState(false);
    let navigate = useNavigate();
    const handleDateStartChange = (date) => {
        setSelectedDateStart(date);
    };
    const handleDateEndChange = (date) => {
        setSelectedDateEnd(date);
    };
    const handleSubmit = () => {
        setIsLoading(true)
        const process = async () => {
            try {
                let formData = new FormData();
                formData.append("startTime", selectedDateStart.getTime());
                formData.append("endTime", selectedDateEnd.getTime());
                formData.append("startingPrice", startingPrice);
                formData.append("buyoutPrice", buyoutPricePrice);
                const hashtags = content.match(/#\w+\b/g);
                const content1 = content.replace(/#\w+\b/g, '');
                formData.append("content", content1);
                formData.append("hashtags", JSON.stringify(hashtags || [null]));
                formData.append("imgFile", imgFile.current.files[0] || null);
                formData.append("name", nameProduct);
                formData.append("description", descriptionProduct);
                let res = await authApi().post(endpoints[`auction`], formData);
                if (res.status === 201)
                    navigate("/auction")
                console.info(res.data);
            } catch (ex) {
                console.error(ex);
                setIsLoading(false);
            };
        }
        setIsLoading(false);
        process();
    }
    return (
        <>
            <section className="mt-2 mb-4 bg-gray-100/50">
                <form className="container max-w-2xl mx-auto shadow-md md:w-3/4">
                    <div className="p-4 border-t-2 border-indigo-400 rounded-lg bg-gray-100/5 ">
                        <div className="max-w-sm mx-auto md:w-full md:mx-0">
                            <div className="inline-flex items-center space-x-4">
                                <h1 className="text-black">Information Auction</h1>
                            </div>
                        </div>
                    </div>
                    <div className="space-y-2 bg-white">
                        <div className="items-center w-full p-4 space-y-2 text-gray-500 md:inline-flex md:space-y-0">
                            <div className="max-w-sm mx-auto md:w-1/2">
                                <p className="text-gray-700">Start time</p>
                                <DatePicker
                                    selected={selectedDateStart}
                                    onChange={handleDateStartChange}
                                    dateFormat="yyyy/MM/dd" // Customize the date format
                                    className="w-full p-2 border rounded-lg"
                                />
                            </div>
                            <div className="max-w-sm mx-auto md:w-1/2">
                                <p className="text-gray-700">Start end</p>
                                <DatePicker
                                    selected={selectedDateEnd}
                                    onChange={handleDateEndChange}
                                    dateFormat="yyyy/MM/dd" // Customize the date format
                                    className="w-full p-2 border rounded-lg"
                                />
                            </div>
                        </div>
                        <div className="items-center w-full p-4 space-y-2 text-gray-500 md:inline-flex md:space-y-0">
                            <h2 className="max-w-sm mx-auto md:w-1/3">Starting Price</h2>
                            <div className="max-w-sm mx-auto md:w-2/3">
                                <div className="relative">
                                    <input
                                        type="text"
                                        value={startingPrice} onChange={e => setStartingPrice(e.target.value)}
                                        className="rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-sm text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                                        placeholder="Starting Price"
                                    />
                                </div>
                            </div>
                        </div>
                        <div className="items-center w-full p-4 space-y-2 text-gray-500 md:inline-flex md:space-y-0">
                            <h2 className="max-w-sm mx-auto md:w-1/3">Content</h2>
                            <div className="max-w-sm mx-auto md:w-2/3">
                                <textarea
                                    className="overflow-hidden flex-1 w-full px-4 py-2 text-base text-gray-700 placeholder-gray-400 bg-white border border-gray-300 rounded-lg appearance-none focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                                    value={content} onChange={e => setContent(e.target.value)}
                                    placeholder="Enter your Content"
                                    name="content"
                                    rows="2"
                                    cols="40"
                                ></textarea>
                            </div>
                        </div>
                        <hr />
                        <div className="items-center w-full p-4 space-y-4 text-gray-500 md:inline-flex md:space-y-0">
                            <h2 className="max-w-sm mx-auto md:w-1/3">Product info</h2>
                            <div className="max-w-sm mx-auto space-y-5 md:w-2/3">
                                <div>
                                    <div className="relative">
                                        <input
                                            type="text"
                                            value={nameProduct} onChange={e => setNameProduct(e.target.value)}
                                            className="rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-sm text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                                            placeholder="Name"
                                        />
                                    </div>
                                </div>
                                <textarea
                                    className="overflow-hidden flex-1 w-full px-4 py-2 text-base text-gray-700 placeholder-gray-400 bg-white border border-gray-300 rounded-lg appearance-none focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                                    value={descriptionProduct} onChange={e => setDescriptionProduct(e.target.value)}
                                    placeholder="Enter description product"
                                    name="content"
                                    rows="2"
                                    cols="40"
                                ></textarea>
                            </div>
                        </div>
                        <hr />
                        <div className="items-center w-full p-8 space-y-4 text-gray-500 md:inline-flex md:space-y-0">
                            <h2 className="max-w-sm mx-auto md:w-4/12">Product Image</h2>
                            <div class="w-full max-w-sm pl-2 mx-auto space-y-5 md:w-5/12 md:pl-9 md:inline-flex">
                                <div class=" relative ">

                                    <input type="file" className="mt-2" ref={imgFile} />
                                    <button
                                        type="button"
                                        className="py-2 px-4 flex justify-center items-center bg-red-600 hover:bg-red-700 focus:ring-red-500 focus:ring-offset-red-200 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2 rounded-lg"
                                    >
                                        <svg
                                            width="20"
                                            height="20"
                                            fill="currentColor"
                                            className="mr-2"
                                            viewBox="0 0 1792 1792"
                                            xmlns="http://www.w3.org/2000/svg"
                                        >
                                            <path d="M1344 1472q0-26-19-45t-45-19-45 19-19 45 19 45 45 19 45-19 19-45zm256 0q0-26-19-45t-45-19-45 19-19 45 19 45 45 19 45-19 19-45zm128-224v320q0 40-28 68t-68 28h-1472q-40 0-68-28t-28-68v-320q0-40 28-68t68-28h427q21 56 70.5 92t110.5 36h256q61 0 110.5-36t70.5-92h427q40 0 68 28t28 68zm-325-648q-17 40-59 40h-256v448q0 26-19 45t-45 19h-256q-26 0-45-19t-19-45v-448h-256q-42 0-59-40-17-39 14-69l448-448q18-19 45-19t45 19l448 448q31 30 14 69z">
                                            </path>
                                        </svg>
                                        Upload
                                    </button>
                                </div>
                            </div>
                            <div class="text-center md:w-3/12 md:pl-6"></div>
                        </div>
                        <hr />
                        <div className="w-full px-4 pb-4 ml-auto text-gray-500 md:w-1/3">
                            <button
                                type="button"
                                onClick={handleSubmit}
                                className="py-2 px-4  bg-blue-600 hover:bg-blue-700 focus:ring-blue-500 focus:ring-offset-blue-200 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2  rounded-lg "
                            >
                                Create
                            </button>
                        </div>
                    </div>
                </form>
            </section>
            {isLoading ? <Loading /> : null}
        </>

    );
}
