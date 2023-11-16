import { Navbar, Nav, Container, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

interface Props {
  conference: string | undefined;
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
    navigate("/register");
  };
  return (
    <>
      <Navbar bg="dark" data-bs-theme="dark">
        <Container>
          <Navbar.Brand onClick={handleClickHome}>Posterheimer</Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Link onClick={handleClickConference}>{conference}</Nav.Link>
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
