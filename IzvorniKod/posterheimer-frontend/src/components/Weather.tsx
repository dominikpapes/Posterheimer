import { useEffect, useState } from "react";

import "../styles.css";
import { Table } from "react-bootstrap";

const api = {
  key: "aa1ef53b0bb7d2e72fe42357e59adacb",
  base: "https://api.openweathermap.org/data/2.5/",
};

interface WeatherData {
  temperature: number;
  icon: string;
  time: string;
}

interface Props {
  location: string;
}

function Weather({ location }: Props) {
  const [weather, setWeather] = useState<WeatherData | null>(null);
  const [forecasts, setForecasts] = useState<WeatherData[]>([]);
  const [iconUrl, setIconUrl] = useState("");

  function loadWeatherData() {
    fetch(`${api.base}weather?q=${location}&units=metric&APPID=${api.key}`)
      .then((response) => response.json())
      .then((data) => {
        console.log("Weather: ", data);
        const weatherData: WeatherData = {
          temperature: data.main.temp,
          icon: data.weather[0].icon,
          time: data.dt_txt,
        };
        setWeather(weatherData);
      });
  }

  async function fetchForecast() {
    fetch(`${api.base}forecast?q=${location}&units=metric&APPID=${api.key}`)
      .then((response) => response.json())
      .then((data) => {
        console.log("Forecast: ", data);
        data.list.forEach((element: any) => {
          const weatherData: WeatherData = {
            temperature: element.main.temp,
            icon: element.weather[0].icon,
            time: element.dt_txt,
          };
          setForecasts((currentForecasts) => [
            ...currentForecasts,
            weatherData,
          ]);
        });
      });
  }

  useEffect(() => {
    // loadWeatherData();
    fetchForecast();
  }, []);

  console.log(forecasts);

  return (
    <>
      <div className="weather-card card">
        <img
          src={`https://openweathermap.org/img/wn/${forecasts[0]?.icon}@2x.png`}
          alt=""
          className="weather-image"
        />
        <h2>{location}</h2>
        <h3>{forecasts[0]?.temperature}°C</h3>
      </div>
      <div className="card forecast-container">
        <Table responsive>
          <tbody>
            <tr>
              {forecasts.map((forecast, index) => (
                <td key={index}>
                  <div className="forecast-card">
                    <img
                      src={`https://openweathermap.org/img/wn/${forecast?.icon}@2x.png`}
                      alt=""
                      className="weather-image"
                    />
                    <span>{forecast.time.split(" ")[0]}</span>
                    <span>{forecast.time.split(" ")[1]}</span>
                    <span>{forecast?.temperature}°C</span>
                  </div>
                </td>
              ))}
            </tr>
          </tbody>
        </Table>
      </div>

      {/* <div className="forecast-cdivontainer">
          <div className="row-fluid">
            {forecasts.map((forecast, index) => (
              <div className="col-lg-3" key={index}>
                <img
                  src={`https://openweathermap.org/img/wn/${forecast?.icon}@2x.png`}
                  alt=""
                  className="weather-image w-25"
                />
                <h3>{forecast?.temperature}°C</h3>
              </div>
            ))}
          </div>
        </div> */}
    </>
  );
}

export default Weather;
