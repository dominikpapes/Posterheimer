import React, { useState, useContext, useEffect } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const VISITOR = import.meta.env.VITE_VISITOR;
const REGISTERED = import.meta.env.VITE_REGISTERED;
const ADMIN = import.meta.env.VITE_ADMIN;
const SUPERUSER = import.meta.env.VITE_SUPERUSER;

const SUPERUSER_ID = import.meta.env.VITE_SUPERUSER_ID;
const SUPERUSER_IME = import.meta.env.VITE_SUPERUSER_IME;

interface Props {
  conferenceId: number;
  conferenceName: string;
}

function LoginModal({ conferenceId, conferenceName }: Props) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  async function handleLogin(event: React.FormEvent<HTMLFormElement>) {
    // Perform login logic
    event.preventDefault();

    localStorage.setItem("userRole", VISITOR);
    if (
      conferenceId === Number(localStorage.getItem("conferenceId")) &&
      conferenceName === localStorage.getItem("conferenceName") &&
      conferenceId &&
      conferenceName
    ) {
      window.location.reload();
    } else {
      localStorage.setItem("conferenceId", conferenceId.toString());
      localStorage.setItem("conferenceName", conferenceName.toString());
      navigate("/conference");
    }
  }

  return (
    <>
      <Modal.Header closeButton>
        <Modal.Title>Login - {conferenceName}</Modal.Title>
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
