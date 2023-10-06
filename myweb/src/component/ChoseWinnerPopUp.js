import React, { useEffect, useState } from 'react';
import { authApi, endpoints } from '../configs/APIS';
import { useNavigate, useParams } from 'react-router-dom';
import Loading from './Loading';
import { toast } from 'react-toastify';

const ChoseWinner = () => {
    const [person, setPerson] = useState([]);
    const { id } = useParams();
    const nav = useNavigate();
    const [isLoading, setIsLoading] = useState(false);
    useEffect(() => {
        const process = async () => {
            setIsLoading(true);
            try {
                let { data } = await authApi().get(endpoints[`choseWinner`](id));
                console.info(data);
                setPerson(data);
                console(person);
            } catch (ex) {
                console.error(ex);
            }
            setIsLoading(false);
        }
        process();
    }, []);
    const handleWinner = (userId) => {
        setIsLoading(true)
        const process = async (userId) => {
            try {
                console.info(userId);
                let formData = new FormData();
                formData.append("userId", userId);
                let res = await authApi().post(endpoints[`choseWinner`](id), formData);
                if (res.status === 200) {
                    toast.success("chose winner successfull");
                    nav("/auction");
                }else toast.error("Invalid user");
            } catch (ex) {
                setIsLoading(false);
                console.error(ex);
            }
        }
        process(userId);
        setIsLoading(false);
    }
    const handleClose = () => {
        nav("/auction");
    }
    return (
        <div className="fixed inset-0 flex items-center justify-center z-50 ">
            <div className="fixed inset-0 bg-black opacity-50"></div>
            <div className="z-60 relative w-1/3 p-4 m-auto bg-white shadow-lg rounded-2xl ">
                <div className="w-full h-full text-center">
                    <div className="flex flex-col justify-between h-full">
                        {/* -------- */}
                        <div className="container flex flex-col items-center justify-center w-full mx-auto bg-white rounded-lg shadow dark:bg-gray-800">
                            <ul className="flex flex-col divide-y divide">
                                {
                                    person && person.map((p) => {
                                        return (
                                            <li className="flex flex-row">
                                                <div className="flex items-center flex-1 p-4 cursor-pointer select-none">
                                                    <div className="flex flex-col items-center justify-center w-10 h-10 mr-4">
                                                        <a href="#" className="relative block">
                                                            <img alt="profil" src={p.user.avatar} className="mx-auto object-cover rounded-full h-10 w-10 " />
                                                        </a>
                                                    </div>
                                                    <div className="flex-1 pl-1 mr-16">
                                                        <div className="font-medium dark:text-white">
                                                            {p.content}
                                                        </div>
                                                        <div className="text-sm text-gray-600 dark:text-gray-200">
                                                            {p.user.username}
                                                        </div>
                                                    </div>
                                                    <div className="text-xs text-gray-600 dark:text-gray-200">
                                                        {new Date(p.createAt).toLocaleString()}
                                                    </div>
                                                    <div className="ml-2 text-xs text-gray-600 dark:text-gray-200">
                                                        <button onClick={() => handleWinner(p.user.userId)} className="py-2 px-4 bg-indigo-600 hover:bg-indigo-700 focus:ring-indigo-500 focus:ring-offset-indigo-200 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2 rounded-lg">
                                                            Winner
                                                        </button>
                                                    </div>
                                                </div>
                                            </li>
                                        )
                                    })
                                }

                            </ul>
                        </div>
                        {/* ---------- */}
                    </div>
                </div>
            </div>
            {isLoading ? <Loading /> : null}
        </div>
    );
}

export default ChoseWinner;
