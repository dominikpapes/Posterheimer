import React, { useState, useContext, useEffect } from "react";
import { Modal, Button, Form, Spinner } from "react-bootstrap";
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

interface LoginData {
  username: string;
  password: string;
}

interface Credentials {
  jwtToken: string;
  role: string;
  ime: string;
  prezime: string;
  voted: string;
}

function LoginModal({ conferenceId, conferenceName }: Props) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [isLoggingIn, setIsLoggingIn] = useState(false);

  const navigate = useNavigate();

  const [showAlert, setShowAlert] = useState(false);

  async function login(dataToSend: LoginData) {
    try {
      const response = await fetch("/api/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(dataToSend),
      });

      const data = await response.json();
      return data;
    } catch (error) {
      console.log(error);
    } finally {
      setIsLoggingIn(false);
    }
  }

  async function handleLogin(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setIsLoggingIn(true);
    const data = {
      username: username,
      password: password,
    };

    try {
      console.log(data);
      console.log(conferenceId);
      const credentials: Credentials = await login(data);
      console.log("Credentials", credentials);

      localStorage.setItem("userEmail", username);
      localStorage.setItem("userRole", credentials.role);
      localStorage.setItem("userName", credentials.ime);
      localStorage.setItem("userSurname", credentials.prezime);
      localStorage.setItem("jwtToken", credentials.jwtToken);
      localStorage.setItem("voted", credentials.voted);
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
    } catch (error) {
      setShowAlert(true);
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
          <Button variant="primary" type="submit" disabled={isLoggingIn}>
            {isLoggingIn && (
              <Spinner
                as="span"
                animation="grow"
                size="sm"
                role="status"
                aria-hidden="true"
              />
            )}
            Prijava
          </Button>
        </Form>
        {showAlert && (
          <div className="alert alert-danger mt-3" role="alert">
            Pogrešni podaci za prijavu
          </div>
        )}
      </Modal.Body>
    </>
  );
}

export default LoginModal;
