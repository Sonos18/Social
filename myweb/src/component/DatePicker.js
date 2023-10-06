import React, { useState } from 'react';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';


const MyDatePicker = () => {
    const [selectedDate, setSelectedDate] = useState(null);
  
    const handleDateChange = (date) => {
      setSelectedDate(date);
    };
  
    return (
      <div className="p-4">
        <h2 className="text-xl font-semibold mb-2">Select a Date</h2>
        <DatePicker
          selected={selectedDate}
          onChange={handleDateChange}
          dateFormat="yyyy/MM/dd" // Customize the date format
          className="w-full p-2 border rounded-lg"
        />
        {selectedDate && (
          <p className="mt-4">
            Selected Date: {selectedDate.toLocaleDateString()}
          </p>
        )}
      </div>
    );
  };
  
  export default MyDatePicker;
  