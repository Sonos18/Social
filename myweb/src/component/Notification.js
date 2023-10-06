import { useState } from "react";
import { useEffect } from "react";
import { authApi, endpoints } from "../configs/APIS";

const Notification = ({ notification, setNotification, setamount, amount }) => {
    const handleRead = (id) => {
        const process = async (id) => {
            try {
                await authApi().post(endpoints[`read`](id));
                // Sau khi cập nhật thành công, bạn có thể cập nhật trạng thái của thông báo trong danh sách
                const updatedNotification = notification.map((item) =>
                    item.notificationId === id ? { ...item, isRead: true } : item
                );
                setNotification(updatedNotification);
                let count = amount;
                if (count > 0)
                    setamount(count - 1);
            }
            catch (ex) {
                console.error(ex);
            }
        };
        process(id);
    };
    return (
        <div className="z-10 max-w-sm fixed top-16 right-28 m-4 p-4 bg-gray-100 text-black rounded-md shadow-lg">
            <h1 className="mb-2 text-center">Notification</h1>
            {notification ? (
                <>
                    {notification.map((p) => (
                        <div className="h-20 mx-2 border-0.5 rounded-md w-70" onClick={() => handleRead(p.notificationId)}>
                            <div className={`flex flex-row items-center justify-center h-full mx-1 ${p.isRead ? "opacity-50":"animate-pulse"}`}>
                                <img
                                    alt="profil"
                                    src={p.userId.avatar}
                                    className="mx-2 object-cover rounded-full h-12 w-12"
                                />
                                <div className="flex flex-col space-y-2">
                                    <div class="w-46 h-6 rounded-md text-left">
                                    {p.createdAt}</div>
                                    <div className="h-6 rounded-md w-46 text-left">
                                    {p.userId.username} Da {p.actionType} bai viet cua ban</div>
                                </div>
                            </div>
                        </div>


                    ))}
                </>
            ) : (
                <div>No message</div>
            )}

        </div>
    );
}

export default Notification