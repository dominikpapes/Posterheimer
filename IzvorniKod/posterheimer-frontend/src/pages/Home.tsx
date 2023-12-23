import { useEffect, useState } from "react";
import { Navbar, Container } from "react-bootstrap";
import ConferencesList from "../components/ConferencesList";

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

  useEffect(() => {
    // fetch("/api/konferencije")
    //   .then((res) => res.json())
    //   .then((confs) => setConferences(confs));
    setConferences(mock_conference);
  }, []);

  const onSelectKonferencija = () => {};
  return (
    <>
      <Navbar className="bg-body-tertiary" bg="dark" data-bs-theme="dark">
        <Container>
          <Navbar.Brand href="/">
            <img
              alt=""
              src="../../public/logo.png"
              width="30"
              height="30"
              className="d-inline-block align-top"
            />{" "}
            Posterheimer
          </Navbar.Brand>
        </Container>
      </Navbar>
      <div className="container text-center">
        <ConferencesList
          conferences={conferences}
          heading="Dostupne konferencije"
          onSelectItem={onSelectKonferencija}
        ></ConferencesList>
      </div>
    </>
  );
}

export default Home;
