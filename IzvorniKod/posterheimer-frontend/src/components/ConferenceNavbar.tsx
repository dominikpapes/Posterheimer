import {
  Navbar,
  Nav,
  Container,
  Button,
  Modal,
  Dropdown,
  NavDropdown,
} from "react-bootstrap";
import { useNavigate, Link, NavLink, useLocation } from "react-router-dom";
import LoginModal from "./LoginModal";
import { useState } from "react";

const VISITOR = import.meta.env.VITE_VISITOR;
const REGISTERED = import.meta.env.VITE_REGISTERED;
const ADMIN = import.meta.env.VITE_ADMIN;
const SUPERUSER = import.meta.env.VITE_SUPERUSER;

function ConferenceNavbar() {
  const conference = localStorage.getItem("conference");
  const [showModal, setShowModal] = useState(false);

  const navigate = useNavigate();

  let userRole = localStorage.getItem("userRole");
  let itemContent;

  function logout() {
    localStorage.clear();
    navigate("/");
  }

  if (userRole === VISITOR) {
    itemContent = (
      <>
        <Button>
          <Link to="/register" className="text-link">
            Registracija
          </Link>
        </Button>
      </>
    );
  } else if (userRole === REGISTERED) {
    itemContent = (
      <NavDropdown
        id="user-dropdown"
        title={userRole}
        menuVariant="dark"
        className="justify-content-end"
      >
        <NavDropdown.Item onClick={logout}>
          Odjava <i className="fa-solid fa-right-from-bracket ml-2"></i>
        </NavDropdown.Item>
      </NavDropdown>
    );
  } else {
    itemContent = (
      <NavDropdown
        id="user-dropdown"
        title={userRole}
        menuVariant="dark"
        className="justify-content-end"
      >
        <NavDropdown.Item as={Link} to="/users">
          Korisnici
        </NavDropdown.Item>
        <NavDropdown.Item onClick={logout}>
          Odjava <i className="fa-solid fa-right-from-bracket ml-2"></i>
        </NavDropdown.Item>
      </NavDropdown>
    );
  }
  return (
    <>
      <Navbar
        collapseOnSelect
        expand="lg"
        className="bg-body-tertiary"
        bg="dark"
        data-bs-theme="dark"
      >
        <Container>
          <Navbar.Brand>
            <img
              alt=""
              src="../../public/logo.png"
              width="30"
              height="30"
              className="d-inline-block align-top"
            />{" "}
            Posterheimer
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="navbar" />
          <Navbar.Collapse id="navbar">
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
              <Nav.Link as={Link} to="/sponsors" className="text-link">
                Pokrovitelji
              </Nav.Link>
            </Nav>
            <Nav className="justify-content-end">{itemContent}</Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </>
  );
}
export default ConferenceNavbar;
