import { useContext, useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { SelectedConferenceContext } from "./ListGroup";
import { LoginScreenFunctionsContext } from "./ConferenceAcessModal";

interface Props {
  handleLogin: () => void;
  handleRegister: () => void;
  handleAccess: () => void;
}

function AccessModal() {
  const conference = useContext(SelectedConferenceContext);
  const functions = useContext(LoginScreenFunctionsContext);
  const [pin, setPin] = useState("");
  const navigate = useNavigate();

  function handleLogin() {
    // Perform login logic
    navigate("/conference", { state: conference });
    // Close the modal after logging in
  }

  return (
    <>
      <Modal.Header closeButton>
        <Modal.Title>Pristupi - {conference}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group className="mb-3" controlId="pin">
            <Form.Label>PIN:</Form.Label>
            <Form.Control
              type="password"
              value={pin}
              onChange={(e) => setPin(e.target.value)}
            />
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="primary" onClick={handleLogin}>
          Pristupi
        </Button>
      </Modal.Footer>
    </>
  );
}

export default AccessModal;
