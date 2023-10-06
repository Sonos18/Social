import React, { useRef, useState } from "react";
import APIS, { endpoints } from "../configs/APIS";
import { Link, useNavigate } from "react-router-dom";
import Loading from "../component/Loading";
import { toast } from "react-toastify";

const Register = () => {
    const [user, setUser] = useState({
        "username": "",
        "email": "",
        "password": "",
        "confirmPassword": ""
    });
    const [isLoading, setIsLoading] = useState(false);
    const [err, setErr] = useState(null);
    const avatar = useRef();
    let nav = useNavigate();
    const [showPassword, setShowPassword] = useState(false);
    const change = (evt, field) => {
        setUser(current => {
            return { ...current, [field]: evt.target.value }
        })
    };
    const register = (evt) => {
        evt.preventDefault();
        setIsLoading(true);
        const process = async () => {
            try {
                let form = new FormData();
                for (let field in user)
                    if (field !== "confirmPassword")
                        form.append(field, user[field]);
                form.append("avatar", avatar.current.files[0]);
                let res = await APIS.post(endpoints['register'], form);
                if (res.status === 201)
                {
                    nav("/login");
                    toast.success("Register successfull");
                }
                else if(res.status===500)
                    toast.error("USERNAME ALREADY EXISTS ...");
                else if(res.status===400)
                    toast.error("sdhfsgf");
            } catch (ex) {
                toast.error("FILL OUT OR USERNAME ALREADY EXISTS ...");
            }
            setIsLoading(false);

        }
        if (user.password === user.confirmPassword)
            process();
        else {
            setIsLoading(false);
            toast.error("PASSWORD MISMATCH ...");
        }
    }

    //Showpassword

    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };

    return (
        <section className="bg-blue-100  min-h-screen flex items-center justify-center">
            {/* login container */}
            <div className="bg-gray-100 flex rounded-2xl shadow-lg max-w-3xl p-5 items-center">
                {/* image */}
                <div className="md:block hidden w-1/2">
                    <img
                        className="rounded-2xl"
                        src="https://images.unsplash.com/photo-1616606103915-dea7be788566?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1887&q=80"
                    />
                </div>
                {/* form */}
                <div className="md:w-1/2 px-8 md:px-16">
                    <h2 className="font-bold text-2xl text-[#002D74]">Register</h2>
                    <p className="text-xs mt-4 text-[#002D74]">
                        If you are already a member, easily register
                    </p>
                    {err === null ? "" : <alert className="text-red-500">{err}</alert>}
                    <form action="" className="flex flex-col gap-4" onSubmit={register}>
                        <input value={user.username} onChange={e => change(e, "username")}
                            className="p-2 mt-6 rounded-xl border" type="text" name="username" placeholder="Username" />
                        <input value={user.email} onChange={e => change(e, "email")}
                            className="p-2 rounded-xl border" type="email" name="email" placeholder="Your Email" />
                        <div className="relative">
                            <input value={user.password} onChange={e => change(e, "password")}
                                className="p-2 rounded-xl border w-full" type={showPassword ? "text" : "password"} name="password" placeholder="Password" />
                            <svg
                                xmlns="http://www.w3.org/2000/svg"
                                width={16}
                                height={16}
                                fill="gray"
                                className="bi bi-eye absolute top-1/2 right-3 -translate-y-1/2"
                                onClick={togglePasswordVisibility}
                                viewBox="0 0 16 16"
                            >
                                <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z" />
                                <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z" />
                            </svg>
                        </div>
                        <div className="relative">
                            <input value={user.confirmPassword} onChange={e => change(e, "confirmPassword")}
                                className="p-2 rounded-xl border w-full" type={showPassword ? "text" : "password"} name="confirmPassword" placeholder="ConfirmPassword" />
                            <svg
                                xmlns="http://www.w3.org/2000/svg"
                                width={16}
                                height={16}
                                fill="gray"
                                className="bi bi-eye absolute top-1/2 right-3 -translate-y-1/2"
                                onClick={togglePasswordVisibility}
                                viewBox="0 0 16 16"
                            >
                                <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z" />
                                <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z" />
                            </svg>
                        </div>
                        <div className="mb-3">
                            <label className="block text-sm font-medium text-gray-700">Avatar</label>
                            <input type="file" className="mt-1 block w-full py-2 px-3 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" ref={avatar} />
                        </div>

                        <button type="submit"
                            className="bg-[#002D74] rounded-xl text-white py-2 hover:scale-105 duration-300">
                            Register
                        </button>
                    </form>
                    <div className="mt-6 grid grid-cols-3 items-center text-gray-400">
                        <hr className="border-gray-400" />
                        <p className="text-center text-sm">OR</p>
                        <hr className="border-gray-400" />
                    </div>
                    <div className="mt-3 text-xs flex justify-between items-center text-[#002D74]">
                        <p>You have an account?</p>
                        <Link to="/login">
                            <button className="py-2 px-5 bg-white border rounded-xl hover:scale-110 duration-300">
                                Login
                            </button>
                        </Link>

                    </div>
                </div>
            </div>
            {isLoading ? <Loading /> : null}
        </section>

    );
};

export default Register;




