import React, { useState, useEffect } from 'react';

function Clock({ endTime }) {

    const [days, setDays] = useState();
    const [hours, setHours] = useState();
    const [minutes, setMinutes] = useState();
    const [seconds, setSeconds] = useState();

    let interval;

    const countDown = () => {
        interval = setInterval(() => {
            const now = new Date().getTime();
            const different = endTime - now;
            const days = Math.floor(different / (1000 * 60 * 60 * 24));
            const hours = Math.floor((different % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            const minutes = Math.floor((different % (1000 * 60 * 60)) / (1000 * 60));
            const seconds = Math.floor((different % (1000 * 60)) / 1000);

            if (endTime < 0) clearInterval(interval.current);
            else {
                setDays(days);
                setHours(hours);
                setMinutes(minutes);
                setSeconds(seconds);
            }
        });
    };

    useEffect(() => {
        countDown();
    }, []);

    return (
        <div className="clock__wrapper flex items-center space-x-3">
            <div className="clock__data flex items-center space-x-3">
                <div className="text-center">
                    <h1 className="text-lg mb-2">{days} </h1>
                    <h5 className="text-sm">Days </h5>
                </div>
                <span className="text-lg">:</span>
            </div>
            <div className="clock__data flex items-center space-x-3">
                <div className="text-center">
                    <h1 className="text-lg mb-2">{hours} </h1>
                    <h5 className="text-sm">Hours </h5>
                </div>
                <span className="text-lg">:</span>
            </div>
            <div className="clock__data flex items-center space-x-3">
                <div className="text-center">
                    <h1 className="text-lg mb-2">{minutes} </h1>
                    <h5 className=" text-sm">Minutes </h5>
                </div>
                <span className="text-lg">:</span>
            </div>
            <div className="clock__data flex items-center space-x-3">
                <div className="text-center">
                    <h1 className=" text-lg mb-2">{seconds} </h1>
                    <h5 className=" text-sm">Seconds </h5>
                </div>
            </div>
        </div>
    );
}

export default Clock;
