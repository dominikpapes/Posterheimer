import React, { useState, useContext, createContext } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import LoginModal from "./LoginModal";
import { SelectedConferenceContext } from "./ListGroup";
import AccessModal from "./AccessModal";
import RegisterModal from "./RegisterModal";

interface Props {
  showModal: boolean;
  handleClose: () => void;
}
function ConferenceAcessModal({ showModal, handleClose }: Props) {
  const [showAcess, setShowAccess] = useState(true);
  const [showLogin, setShowLogin] = useState(false);
  const [showRegister, setShowRegister] = useState(false);

  const conference = useContext(SelectedConferenceContext);
  const navigate = useNavigate();

  function handleAccess() {
    navigate("/conference", { state: conference });
  }

  function handleSwitchLogin() {
    setShowAccess(false);
    setShowRegister(false);
    setShowLogin(true);
  }

  function handleSwitchRegister() {
    setShowAccess(false);
    setShowRegister(true);
    setShowLogin(false);
  }

  return (
    <Modal show={showModal} onHide={handleClose}>
      {showAcess && <AccessModal></AccessModal>}
      {showLogin && <LoginModal></LoginModal>}
      {showRegister && <RegisterModal></RegisterModal>}
      <Modal.Footer>
        <Button variant="primary" onClick={handleSwitchLogin}>
          Prijavi se
        </Button>
        <Button variant="secondary" onClick={handleSwitchRegister}>
          Registriraj se
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default ConferenceAcessModal;
