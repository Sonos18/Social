const Comment=({handleShowComment})=>{
    return(
        <div onClick={handleShowComment} className="inline-block items-center w-1/2">
          <button className="flex items-center space-x-2 focus:outline-none">
            <svg
              viewBox="0 0 512 512"
              fill="currentColor"
              height="1em"
              width="1em"
            >
              <path d="M144 208c-17.7 0-32 14.2-32 31.1 0 18.6 14.3 32.9 32 32.9s31.1-14.25 31.1-32-13.3-32-31.1-32zm112-.9c-17.75 0-31.1 14.25-31.1 32s14.25 31.1 31.1 31.1 31.1-14.25 31.1-31.1-13.3-32-31.1-32zm112 .9c-17.75 0-31.1 14.25-31.1 32s14.25 32 31.1 32c17.75 0 31.99-14.25 31.99-32 .01-17.8-14.19-32-31.99-32zM256 31.1C114.6 31.1.9 124.22.9 239.1c0 47.62 19.91 91.25 52.91 126.3-14.87 39.5-45.87 72.88-46.37 73.25-6.624 7-8.373 17.25-4.624 26C5.818 474.2 14.38 480 24 480c61.49 0 109.1-25.75 139.1-46.25 28.87 9 60.16 14.25 92.9 14.25 141.4 0 255.1-93.13 255.1-207.1S397.4 31.1 256 31.1zm0 368.9c-26.75 0-53.12-4.125-78.36-12.12l-22.75-7.125L135.4 394.5c-14.25 10.12-33.87 21.38-57.49 29 7.374-12.12 14.37-25.75 19.87-40.25l10.62-28-20.62-21.87C69.81 314.1 48.06 282.2 48.06 240c0-88.25 93.24-160 207.1-160s207.1 71.75 207.1 160S370.8 400 256 400z" />
            </svg>
            <span className="text-gray-600">Comment</span>
          </button>
        </div>
    );
}
export default Comment;