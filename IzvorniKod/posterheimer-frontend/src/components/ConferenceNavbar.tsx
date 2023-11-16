import { Navbar, Nav, Container } from "react-bootstrap";

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
          </Nav>
        </Container>
      </Navbar>
    </>
  );
}

export default ConferenceNavbar;
