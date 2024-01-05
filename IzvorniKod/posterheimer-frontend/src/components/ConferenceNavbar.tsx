import { Navbar, Nav, Container, Button, Modal } from "react-bootstrap";
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
  } else {
    itemContent = (
      <>
        <span className="text-light">
          <i className="fa-solid fa-circle-user mx-2" />
          <span>{userRole}</span>
        </span>
      </>
    );
  }
  return (
    <>
      <Navbar className="bg-body-tertiary" bg="dark" data-bs-theme="dark">
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
          {itemContent}
          <i
            className="fa-solid fa-right-from-bracket mx-4 navbar-icon"
            onClick={logout}
          ></i>
        </Container>
      </Navbar>
    </>
  );
}
export default ConferenceNavbar;
