import { useEffect, useState } from "react";
import { Table } from "react-bootstrap";
import loading from "../../public/spinner.gif";
import "../styles.css";

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
  const [isLoading, setIsLoading] = useState(true);
  const [forecasts, setForecasts] = useState<WeatherData[]>([]);

  async function fetchForecast() {
    fetch(`${api.base}forecast?q=${location}&units=metric&APPID=${api.key}`)
      .then((response) => response.json())
      .then((data) => {
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
    fetchForecast().then(() => setIsLoading(false));
  }, []);

  return (
    <>
      <div className="weather-card card my-1">
        <img
          src={`https://openweathermap.org/img/wn/${forecasts[0]?.icon}@2x.png`}
          alt=""
          className="weather-image"
        />
        <h2>{location}</h2>
        <h3>{forecasts[0]?.temperature}°C</h3>
      </div>
      <div className="card forecast-container my-1">
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
    </>
  );
}

export default Weather;
