import axios from "axios";
import cookie from "react-cookies";

const SERVER_CONTEXT = "/";
const SERVER = "http://localhost:8080";

export const endpoints = {
    "posts": `${SERVER_CONTEXT}api/posts/`,
    "login": `${SERVER_CONTEXT}api/login/`,
    "current-user":`${SERVER_CONTEXT}api/current_user/`,
    "register":`${SERVER_CONTEXT}api/register/`,
    "post-detail":(id)=>`${SERVER_CONTEXT}api/posts/${id}/`,
    "notification":`${SERVER_CONTEXT}api/users/notifications/`,
    "read":(id)=>`${SERVER_CONTEXT}api/users/notifications/${id}/`,
    "like":(id)=>`${SERVER_CONTEXT}api/posts/${id}/like/`,
    "lock":(id)=>`${SERVER_CONTEXT}api/posts/${id}/lock/`,
    "auction":`${SERVER_CONTEXT}api/auction/`,
    "dislike":(id)=>`${SERVER_CONTEXT}api/posts/${id}/dislike`,
    "comment":(postId)=>`${SERVER_CONTEXT}api/posts/${postId}/comments/`,
    "winningBid":(auctionId)=>`${SERVER_CONTEXT}api/auction/${auctionId}/winningBid/`,
    "choseWinner":(auctionId)=>`${SERVER_CONTEXT}api/auction/${auctionId}/choseWinner/`,
    "report":(postId)=>`${SERVER_CONTEXT}api/post/${postId}/report`,
    "admin-users":`${SERVER_CONTEXT}api/admin/users/`,
    "chart":`${SERVER_CONTEXT}api/admin/charts/`,
    "reports":`${SERVER_CONTEXT}api/admin/reports/`,
}

export const authApi = () => {
    return axios.create({
        baseURL: SERVER,
        headers: {
            "Authorization": cookie.load("token")
        }
    })
}

export default axios.create({
    baseURL: SERVER
})