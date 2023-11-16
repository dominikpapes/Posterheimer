import React, { useState, useContext } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

import { SelectedConferenceContext } from "./ListGroup";

function LoginModal() {
  const conference = useContext(SelectedConferenceContext);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const handleLogin = async (event: React.FormEvent<HTMLFormElement>) => {
    // Perform login logic
    event.preventDefault();
    const credentials = btoa(`${username}:${password}`);
    const response = await fetch("/api/konferencije/2", {
      headers: {
        Authorization: `Basic ${credentials}`,
      },
    });

    if (response.ok) {
      const data = await response.text();
      console.log(data);
      navigate("/conference", { state: conference?.idKonferencija });
    } else {
      console.error("Authentication failed");
    }
    // Close the modal after logging in
  };

  return (
    <>
      <Modal.Header closeButton>
        <Modal.Title>Login - {conference?.imeKonferencija}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form onSubmit={handleLogin}>
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
          <Button variant="primary" type="submit">
            Login
          </Button>
        </Form>
      </Modal.Body>
    </>
  );
}

export default LoginModal;
