import { useState } from "react";
import { Button, Modal } from "react-bootstrap";
import LoginModal from "./LoginModal";

interface Conference {
  idKonferencija: number;
  imeKonferencija: string;
}

interface Props {
  conferences: Conference[];
  heading: string;
  onSelectItem: (item: {}) => void;
  showDelete: boolean;
}

function ConferencesList({
  conferences,
  heading,
  onSelectItem,
  showDelete,
}: Props) {
  const [selectedIndex, setSelectedIndex] = useState(-1);
  const [showModal, setShowModal] = useState(false);

  const selectedConference = conferences[selectedIndex];

  function handleClickAcess() {
    setShowModal(true);
  }

  function handleClickCloseModal() {
    setShowModal(false);
  }

  function handleDeleteConference() {}

  return (
    <>
      <Modal show={showModal} onHide={() => setShowModal(false)}>
        <LoginModal
          conferenceId={selectedConference?.idKonferencija}
          conferenceName={selectedConference?.imeKonferencija}
          show={showModal}
          handleClose={handleClickCloseModal}
        ></LoginModal>
      </Modal>

      <h1>{heading}</h1>
      <ul className="list-group">
        {conferences.map((item, index) => (
          <li
            className={
              selectedIndex === index
                ? "list-group-item light"
                : "list-group-item"
            }
            onClick={() => {
              setSelectedIndex(index);
            }}
            key={item.idKonferencija}
          >
            <h3 className="float-start">{item.imeKonferencija}</h3>
            <Button
              variant="success"
              className="float-end"
              onClick={handleClickAcess}
              title="Pristupi konferenciji"
            >
              Pristupi
            </Button>
            {showDelete && (
              <Button
                variant="danger"
                className="mx-2 float-end"
                onClick={handleDeleteConference}
                title="Obriši konferenciju"
              >
                Obriši
              </Button>
            )}
          </li>
        ))}
      </ul>
    </>
  );
}

export default ConferencesList;
