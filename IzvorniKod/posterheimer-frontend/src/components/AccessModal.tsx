import { useContext, useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { SelectedConferenceContext } from "./ListGroup";

interface Props {
  handleLogin: () => void;
  handleRegister: () => void;
  handleAccess: () => void;
}

function AccessModal() {
  const conference = useContext(SelectedConferenceContext);
  const [pin, setPin] = useState("");
  const navigate = useNavigate();

  const handleLogin = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    fetch(`api/konferencije/${conference?.idKonferencija}`)
      .then((res) => res.json())
      .then((access) => {
        if (access) {
          navigate("/conference", { state: conference?.idKonferencija });
        } else {
          console.log("access denied");
        }
      })
      .catch((exc) => console.log(exc));
  };
  // Close the modal after logging in

  return (
    <>
      <Modal.Header closeButton>
        <Modal.Title>Pristupi - {conference?.imeKonferencija}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form onSubmit={handleLogin}>
          <Form.Group className="mb-3" controlId="pin">
            <Form.Label>PIN:</Form.Label>
            <Form.Control
              type="password"
              value={pin}
              onChange={(e) => setPin(e.target.value)}
            />
          </Form.Group>
          <Button variant="primary" type="submit">
            Pristupi
          </Button>
        </Form>
      </Modal.Body>
    </>
  );
}
export default AccessModal;
