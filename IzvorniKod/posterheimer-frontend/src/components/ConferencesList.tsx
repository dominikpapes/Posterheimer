import { useState } from "react";
import { Button, Modal, Spinner } from "react-bootstrap";
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
  const [isSending, setIsSending] = useState(false);
  const [confs, setConfs] = useState(conferences);

  const selectedConference = conferences[selectedIndex];

  function handleClickAcess() {
    setShowModal(true);
  }

  function handleClickCloseModal() {
    setShowModal(false);
  }

  async function deleteConference(id: number) {
    try {
      setIsSending(true);
      let conf = { idKonferencija: id };
      const token = localStorage.getItem("jwtToken");
      const response = await fetch(`/api/konferencije`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(conf),
      });
    } catch (error) {
      console.log(error);
    } finally {
      setIsSending(false);
      window.location.reload();
    }
  }

  return (
    <>
      <Modal show={showModal} onHide={() => setShowModal(false)}>
        <LoginModal
          conferenceId={selectedConference?.idKonferencija}
          conferenceName={selectedConference?.imeKonferencija}
        ></LoginModal>
      </Modal>

      <h1>{heading}</h1>
      <ul className="list-group">
        {confs.map((item, index) => (
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
            {!showDelete && (
              <Button
                variant="success"
                className="float-end"
                onClick={handleClickAcess}
                title="Pristupi konferenciji"
              >
                Pristupi
              </Button>
            )}
            {showDelete && (
              <Button
                onClick={() => deleteConference(item.idKonferencija)}
                disabled={isSending}
                variant="danger"
                className="mx-2 float-end"
              >
                {isSending && (
                  <Spinner
                    as="span"
                    animation="grow"
                    size="sm"
                    role="status"
                    aria-hidden="true"
                  />
                )}
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
