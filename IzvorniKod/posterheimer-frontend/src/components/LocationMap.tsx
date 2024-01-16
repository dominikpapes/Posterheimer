import React, { useEffect, useState } from "react";
import { MapContainer, Marker, Popup, TileLayer } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import Loading from "./Loading";

interface Props {
  adresa: string;
  grad: string;
  pbr: string;
  konfIme: string;
}

function LocationMap({ adresa, grad, pbr, konfIme }: Props) {
  const [hasError, setHasError] = useState(false);
  const [location, setLocation] = useState({
    isReady: false,
    latitude: 0,
    longitude: 0,
    display_name: "",
  });

  async function getCoord() {
    try {
      let url = `https://nominatim.openstreetmap.org/search?city='${grad}'&street='${adresa}&postalcode='${pbr}'&format=json&limit=1`;

      fetch(url, {
        method: "GET",
        mode: "cors",
      })
        .then((response) => {
          if (response.ok) {
            return response.json();
          }
        })
        .then((data) => {
          setLocation({
            latitude: data[0].lat,
            longitude: data[0].lon,
            display_name: data[0].display_name,
            isReady: true,
          });
        });
    } catch (error) {
      setHasError(true);
    }
  }
  useEffect(() => {
    getCoord();
  }, []);
  return (
    <>
      {location.isReady && (
        <MapContainer
          center={[location.latitude, location.longitude]}
          zoom={10}
          scrollWheelZoom={true}
        >
          <TileLayer
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />
          <Marker position={[location.latitude, location.longitude]}>
            <Popup>{konfIme}</Popup>
          </Marker>
        </MapContainer>
      )}
    </>
  );
}

export default LocationMap;
