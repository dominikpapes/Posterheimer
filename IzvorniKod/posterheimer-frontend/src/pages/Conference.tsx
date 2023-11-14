import ConferenceNavbar from "../components/ConferenceNavbar";
import { useLocation, useNavigate } from "react-router-dom";
import { useState } from "react";
import Posters from "../components/Posters";
import Photos from "../components/Photos";
import Patrons from "../components/Patrons";

const api = {
  key: "aa1ef53b0bb7d2e72fe42357e59adacb",
  base: "https://api.openweathermap.org/data/2.5",
};

function Conference() {
  const location = useLocation();
  const [showConference, setShowConference] = useState(true);
  const [showPosters, setShowPosters] = useState(false);
  const [showPhotos, setShowPhotos] = useState(false);
  const [showPatrons, setShowPatrons] = useState(false);

  const navigate = useNavigate();

  function handleHome() {
    // log out
    navigate("/");
  }

  function handleConference() {
    setShowConference(true);
    setShowPosters(false);
    setShowPhotos(false);
    setShowPatrons(false);
  }

  function handlePosters() {
    setShowConference(false);
    setShowPosters(true);
    setShowPhotos(false);
    setShowPatrons(false);
  }

  function handlePhotos() {
    setShowConference(false);
    setShowPosters(false);
    setShowPhotos(true);
    setShowPatrons(false);
  }

  function handlePatrons() {
    setShowConference(false);
    setShowPosters(false);
    setShowPhotos(false);
    setShowPatrons(true);
  }

  const conferenceName = location.state;
  return (
    <>
      <ConferenceNavbar
        conference={conferenceName}
        handleClickHome={handleHome}
        handleClickConference={handleConference}
        handleClickPosters={handlePosters}
        handleClickPhotos={handlePhotos}
        handleClickPatrons={handlePatrons}
      />
      {showConference && <p>Informacije o konferenciji</p>}
      {showPosters && <Posters />}
      {showPhotos && <Photos />}
      {showPatrons && <Patrons />}
    </>
  );
}

export default Conference;
