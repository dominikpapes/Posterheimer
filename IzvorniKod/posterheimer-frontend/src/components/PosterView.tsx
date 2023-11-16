import { Modal, Button } from "react-bootstrap";
import { useState } from "react";

interface Props {
  posterUri: string;
}

function PosterView({ posterUri }: Props) {
  const [showModal, setShowModal] = useState(false);

  const handleShowPosterView = () => {
    setShowModal(true);
  };

  const handleClosePosterView = () => {
    setShowModal(false);
  };

  return (
    <>
      <p onClick={handleShowPosterView}>{posterUri}</p>
      <Modal show={showModal} onHide={handleClosePosterView} size="xl">
        <Modal.Header closeButton>
          <Modal.Title>{posterUri}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <div className="file-view">
            <embed
              title="Poster"
              src={posterUri}
              width="100%"
              height="500px"
            ></embed>
          </div>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClosePosterView}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default PosterView;
