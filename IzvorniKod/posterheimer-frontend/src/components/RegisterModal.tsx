import { useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";

interface Props {
  show: boolean;
  handleClose: () => void;
}

function RegisterModal() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [pin, setPin] = useState("");

  function handleRegister() {
    console.log("registering");
  }

  return (
    <>
      <Modal.Header closeButton>
        <Modal.Title>Registracija</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group controlId="formEmail">
            <Form.Label>Email adresa</Form.Label>
            <Form.Control
              type="email"
              placeholder="Upišite email adresu"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </Form.Group>
          <Form.Group controlId="formPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="Enter your password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </Form.Group>
          <Form.Group controlId="formPin">
            <Form.Label>PIN konferencije</Form.Label>
            <Form.Control
              type="password"
              placeholder="Upišite PIN konferencije"
              value={pin}
              onChange={(e) => setPin(e.target.value)}
              required
            />
          </Form.Group>
          <Button variant="primary" onClick={handleRegister}>
            Register
          </Button>
        </Form>
      </Modal.Body>
    </>
  );
}

export default RegisterModal;
