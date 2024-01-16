import ConferenceNavbar from "../components/ConferenceNavbar";
import { useLocation, useNavigate } from "react-router-dom";
import { createContext, useEffect, useState } from "react";
import Weather from "../components/Weather";

import loading from "../../public/spinner.gif";
import { Card, CardTitle, Offcanvas, Spinner } from "react-bootstrap";
import PleaseLogin from "../components/PleaseLogin";
import Loading from "../components/Loading";
import LocationMap from "../components/LocationMap";

const VISITOR = import.meta.env.VITE_VISITOR;

interface Conference {
  idKonferencija: string;
  imeKonferencija: string;
  mjesto: string;
  adresa: string;
  zipCode: string;
  datumVrijemePocetka: Date;
  datumVrijemeZavrsetka: Date;
  videoUrl: string;
}

const empty_conference: Conference = {
  idKonferencija: "",
  imeKonferencija: "",
  mjesto: "",
  adresa: "",
  zipCode: "",
  datumVrijemePocetka: new Date(),
  datumVrijemeZavrsetka: new Date(),
  videoUrl: "",
};

function Conference() {
  const userRole = localStorage.getItem("userRole");
  const conferenceId = Number(localStorage.getItem("conferenceId"));
  const [isLoading, setIsLoading] = useState(true);
  const [conference, setConference] = useState<Conference>(empty_conference);
  const showLoginPrompt = userRole === VISITOR;

  const [dateStart, setDateStart] = useState(new Date());
  const [dateEnd, setDateEnd] = useState(new Date());

  const dateOptions: Intl.DateTimeFormatOptions = {
    weekday: "short", // 'short' or 'long' for short or full weekday names
    year: "numeric",
    month: "short",
    day: "numeric",
    hour: "numeric",
    minute: "numeric",
    second: "numeric",
  };

  async function getConferences(conferenceId: number) {
    let token = localStorage.getItem("jwtToken");
    const response = await fetch(`/api/konferencije/${conferenceId}`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    const data = await response.json();
    return data;
  }

  useEffect(() => {
    setIsLoading(true);
    getConferences(conferenceId).then((data) => {
      setConference(data);
      setIsLoading(false);
    });
  }, []);

  useEffect(() => {
    setDateStart(new Date(conference.datumVrijemePocetka));
    setDateEnd(new Date(conference.datumVrijemeZavrsetka));
  }, [conference]);

  return (
    <>
      <ConferenceNavbar />

      <>
        {isLoading ? (
          <Loading />
        ) : (
          <div className="conference-content">
            <Card className="conference-info my-2 p-2">
              <CardTitle>{conference.imeKonferencija.toUpperCase()}</CardTitle>
              <Card.Body>
                {dateStart.toLocaleString("hr-HR", dateOptions)} -{" "}
                {dateEnd.toLocaleString("hr-HR", dateOptions)}
                <LocationMap
                  adresa={conference.adresa}
                  grad={conference.mjesto}
                  pbr={conference.zipCode}
                  konfIme={conference.imeKonferencija}
                />
                <Card className="location-container mt-2">
                  <a
                    href={`https://maps.google.com/?q=${conference.adresa}+${conference.mjesto}`}
                    target="_blank"
                  >
                    <i className="fa-solid fa-location-dot fa-2x"></i>
                    <br />
                    {conference.adresa}
                    <br />
                    {conference.mjesto}
                    <br />
                    {conference.zipCode}
                  </a>
                </Card>
              </Card.Body>
            </Card>
            {conference.mjesto && <Weather location={conference.mjesto} />}
            {showLoginPrompt ? (
              <PleaseLogin />
            ) : (
              <iframe
                className="video mb-3"
                src={conference.videoUrl}
                title="conference-video"
              ></iframe>
            )}
          </div>
        )}
      </>
    </>
  );
}
export default Conference;
