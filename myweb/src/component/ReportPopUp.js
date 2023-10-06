import React, { useState } from 'react';
import { authApi, endpoints } from '../configs/APIS';
import { useParams } from 'react-router-dom';


const ReportPopUp = () => {
    const {id}=useParams();
    const[value,setValue]=useState("Reason")
    const [showListDown,setShowListDown]=useState(false);
    const reasons = [
        { reason: "User bids but does not pay maths" },
        { reason: "User uses incorrect words" },
        { reason: "Inappropriate content" }
    ];
    const handleReport=()=>{
        const process=async()=>{
            let formData=new FormData();
            formData.append("content",value);
            formData.append("postId",id);
            let{data}=await authApi().post(endpoints[`report`](id),formData);
        }
    }
    const handleOpen = () => setShowListDown(!showListDown);
    const handleReason=(index)=>{
        setValue(reasons[index].reason);
    }
    return (
        <div className="w-64 p-4 m-auto bg-white shadow-lg rounded-2xl dark:bg-gray-800">
            <div className="w-full h-full text-center">
                <div className="flex flex-col justify-between h-full">
                    <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="32"
                        height="32"
                        fill="currentColor"
                        className="bi bi-bookmark-plus text-white mx-auto"
                        viewBox="0 0 16 16"
                    >
                        <path d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v13.5a.5.5 0 0 1-.777.416L8 13.101l-5.223 2.815A.5.5 0 0 1 2 15.5V2z" />
                        <path d="M8 4a.5.5 0 0 1 .5.5V6H10a.5.5 0 0 1 0 1H8.5v1.5a.5.5 0 0 1-1 0V7H6a.5.5 0 0 1 0-1h1.5V4.5A.5.5 0 0 1 8 4z" />
                    </svg>
                    <p className="mt-4 text-xl font-bold text-gray-800 dark:text-gray-200">
                        Report Post
                    </p>
                    {/* --------- */}
                    <div className="mt-2 relative inline-block text-left">
                        <div>
                            <button
                                onClick={handleOpen}
                                type="button"
                                className="border border-gray-300 bg-white dark:bg-gray-800 shadow-sm flex items-center justify-center w-full rounded-md px-4 py-2 text-sm font-medium text-gray-700 dark:text-gray-50 hover:bg-gray-50 dark:hover:bg-gray-500 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-gray-100 focus:ring-gray-500"
                                id="options-menu"
                            >
                             {value}   
                                <svg
                                    width="20"
                                    height="20"
                                    fill="currentColor"
                                    viewBox="0 0 1792 1792"
                                    xmlns="http://www.w3.org/2000/svg"
                                >
                                    <path d="M1408 704q0 26-19 45l-448 448q-19 19-45 19t-45-19l-448-448q-19-19-19-45t19-45 45-19h896q26 0 45 19t19 45z"></path>
                                </svg>
                            </button>
                        </div>
                        {showListDown&&
                        <div className="absolute right-0 w-54 mt-2 origin-top-right bg-white rounded-md shadow-lg dark:bg-gray-800 ring-1 ring-black ring-opacity-5">
                            <div className="border-gray-300 py-1" role="menu" aria-orientation="vertical" aria-labelledby="options-menu">
                                {reasons.map((reason,index)=>
                                (<div onClick={() => handleReason(index)}
                                    className="block px-4 py-2 text-md text-gray-700 hover:bg-gray-100 hover:text-gray-900 dark:text-gray-100 dark:hover:text-white dark:hover:bg-gray-600"
                                    role="menuitem"
                                >
                                    <span className="flex flex-col">
                                        <span>{reasons[index].reason}</span>
                                    </span>
                                </div>
                                ))}
                            </div>
                        </div>}
                    </div>
                    {/* --------- */}
                    <div className="flex items-center justify-between w-full gap-4 mt-8">
                        <button
                            type="button"
                            className="py-2 px-4 bg-indigo-600 hover:bg-indigo-700 focus:ring-indigo-500 focus:ring-offset-indigo-200 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2 rounded-lg"
                        >
                            Report
                        </button>
                        <button
                            type="button"
                            className="py-2 px-4 bg-white hover:bg-gray-100 focus:ring-indigo-500 focus:ring-offset-indigo-200 text-indigo-500 w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2 rounded-lg"
                        >
                            Cancel
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ReportPopUp;