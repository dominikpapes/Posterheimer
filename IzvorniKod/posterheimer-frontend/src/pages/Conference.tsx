import ConferenceNavbar from "../components/ConferenceNavbar";
import { useLocation, useNavigate } from "react-router-dom";
import { createContext, useEffect, useState } from "react";
import Posters from "./Posters";
import Photos from "./Photos";
import Patrons from "../components/Patrons";
import Weather from "../components/Weather";
import Register from "./Register";

interface Conference {
  idKonferencija: string;
  imeKonferencija: string;
  mjesto: string;
  datumVrijemePocetka: Date;
  datumVrijemeZavrsetka: Date;
}

const mock_conference: Conference = {
  idKonferencija: "1",
  imeKonferencija: "Mock Conference",
  mjesto: "Zagreb",
  datumVrijemePocetka: new Date(),
  datumVrijemeZavrsetka: new Date(new Date().setDate(new Date().getDate() + 7)),
};

function fetchConference(conferenceId: number) {
  let conference;
  fetch(`api/konferencije/${conferenceId}`, {
    headers: {
      // Authorization: `Basic ${credentials}`,
    },
  })
    .then((res) => res.json())
    .then((data) => (conference = data));
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

  // let key = "credentials";
  // let credentials = localStorage.getItem(key);

  const navigate = useNavigate();
  // const conferenceId = location.state;
  // console.log("konfId" + conferenceId);

  useEffect(() => {
    setConference(mock_conference);
  }, []);

  useEffect(() => {
    console.log(conference);
  }, [conference]);

  return (
    <>
      <ConferenceNavbar />
      {conference.mjesto && componentToShow === "conference" && (
        <Weather location={conference.mjesto} />
      )}
    </>
  );
}

export default Conference;

export const ConferenceContext = createContext(null);
