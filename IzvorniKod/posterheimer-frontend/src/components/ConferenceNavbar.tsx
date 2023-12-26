import { Navbar, Nav, Container, Button } from "react-bootstrap";
import { useNavigate, Link, NavLink, useLocation } from "react-router-dom";

function ConferenceNavbar() {
  const conference = localStorage.getItem("conference");
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
            <Nav.Link as={Link} to="/conference" className="text-link">
              Konferencija
            </Nav.Link>
            <Nav.Link as={Link} to="/posters" className="text-link">
              Posteri
            </Nav.Link>
            <Nav.Link as={Link} to="/photos" className="text-link">
              Fotografije
            </Nav.Link>
            <Button>
              <Link to="/register" className="text-link">
                Registracija
              </Link>
            </Button>
          </Nav>
        </Container>
      </Navbar>
    </>
  );
}
export default ConferenceNavbar;
