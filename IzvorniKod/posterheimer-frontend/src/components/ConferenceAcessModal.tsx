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
  const [showLogin, setShowLogin] = useState(true);
  const [showRegister, setShowRegister] = useState(false);

  const conference = useContext(SelectedConferenceContext);
  const navigate = useNavigate();

  function handleAccess() {
    navigate("/conference", { state: conference });
  }

  return (
    <Modal show={showModal} onHide={handleClose}>
      {showLogin && <LoginModal></LoginModal>}
    </Modal>
  );
}

export default ConferenceAcessModal;
