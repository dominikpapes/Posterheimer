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

  const userRole = localStorage.getItem("userRole");
  const userName = localStorage.getItem("userName");
  const userSurname = localStorage.getItem("userSurname");
  const title = `${userName} ${userSurname}`;
  const conferenceId = Number(localStorage.getItem("conferenceId"));
  const conferenceName = localStorage.getItem("conferenceName") || "";
  let itemContent;

  function logout() {
    localStorage.clear();
    navigate("/");
  }

  if (userRole === VISITOR) {
    itemContent = (
      <>
        <NavDropdown.Item as={Link} to="/register">
          Registracija
        </NavDropdown.Item>
        <NavDropdown.Item onClick={() => setShowModal(true)}>
          Prijava
        </NavDropdown.Item>
        <NavDropdown.Item onClick={logout}>
          Odjava <i className="fa-solid fa-right-from-bracket ml-2"></i>
        </NavDropdown.Item>
      </>
    );
  } else if (userRole === REGISTERED) {
    itemContent = (
      <NavDropdown.Item onClick={logout}>
        Odjava <i className="fa-solid fa-right-from-bracket ml-2"></i>
      </NavDropdown.Item>
    );
  } else {
    itemContent = (
      <>
        <NavDropdown.Item as={Link} to="/users">
          Korisnici
        </NavDropdown.Item>
        <NavDropdown.Item onClick={logout}>
          Odjava <i className="fa-solid fa-right-from-bracket ml-2"></i>
        </NavDropdown.Item>
      </>
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
            </Nav>
            <Nav className="justify-content-end">
              <NavDropdown
                id="user-dropdown"
                title={title}
                menuVariant="dark"
                className="justify-content-end"
              >
                {itemContent}
              </NavDropdown>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>

      <Modal show={showModal} onHide={() => setShowModal(false)}>
        <LoginModal
          conferenceId={conferenceId}
          conferenceName={conferenceName}
        ></LoginModal>
      </Modal>
    </>
  );
}
export default ConferenceNavbar;
