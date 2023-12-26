import { useEffect, useState } from "react";
import { Modal, Button, Card } from "react-bootstrap";
import "../styles.css";

import pdf_filepath1 from "../assets/posters/b5_Programsko_inzenjerstvo_i_informacijski_sustavi.pdf";
import pdf_filepath2 from "../assets/posters/b5_Racunalno_inzenjerstvo.pdf";
import ConferenceNavbar from "../components/ConferenceNavbar";

interface Poster {
  id: number;
  filepath: string;
  name: string;
  author: string;
}

const empty_poster: Poster = {
  id: 0,
  filepath: "",
  name: "",
  author: "",
};

const mock_posters: Poster[] = [
  {
    id: 1,
    filepath: pdf_filepath1,
    name: "pdf1",
    author: "user1",
  },
  {
    id: 2,
    filepath: pdf_filepath2,
    name: "pdf2",
    author: "user2",
  },
];
function Posters() {
  const [posters, setPosters] = useState<Poster[]>([]);
  const [chosenPoster, setChosenPoster] = useState(empty_poster);
  const [showPoster, setShowPoster] = useState(false);

  useEffect(() => {
    setPosters(mock_posters);
  }, []);

  return (
    <>
      <ConferenceNavbar />
      <div className="poster-grid app-content">
        {mock_posters.map((poster, index) => (
          <div
            key={poster.id}
            className="poster-container"
            onClick={() => {
              setChosenPoster(poster);
              console.log("Chosen poster: ", chosenPoster);
              setShowPoster(true);
            }}
          >
            <i className="fa-solid fa-file-pdf fa-5x"></i>
            <div className="h6 my-2">
              {poster.name} <br />
              {poster.author}
            </div>
          </div>
        ))}
      </div>

      {/* Poster view */}
      <Modal show={showPoster} onHide={() => setShowPoster(false)} size="xl">
        <Modal.Header closeButton>
          <Modal.Title>{chosenPoster.name}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <embed
            src={chosenPoster.filepath}
            title={chosenPoster.name}
            className="poster-embed"
          />
        </Modal.Body>
        <Modal.Footer>
          <Button variant="success" onClick={() => setShowPoster(false)}>
            Glasaj
          </Button>
          <Button
            variant="primary"
            href={chosenPoster.filepath}
            target="_blank"
            rel="norefferer"
          >
            Otvori
          </Button>
          <Button variant="secondary" onClick={() => setShowPoster(false)}>
            Zatvori
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default Posters;
