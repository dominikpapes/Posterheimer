import { useEffect, useState } from "react";

import "../styles.css";

const api = {
  key: "aa1ef53b0bb7d2e72fe42357e59adacb",
  base: "https://api.openweathermap.org/data/2.5/",
};

interface WeatherData {
  temperature: number;
  icon: string;
}

interface Props {
  location: string;
}

function Weather({ location }: Props) {
  const [weather, setWeather] = useState<WeatherData | null>(null);

  // function loadWeatherData_old() {
  //   axios
  //     .get<WeatherData>(
  //       `${api.base}weather?q=Zagreb&units=metric&APPID=${api.key}`
  //     )
  //     .then((response) => {
  //       setWeather(response.data);
  //     });
  // }

  function loadWeatherData() {
    fetch(`${api.base}weather?q=${location}&units=metric&APPID=${api.key}`)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        const weatherData: WeatherData = {
          temperature: data.main.temp,
          icon: data.weather[0].icon,
        };
        setWeather(weatherData);
      });
  }

  useEffect(loadWeatherData, []);

  const iconUrl = `https://openweathermap.org/img/wn/${weather?.icon}@2x.png`;

  return (
    <>
      <div className="d-flex justify-content-center py-5">
        <div className="weather-card card w-25">
          <img src={iconUrl} alt="" className="weather-image w-25" />
          <h2>{location}</h2>
          <h3>{weather?.temperature}°C</h3>
        </div>
      </div>
    </>
  );
}

export default Weather;
