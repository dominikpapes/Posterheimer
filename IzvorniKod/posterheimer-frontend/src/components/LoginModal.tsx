import React, { useState, useContext } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

import { SelectedConferenceContext } from "./ConferencesList";

interface Props {
  showModal: boolean;
  handleClose: () => void;
}

function LoginModal({ showModal, handleClose }: Props) {
  const conference = useContext(SelectedConferenceContext);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const handleLogin = async (event: React.FormEvent<HTMLFormElement>) => {
    // Perform login logic
    event.preventDefault();

    const credentials = btoa(`${username}:${password}`);
    console.log(credentials);
    let key = "credentials";

    localStorage.setItem(key, credentials);

    const response = await fetch(
      `/api/konferencije/${conference?.idKonferencija}`,
      {
        headers: {
          Authorization: `Basic ${credentials}`,
        },
      }
    );

    if (response.ok) {
      const data = await response.json();
      navigate("/conference", { state: data.idKonferencija });
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
