import React, { useContext, useState } from 'react';
import { authApi, endpoints } from '../configs/APIS';
import { useNavigate, useParams } from 'react-router-dom';
import { MyUserContext } from '../App';
import { toast } from 'react-toastify';

const InputPrice = () => {
    const [price, setPrice] = useState(null);
    const { id } = useParams();
    const nav = useNavigate();
    const onConfirm = () => {
        const process = async () => {
            try {
                let formData = new FormData();
                formData.append("id", id);
                formData.append("bid", price);
                let  res  = await authApi().post(endpoints[`winningBid`](id), formData);
                if(res.sta===400)
                    toast.error("Value entered is wrong")
                else toast.success("successfull");
                nav("/auction");
            } catch (ex) {
                console.error(ex);
            }
        }
        process();
    }
    const onCancel = () => {
        nav("/auction");
    }
    return (
        <div className="fixed inset-0 flex items-center justify-center z-50">
            <div className="fixed inset-0 bg-black opacity-50"></div>
            <div className="z-60 relative w-64 p-4 m-auto bg-white shadow-lg rounded-2xl dark:bg-gray-800">
                <div className="w-full h-full text-center">
                    <div className="flex flex-col justify-between h-full">
                        <svg
                            width="40"
                            height="40"
                            className="w-12 h-12 m-auto mt-2 text-indigo-500"
                            fill="currentColor"
                            viewBox="0 0 1792 1792"
                            xmlns="http://www.w3.org/2000/svg"
                        >
                            <path d="M704 1376v-704q0-14-9-23t-23-9h-64q-14 0-23 9t-9 23v704q0 14 9 23t23 9h64q14 0 23-9t9-23zm256 0v-704q0-14-9-23t-23-9h-64q-14 0-23 9t-9 23v704q0 14 9 23t23 9h64q14 0 23-9t9-23zm256 0v-704q0-14-9-23t-23-9h-64q-14 0-23 9t-9 23v704q0 14 9 23t23 9h64q14 0 23-9t9-23zm-544-992h448l-48-117q-7-9-17-11h-317q-10 2-17 11zm928 32v64q0 14-9 23t-23 9h-96v948q0 83-47 143.5t-113 60.5h-832q-66 0-113-58.5t-47-141.5v-952h-96q-14 0-23-9t-9-23v-64q0-14 9-23t23-9h309l70-167q15-37 54-63t79-26h320q40 0 79 26t54 63l70 167h309q14 0 23 9t9 23z">
                            </path>
                        </svg>
                        <input type="text" id="&quot;form-subscribe-Subscribe"
                            className="mt-2 rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-sm text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent"
                            placeholder="Enter Price" value={price} onChange={e => setPrice(e.target.value)} />
                        <div className="flex items-center justify-between w-full gap-4 mt-8">
                            <button
                                type="button"
                                onClick={onConfirm}
                                className="py-2 px-4 bg-indigo-600 hover:bg-indigo-700 focus:ring-indigo-500 focus:ring-offset-indigo-200 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2 rounded-lg"
                            >
                                Enter
                            </button>
                            <button
                                type="button"
                                onClick={onCancel}
                                className="py-2 px-4 bg-white hover:bg-gray-100 focus:ring-indigo-500 focus:ring-offset-indigo-200 text-indigo-500 w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2 rounded-lg"
                            >
                                Cancel
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default InputPrice;
