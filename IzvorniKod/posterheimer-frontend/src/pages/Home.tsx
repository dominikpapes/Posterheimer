import { useEffect, useState } from "react";
import { Navbar, Container } from "react-bootstrap";
import ConferencesList from "../components/ConferencesList";
import Titlebar from "../components/Titlebar";
import { useNavigate } from "react-router-dom";

interface Conference {
  idKonferencija: number;
  imeKonferencija: string;
}

const mock_conference = [
  {
    idKonferencija: 1,
    imeKonferencija: "Mock Conference 1",
  },
  {
    idKonferencija: 2,
    imeKonferencija: "Mock Conference 2",
  },
];

function Home() {
  const [conferences, setConferences] = useState<Conference[]>([]);
  const navigate = useNavigate();

  useEffect(() => {
    // fetch("/api/konferencije")
    //   .then((res) => res.json())
    //   .then((confs) => setConferences(confs));
    setConferences(mock_conference);
  }, []);

  const onSelectKonferencija = () => {};
  return (
    <>
      <Titlebar></Titlebar>
      <div className="container text-center">
        <ConferencesList
          conferences={conferences}
          heading="Dostupne konferencije"
          onSelectItem={onSelectKonferencija}
        ></ConferencesList>
        <i
          className="fa-solid fa-square-plus fa-5x"
          title="Nova konferencija"
          onClick={() => navigate("/newConference")}
        ></i>
      </div>
    </>
  );
}

export default Home;
