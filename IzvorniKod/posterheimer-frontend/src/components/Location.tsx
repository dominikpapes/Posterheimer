import React, { useState } from "react";
import Map from "react-map-gl";

const TOKEN =
  "pk.eyJ1IjoiZHBhcGVzIiwiYSI6ImNscXBiYjVqbzFuMjQybHA5d2V0MmxvMjgifQ.8IfqNeIM9Fg1q7ZZ19dFLw";

function Location() {
  const [viewport, setViewport] = useState({
    latitude: 46.17221,
    longitude: 15.80634,
    zoom: 10,
    width: "100vw",
    height: "100vh",
  });

  return (
    <div>
      <Map {...viewport} mapboxAccessToken={TOKEN} />
    </div>
  );
}

export default Location;
