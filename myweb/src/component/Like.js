import React, {  useEffect, useState } from 'react';
import { authApi, endpoints } from '../configs/APIS';

const Like = (props) => {
  const [like, setLike] = useState(false);
  const [amount, setAmount] = useState(0);
  const handleLike = () => {
    const process = async () => {
      try {
        let formData = new FormData();
        formData.append("id", props.id);
        let tamp = 0;
        let res=await authApi().post(endpoints['like'](props.id), formData);
        console.info(res.data);
        if (like) {
          tamp = amount - 1;
        }
        else {
          tamp = amount + 1;
        }
          setLike(!like);
          setAmount(tamp);
        }
    catch (ex) {
          console.error(ex);
        }

      }
    process();
    };
    useEffect(() => {
      const nameExits = props.like.includes(props.username);
      let count = 0;
      if (props.like)
        count = props.like.length;
      setAmount(count);
      setLike(nameExits ? true : false);
    }, []);
    return (
      <div onClick={handleLike}
        className="items-center w-1/2 text-center inline-block m-2">
        <button className="flex items-center space-x-2 focus:outline-none">
          <span className="text-gray-600">{amount}</span>
          <svg
            viewBox="0 0 1024 1024"
            fill="currentColor"
            height="1em"
            width="1em"
            className={like ? "text-blue-400" : ""}
          >
            <path d="M885.9 533.7c16.8-22.2 26.1-49.4 26.1-77.7 0-44.9-25.1-87.4-65.5-111.1a67.67 67.67 0 00-34.3-9.3H572.4l6-122.9c1.4-29.7-9.1-57.9-29.5-79.4A106.62 106.62 0 00471 99.9c-52 0-98 35-111.8 85.1l-85.9 311H144c-17.7 0-32 14.3-32 32v364c0 17.7 14.3 32 32 32h601.3c9.2 0 18.2-1.8 26.5-5.4 47.6-20.3 78.3-66.8 78.3-118.4 0-12.6-1.8-25-5.4-37 16.8-22.2 26.1-49.4 26.1-77.7 0-12.6-1.8-25-5.4-37 16.8-22.2 26.1-49.4 26.1-77.7-.2-12.6-2-25.1-5.6-37.1zM184 852V568h81v284h-81zm636.4-353l-21.9 19 13.9 25.4a56.2 56.2 0 016.9 27.3c0 16.5-7.2 32.2-19.6 43l-21.9 19 13.9 25.4a56.2 56.2 0 016.9 27.3c0 16.5-7.2 32.2-19.6 43l-21.9 19 13.9 25.4a56.2 56.2 0 016.9 27.3c0 22.4-13.2 42.6-33.6 51.8H329V564.8l99.5-360.5a44.1 44.1 0 0142.2-32.3c7.6 0 15.1 2.2 21.1 6.7 9.9 7.4 15.2 18.6 14.6 30.5l-9.6 198.4h314.4C829 418.5 840 436.9 840 456c0 16.5-7.2 32.1-19.6 43z" />
          </svg>
          <span className="text-gray-600">Like</span>
        </button>
      </div>
    );
  };

  export default Like;
