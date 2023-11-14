import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

interface Props {
  items: string[];
  heading: string;
  onSelectItem: (item: string) => void;
}

function ListGroup({ items, heading, onSelectItem }: Props) {
  const [selectedIndex, setSelectedIndex] = useState(-1);
  const navigate = useNavigate();

  const propToSend = { conference: items[selectedIndex] };

  const handleClickLogin = () => {
    navigate("/login");
  };

  return (
    <>
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
