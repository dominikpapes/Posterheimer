import React, { useState, useContext } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const VISITOR = import.meta.env.VITE_VISITOR;
const REGISTERED = import.meta.env.VITE_REGISTERED;
const ADMIN = import.meta.env.VITE_ADMIN;
const SUPERUSER = import.meta.env.VITE_SUPERUSER;

interface Props {
  conferenceId: number;
  conferenceName: string;
  showModal: boolean;
  handleClose: () => void;
}

function LoginModal({
  conferenceId,
  conferenceName,
  showModal,
  handleClose,
}: Props) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const handleLogin = async (event: React.FormEvent<HTMLFormElement>) => {
    // Perform login logic
    event.preventDefault();
    localStorage.setItem("conferenceId", conferenceId.toString());
    localStorage.setItem("userRole", VISITOR);

    // const credentials = btoa(`${username}:${password}`);
    // console.log(credentials);
    // let key = "credentials";

    // localStorage.setItem(key, credentials);

    // const response = await fetch(
    //   `/api/konferencije/${conference?.idKonferencija}`,
    //   {
    //     headers: {
    //       Authorization: `Basic ${credentials}`,
    //     },
    //   }
    // );

    // if (response.ok) {
    //   const data = await response.json();
    //   navigate("/conference", { state: data.idKonferencija });
    // } else {
    //   console.error("Authentication failed");
    // }
    // Close the modal after logging in

    navigate("/conference");
  };

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
