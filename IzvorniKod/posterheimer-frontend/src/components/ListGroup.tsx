import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import LoginModal from "./LoginModal";

interface Props {
  items: string[];
  heading: string;
  onSelectItem: (item: string) => void;
}

function ListGroup({ items, heading, onSelectItem }: Props) {
  const [selectedIndex, setSelectedIndex] = useState(-1);
  const [showLogin, setShowLogin] = useState(false);

  const propToSend = { conference: items[selectedIndex] };

  const handleClickLogin = () => {
    setShowLogin(true);
  };

  const handleClickCloseModal = () => {
    setShowLogin(false);
  };

  return (
    <>
      {showLogin && (
        <LoginModal
          conference={items[selectedIndex]}
          showModal={showLogin}
          handleClose={handleClickCloseModal}
        />
      )}
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
              onClick={handleClickLogin}
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
