import { Navbar, Nav, Container, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

interface Props {
  conference: any; // konferencija
  handleClickHome: () => void;
  handleClickConference: () => void;
  handleClickPosters: () => void;
  handleClickPhotos: () => void;
  handleClickPatrons: () => void;
}

function ConferenceNavbar({
  conference,
  handleClickHome,
  handleClickConference,
  handleClickPhotos,
  handleClickPosters,
  handleClickPatrons,
}: Props) {
  const navigate = useNavigate();
  const handleRegister = () => {
    navigate("/register", {
      state: {
        idKonferencija: conference.idKonferencija,
        imeKonferencija: conference.imeKonferencija,
        mjesto: conference.mjesto,
        datumVrijemePocetka: conference.datumVrijemePocetka,
        datumVrijemeZavrsetka: conference.datumVrijemeZavrsetka,
        // Add other properties as needed
      },
    });
  };
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
          <Nav className="me-auto">
            <Nav.Link onClick={handleClickConference}>
              {conference.imeKonferencija}
            </Nav.Link>
            <Nav.Link onClick={handleClickPosters}>Posteri</Nav.Link>
            <Nav.Link onClick={handleClickPhotos}>Fotografije</Nav.Link>
            <Nav.Link onClick={handleClickPatrons}>Pokrovitelji</Nav.Link>
            <Button onClick={handleRegister}>Registriraj se</Button>
          </Nav>
        </Container>
      </Navbar>
    </>
  );
}

export default ConferenceNavbar;
