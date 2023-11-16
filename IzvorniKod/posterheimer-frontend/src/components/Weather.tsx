import { useEffect, useState } from "react";
import axios from "axios";

import "../styles.css";

import clear_icon from "../assets/clear.png";
import cloud_icon from "../assets/cloud.png";
import drizzle_icon from "../assets/drizzle.png";
import humidity_icon from "../assets/humidity.png";
import rain_icon from "../assets/rain.png";
import snow_icon from "../assets/snow.png";
import wind_icon from "../assets/wind.png";

const api = {
  key: "aa1ef53b0bb7d2e72fe42357e59adacb",
  base: "https://api.openweathermap.org/data/2.5/",
};

interface WeatherData {
  main: {
    temp: number;
  };
  name: string;
  weather: [
    {
      icon: string;
    }
  ];
}

interface Props {
  location: string;
}

function Weather({ location }: Props) {
  const [weather, setWeather] = useState<WeatherData | null>(null);

  function loadWeatherData() {
    axios
      .get<WeatherData>(
        `${api.base}weather?q=Zagreb&units=metric&APPID=${api.key}`
      )
      .then((response) => {
        setWeather(response.data);
      });
  }

  useEffect(loadWeatherData, []);

  const iconUrl = `https://openweathermap.org/img/wn/${weather?.weather[0].icon}@2x.png`;

  return (
    <>
      <div className="d-flex justify-content-center py-5">
        <div className="weather-card card w-25">
          <img src={iconUrl} alt="" className="weather-image w-25" />
          <h2>{location}</h2>
          <h3>{weather?.main.temp}°C</h3>
        </div>
      </div>
    </>
  );
}

export default Weather;
