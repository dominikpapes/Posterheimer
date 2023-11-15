import { useState, createContext } from "react";
import { Link, useNavigate } from "react-router-dom";
import LoginModal from "./LoginModal";
import ConferenceAcessModal from "./ConferenceAcessModal";

interface Props {
  items: string[];
  heading: string;
  onSelectItem: (item: string) => void;
}

function ListGroup({ items, heading, onSelectItem }: Props) {
  const [selectedIndex, setSelectedIndex] = useState(-1);
  const [showModal, setShowModal] = useState(false);
  const [showAcess, setShowAccess] = useState(false);
  const [showLogin, setShowLogin] = useState(false);
  const [showRegister, setShowRegister] = useState(false);

  const selectedConference = items[selectedIndex];

  const handleClickAcess = () => {
    setShowModal(true);
  };

  const handleClickCloseModal = () => {
    setShowModal(false);
  };

  return (
    <>
      <SelectedConferenceContext.Provider value={selectedConference}>
        {showModal && (
          <ConferenceAcessModal
            showModal={showModal}
            handleClose={handleClickCloseModal}
          />
        )}
      </SelectedConferenceContext.Provider>
      <h1>{heading}</h1>
      <ul className="list-group">
        {items.map((item, index) => (
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
            key={item}
          >
            <div className="float-start">{item}</div>
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

export default ListGroup;

export const SelectedConferenceContext = createContext<string>("");
