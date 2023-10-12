import {
  BarElement,
  CategoryScale,
  Chart as ChartJS,
  Legend,
  LinearScale,
  Title,
  Tooltip,
} from "chart.js";
import React from "react";
import { Bar } from "react-chartjs-2";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

const options = {
  responsive: true,
  plugins: {
    legend: {
      position: "top",
    },
    title: {
      display: true,
      text: "Chart.js Bar Chart",
    },
  },
};




const ChartForMonth = ({dataChart}) => {
  const data = {
    labels: ["January","February","March","April","May","June","July","August","September","October","November","December"],
    datasets: [
      {
        label: "Population (millions)",
        backgroundColor: ["#BC2A95", "#8D2095", "#5D1793", "#1553CC", "#4DB1C0","#409629","#7FC73D","#FFFE54","#F9CD46","#F29D38","#ED7326","#EC3225"],
        data:dataChart,
        // data: [2478, 2267, 734, 784, 433,400,300,333,2222,112,454,543],
      },
    ],
  };
  return <Bar options={options} data={data} />;
};

export default ChartForMonth;
