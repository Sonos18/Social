// const LoginGoogle=()=>{
//     return(
//         <GoogleOAuthProvider clientId="852352451720-t84v78jbcd5f8st240i16cfij5jljon2.apps.googleusercontent.com">
//         <GoogleLogin onSubmit
//             className="login_google"
//             clientId="852352451720-t84v78jbcd5f8st240i16cfij5jljon2.apps.googleusercontent.com"
//             onSuccess={(credentialResponse) => {
//                 var token = credentialResponse.credential;
//                 var decoded = jwt_decode(token);
//                 console.log(decoded);
//                 loginGoogle(decoded);

//             }}
//             onFailure={(error) => {
//                    console.log("Đăng nhập không thành công", error);

//             }}
//             redirectUri="http://localhost:3000"
//         />
//     </GoogleOAuthProvider>
//     )
// }
// export default LoginGoogle;