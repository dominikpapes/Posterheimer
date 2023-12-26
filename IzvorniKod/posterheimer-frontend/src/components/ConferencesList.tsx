import { useState, createContext } from "react";
import { Modal } from "react-bootstrap";
import LoginModal from "./LoginModal";

interface Conference {
  idKonferencija: number;
  imeKonferencija: string;
}

interface Props {
  conferences: Conference[];
  heading: string;
  onSelectItem: (item: {}) => void;
}

function ConferencesList({ conferences, heading, onSelectItem }: Props) {
  const [selectedIndex, setSelectedIndex] = useState(-1);
  const [showModal, setShowModal] = useState(false);

  const selectedConference = conferences[selectedIndex];

  const handleClickAcess = () => {
    setShowModal(true);
  };

  const handleClickCloseModal = () => {
    setShowModal(false);
  };

  return (
    <>
      <Modal show={showModal} onHide={handleClickCloseModal}>
        <LoginModal
          conferenceId={selectedConference?.idKonferencija}
          conferenceName={selectedConference?.imeKonferencija}
          showModal={showModal}
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
              onSelectItem(item);
            }}
            key={item.idKonferencija}
          >
            <h3 className="float-start">{item.imeKonferencija}</h3>
            <div
              className="btn btn-success float-end"
              onClick={handleClickAcess}
            >
              Pristupi
            </div>
          </li>
        ))}
      </ul>
    </>
  );
}

export default ConferencesList;
