import React from "react";
import { Navbar, Container } from "react-bootstrap";
import logo from "/logo.png";

export default function Titlebar() {
  return (
    <Navbar className="bg-body-tertiary" bg="dark" data-bs-theme="dark">
      <Container>
        <Navbar.Brand href="/">
          <img
            alt=""
            src={logo}
            width="30"
            height="30"
            className="d-inline-block align-top"
          />{" "}
          Posterheimer
        </Navbar.Brand>
      </Container>
    </Navbar>
  );
}
