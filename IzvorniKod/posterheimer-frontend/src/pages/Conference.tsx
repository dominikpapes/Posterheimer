import ConferenceNavbar from "../components/ConferenceNavbar";
import { useLocation, useNavigate } from "react-router-dom";
import { createContext, useEffect, useState } from "react";
import Weather from "../components/Weather";

import loading from "../../public/spinner.gif";
import { Card, Offcanvas } from "react-bootstrap";
import PleaseLogin from "../components/PleaseLogin";

const VISITOR = import.meta.env.VITE_VISITOR;

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
  const [isLoading, setIsLoading] = useState(true);
  const [conference, setConference] = useState<Conference>({
    idKonferencija: "",
    imeKonferencija: "",
    mjesto: "",
    datumVrijemePocetka: new Date(),
    datumVrijemeZavrsetka: new Date(),
  });
  const userRole = localStorage.getItem("userRole");
  const showLoginPrompt = userRole === VISITOR;

  useEffect(() => {
    setIsLoading(true);
    setConference(mock_conference);
    setIsLoading(false);
  }, []);

  useEffect(() => {
    console.log(conference);
  }, [conference]);

  return (
    <>
      <ConferenceNavbar />
      <>
        <div className="conference-content">
          <div className="conference-info">
            <h1>{conference.imeKonferencija}</h1>
            <div>
              {conference.datumVrijemePocetka.toDateString()} -{" "}
              {conference.datumVrijemeZavrsetka.toDateString()}
            </div>
          </div>
          {conference.mjesto && <Weather location={conference.mjesto} />}
          {showLoginPrompt ? (
            <PleaseLogin />
          ) : (
            <iframe
              className="video"
              src="https://www.youtube.com/embed/tgbNymZ7vqY"
              title="conference-video"
            ></iframe>
          )}
        </div>
      </>
    </>
  );
}
export default Conference;
