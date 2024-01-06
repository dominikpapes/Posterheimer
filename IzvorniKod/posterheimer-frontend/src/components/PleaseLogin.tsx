import React, { useState } from "react";
import { Button, ButtonGroup, Card, Modal } from "react-bootstrap";
import { Link } from "react-router-dom";
import LoginModal from "./LoginModal";

function PleaseLogin() {
  const [showModal, setShowModal] = useState(false);

  const conferenceId = Number(localStorage.getItem("conferenceId"));
  const conferenceName = localStorage.getItem("conferenceName") || "";
  return (
    <>
      <Card className="login-prompt" data-bs-theme="light">
        <Card.Title className="mx-auto">Registrirajte se</Card.Title>
        <Card.Body>
          <Card.Text>
            Molimo Vas prijavite se vlastitim korisničkim računom ili napravite
            korisnički račun kako biste mogli pristupiti ovom sadržaju
          </Card.Text>
          <ButtonGroup className="mx-auto">
            <Button variant="success" onClick={() => setShowModal(true)}>
              Prijava
            </Button>
            <Button>
              <Link to="/register" className="text-link">
                Registracija
              </Link>
            </Button>
          </ButtonGroup>
        </Card.Body>
      </Card>

      <Modal show={showModal} onHide={() => setShowModal(false)}>
        <LoginModal
          conferenceId={conferenceId}
          conferenceName={conferenceName}
        ></LoginModal>
      </Modal>
    </>
  );
}

export default PleaseLogin;
