import ConferenceNavbar from "../components/ConferenceNavbar";
import { useLocation, useNavigate } from "react-router-dom";
import { createContext, useEffect, useState } from "react";
import Weather from "../components/Weather";

import loading from "../../public/spinner.gif";
import { Card } from "react-bootstrap";

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

  // let key = "credentials";
  // let credentials = localStorage.getItem(key);

  const navigate = useNavigate();
  // const conferenceId = location.state;
  // console.log("konfId" + conferenceId);

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
      {isLoading ? (
        <div className="true-center">
          <img src={loading} alt="loading" />
        </div>
      ) : (
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
            <iframe
              className="video"
              src="https://www.youtube.com/embed/tgbNymZ7vqY"
              title="conference-video"
            ></iframe>
          </div>
        </>
      )}
    </>
  );
}
export default Conference;
