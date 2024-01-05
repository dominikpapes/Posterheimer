import { useEffect, useState } from "react";
import { Modal, Button, Card, Form } from "react-bootstrap";
import "../styles.css";

import pdf_file1 from "../assets/posters/b5_Programsko_inzenjerstvo_i_informacijski_sustavi.pdf";
import pdf_file2 from "../assets/posters/b5_Racunalno_inzenjerstvo.pdf";
import ConferenceNavbar from "../components/ConferenceNavbar";

const VISITOR = import.meta.env.VITE_VISITOR;
const REGISTERED = import.meta.env.VITE_REGISTERED;
const ADMIN = import.meta.env.VITE_ADMIN;
const SUPERUSER = import.meta.env.VITE_SUPERUSER;

interface Poster {
  idKonferencija: number;
  filePath: any;
  imePoster: string;
  imeAutor: string;
  prezimeAutor: string;
  posterEmail: string;
}

const empty_poster: Poster = {
  idKonferencija: 0,
  filePath: undefined,
  imePoster: "",
  imeAutor: "",
  prezimeAutor: "",
  posterEmail: "",
};

const mock_posters: Poster[] = [
  {
    idKonferencija: 1,
    filePath: pdf_file1,
    imePoster: "pdf1",
    imeAutor: "user1",
    prezimeAutor: "",
    posterEmail: "",
  },
  {
    idKonferencija: 2,
    filePath: pdf_file2,
    imePoster: "pdf2",
    imeAutor: "user2",
    prezimeAutor: "",
    posterEmail: "",
  },
];
function Posters() {
  const [posters, setPosters] = useState<Poster[]>([]);
  const [chosenPoster, setChosenPoster] = useState(empty_poster);
  const [showPoster, setShowPoster] = useState(false);
  const [showPosterForm, setShowPosterForm] = useState(false);
  const [newPoster, setNewPoster] = useState(empty_poster);

  const userRole = localStorage.getItem("userRole");
  const showEdits = userRole === ADMIN || userRole === SUPERUSER;

  function postPoster() {}
  function deletePoster() {}

  function handleSubmit() {}

  function handleChange(e: any) {
    const { name, value } = e.target;
    setNewPoster({
      ...newPoster,
      [name]: value,
    });
  }

  function handleFileChange(e: React.ChangeEvent<HTMLInputElement>) {
    const file = e.target.files?.[0];
    if (file) {
      setNewPoster({
        ...newPoster,
        filePath: URL.createObjectURL(file),
      });
    }
  }

  useEffect(() => {
    setPosters(mock_posters);
  }, []);

  return (
    <>
      <ConferenceNavbar />
      <div className="poster-grid app-content">
        {mock_posters.map((poster, index) => (
          <div className="poster-grid-element" key={poster.imePoster}>
            <div
              className="poster-container"
              onClick={() => {
                setChosenPoster(poster);
                console.log("Chosen poster: ", chosenPoster);
                setShowPoster(true);
              }}
            >
              <i className="fa-solid fa-file-pdf fa-5x"></i>
              <div className="h6 my-2">
                {poster.imePoster} <br />
                {poster.imeAutor}
              </div>
            </div>
            {showEdits && (
              <Button variant="danger" className="delete-poster">
                Obriši
              </Button>
            )}
          </div>
        ))}
        {showEdits && (
          <div
            className="poster-container"
            onClick={() => {
              setShowPosterForm(true);
            }}
          >
            <i className="fa-solid fa-plus fa-5x"></i>
          </div>
        )}
      </div>

      {/* Poster view */}
      <Modal show={showPoster} onHide={() => setShowPoster(false)} size="xl">
        <Modal.Header closeButton>
          <Modal.Title>{chosenPoster.imePoster}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <embed
            src={chosenPoster.filePath}
            title={chosenPoster.imePoster}
            className="poster-embed"
          />
        </Modal.Body>
        <Modal.Footer>
          <Button variant="success" onClick={() => setShowPoster(false)}>
            Glasaj
          </Button>
          <Button
            variant="primary"
            href={chosenPoster.filePath}
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

      {/* Poster form */}
      <Modal
        show={showPosterForm}
        onHide={() => {
          setNewPoster(empty_poster);
          setShowPosterForm(false);
        }}
      >
        <Modal.Header closeButton>
          <Modal.Title>Dodaj poster</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleSubmit}>
            <Form.Group className="my-3">
              <Form.Label>Datoteka</Form.Label>
              <Form.Control
                type="file"
                name="file"
                value={newPoster.filePath}
                onChange={handleChange}
              ></Form.Control>
            </Form.Group>
            <Form.Group className="my-3">
              <Form.Label>Ime datoteke</Form.Label>
              <Form.Control
                type="text"
                name="imePoster"
                value={newPoster.imePoster}
                onChange={handleChange}
              ></Form.Control>
            </Form.Group>
            <Form.Group className="my-3">
              <Form.Label>Ime autora</Form.Label>
              <Form.Control
                type="text"
                name="imeAutor"
                value={newPoster.imeAutor}
                onChange={handleChange}
              ></Form.Control>
            </Form.Group>
            <Form.Group className="my-3">
              <Form.Label>Prezime autora</Form.Label>
              <Form.Control
                type="text"
                name="prezimeAutor"
                value={newPoster.prezimeAutor}
                onChange={handleChange}
              ></Form.Control>
            </Form.Group>
            <Form.Group className="my-3">
              <Form.Label>Email autora</Form.Label>
              <Form.Control
                type="email"
                name="posterEmail"
                value={newPoster.posterEmail}
                onChange={handleChange}
              ></Form.Control>
            </Form.Group>
          </Form>
        </Modal.Body>
      </Modal>
    </>
  );
}

export default Posters;
