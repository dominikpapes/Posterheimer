import React, { useState } from "react";
import { Button, Card, Modal } from "react-bootstrap";
import { Link } from "react-router-dom";
import LoginModal from "./LoginModal";

function PleaseLogin() {
  const [showModal, setShowModal] = useState(false);

  const conferenceId = Number(localStorage.getItem("conferenceId"));
  const conferenceName = localStorage.getItem("conferenceName") || "";
  return (
    <>
      <Card>
        <Card.Title>Registrirajte se</Card.Title>
        <Card.Text>
          Molimo Vas prijavite se vlastitim korisničkim računom ili napravite
          korisnički račun kako biste mogli pristupiti ovoj stranici
        </Card.Text>
        <Button variant="success" onClick={() => setShowModal(true)}>
          Prijava
        </Button>
        <Button>
          <Link to="/register" className="text-link">
            Registracija
          </Link>
        </Button>
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
