import ConferenceNavbar from "../components/ConferenceNavbar";
import { useLocation, useNavigate } from "react-router-dom";
import { createContext, useEffect, useState } from "react";
import Posters from "../components/Posters";
import Photos from "../components/Photos";
import Patrons from "../components/Patrons";
import Weather from "../components/Weather";

interface Conference {
  idKonferencija: string;
  imeKonferencija: string;
  mjesto: string;
  datumVrijemePocetka: Date;
  datumVrijemeZavrsetka: Date;
}

function Conference() {
  const location = useLocation();
  const [componentToShow, setComponentToShow] = useState("conference");
  const [conference, setConference] = useState<Conference>({
    idKonferencija: "",
    imeKonferencija: "",
    mjesto: "",
    datumVrijemePocetka: new Date(),
    datumVrijemeZavrsetka: new Date(),
  });

  let key = "credentials";
  let credentials = localStorage.getItem(key);

  const navigate = useNavigate();
  const conferenceId = location.state;
  console.log("konfId" + conferenceId);

  useEffect(() => {
    fetch(`api/konferencije/${conferenceId}`, {
      headers: {
        Authorization: `Basic ${credentials}`,
      },
    })
      .then((res) => res.json())
      .then((conference) => setConference(conference));
  }, []);

  function handleHome() {
    // log out
    navigate("/");
  }

  function handleConference() {
    setComponentToShow("conference");
  }

  function handlePosters() {
    setComponentToShow("posters");
  }

  function handlePhotos() {
    setComponentToShow("photos");
  }

  function handlePatrons() {
    setComponentToShow("patrons");
  }

  return (
    <>
      <ConferenceNavbar
        conference={conference}
        handleClickHome={handleHome}
        handleClickConference={handleConference}
        handleClickPosters={handlePosters}
        handleClickPhotos={handlePhotos}
        handleClickPatrons={handlePatrons}
      />
      {componentToShow === "conference" && (
        <Weather location={conference.mjesto} />
      )}
      {componentToShow === "posters" && <Posters />}
      {componentToShow === "photos" && <Photos />}
      {componentToShow === "patrons" && <Patrons />}
    </>
  );
}

export default Conference;

export const ConferenceContext = createContext(null);
