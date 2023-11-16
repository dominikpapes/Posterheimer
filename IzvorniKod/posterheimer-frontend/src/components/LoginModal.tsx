import React, { useState, useContext } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

import { SelectedConferenceContext } from "./ListGroup";
import { LoginScreenFunctionsContext } from "./ConferenceAcessModal";

function LoginModal() {
  const conference = useContext(SelectedConferenceContext);
  const functions = useContext(LoginScreenFunctionsContext);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  function handleLogin() {
    // Perform login logic
    navigate("/conference", { state: conference });
    console.log("Logging in with:", username, password);
    // Close the modal after logging in
  }

  return (
    <>
      <Modal.Header closeButton>
        <Modal.Title>Login - {conference}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group className="mb-3" controlId="username">
            <Form.Label>Email:</Form.Label>
            <Form.Control
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="password">
            <Form.Label>Lozinka:</Form.Label>
            <Form.Control
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="primary" onClick={handleLogin}>
          Login
        </Button>
      </Modal.Footer>
    </>
  );
}

export default LoginModal;
