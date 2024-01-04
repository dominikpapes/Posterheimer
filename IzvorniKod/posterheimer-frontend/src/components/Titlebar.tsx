import React from "react";
import { Navbar, Container } from "react-bootstrap";

export default function Titlebar() {
  return (
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
        <Navbar.Brand href="/users">
          <img
            alt=""
            src="../../public/user.png"
            width="30"
            height="30"
            className="d-inline-block align-top"
          />{" "}
          Users
        </Navbar.Brand>
      </Container>
    </Navbar>
  );
}
