import { useEffect, useState } from "react";
import {
  Modal,
  Button,
  Card,
  Form,
  Spinner,
  OverlayTrigger,
  Popover,
  Overlay,
  Toast,
} from "react-bootstrap";
import "../styles.css";

import pdf_file1 from "../assets/posters/b5_Programsko_inzenjerstvo_i_informacijski_sustavi.pdf";
import pdf_file2 from "../assets/posters/b5_Racunalno_inzenjerstvo.pdf";
import ConferenceNavbar from "../components/ConferenceNavbar";
import PleaseLogin from "../components/PleaseLogin";
import Loading from "../components/Loading";
import { useNavigate } from "react-router-dom";
import ConferenceNotYetStarted from "../components/ConferenceNotYetStarted";

const VISITOR = import.meta.env.VITE_VISITOR;
const REGISTERED = import.meta.env.VITE_REGISTERED;
const ADMIN = import.meta.env.VITE_ADMIN;
const SUPERUSER = import.meta.env.VITE_SUPERUSER;

const BASE64_PDF = "data:application/pdf;base64";

interface PostPoster {
  idKonferencija: number;
  filePath: string;
  imePoster: string;
  imeAutor: string;
  prezimeAutor: string;
  posterEmail: string;
}

interface GetPoster {
  idPoster: number;
  imePoster: string;
  imeAutor: string;
  prezimeAutor: string;
  filePath: string;
}

const empty_post_poster: PostPoster = {
  idKonferencija: 0,
  filePath: "",
  imePoster: "",
  imeAutor: "",
  prezimeAutor: "",
  posterEmail: "",
};

const empty_get_poster: GetPoster = {
  idPoster: 0,
  imePoster: "",
  imeAutor: "",
  prezimeAutor: "",
  filePath: "",
};

let fileToUpload: File;

function Posters() {
  const [isLoading, setIsLoading] = useState(true);
  const [isSending, setIsSending] = useState(false);
  const [isDeleting, setIsDeleting] = useState(false);
  const [selectedIndex, setSelectedIndex] = useState(-1);
  const [chosenPoster, setChosenPoster] = useState(empty_get_poster);
  const [showPoster, setShowPoster] = useState(false);
  const [showPosterForm, setShowPosterForm] = useState(false);
  const [showVotingModal, setShowVotingModal] = useState(false);
  const [showToast, setShowToast] = useState(true);
  const [target, setTarget] = useState(null);

  const [newPoster, setNewPoster] = useState<PostPoster>(empty_post_poster);
  const [posters, setPosters] = useState<GetPoster[]>([]);
  const [timeLeft, setTimeLeft] = useState("");

  const [votingOver, setVotingOver] = useState(
    localStorage.getItem("votingOver")
  );
  const confStarted = localStorage.getItem("confStarted");
  const dateEnd = localStorage.getItem("dateEnd") || "";
  const userRole = localStorage.getItem("userRole");
  const showEdits = userRole === ADMIN || userRole === SUPERUSER;
  const showVoting = userRole === REGISTERED;
  const enableVoting = localStorage.getItem("voted") == "false";
  const showLoginPrompt = userRole === VISITOR;

  const navigate = useNavigate();

  function convertBase64(file: any) {
    return new Promise((resolve, reject) => {
      const fileReader = new FileReader();
      fileReader.readAsDataURL(file);

      fileReader.onload = () => {
        resolve(fileReader.result);
      };

      fileReader.onerror = (error) => {
        reject(error);
      };
    });
  }

  async function postPoster(poster: PostPoster) {
    try {
      const token = localStorage.getItem("jwtToken");
      await fetch(`/api/posteri`, {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(poster),
      });
    } catch (error) {
      console.log(error);
    } finally {
      setIsSending(false);
      setShowPosterForm(false);
    }
  }

  async function getPosters() {
    try {
      setIsLoading(true);
      const conferenceId = localStorage.getItem("conferenceId");
      const token = localStorage.getItem("jwtToken");
      const response = await fetch(
        `/api/posteri/idKonferencija/${conferenceId}`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      const data = await response.json();
      data.forEach(
        (element: GetPoster) =>
          (element.imePoster = decodeURI(element.imePoster))
      );
      return data;
    } catch (error) {
      console.log(error);
    } finally {
      setIsLoading(false);
    }
  }

  async function deletePoster(posterId: Number) {
    try {
      setIsDeleting(true);
      const token = localStorage.getItem("jwtToken");
      const response = await fetch(`/api/posteri/id/${posterId}`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
    } catch (error) {
      console.log(error);
    } finally {
      setIsDeleting(false);
      getPosters().then((data) => setPosters(data));
    }
  }

  // todo
  async function sendVote(poster: GetPoster) {
    try {
      setIsSending(true);
      let data = {
        imePoster: poster.imePoster,
        idKonferencija: localStorage.getItem("conferenceId"),
        email: localStorage.getItem("userEmail"),
      };
      const token = localStorage.getItem("jwtToken");
      await fetch(`/api/posteri`, {
        method: "PUT",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      });
      localStorage.setItem("voted", "true");
    } catch (error) {
      console.log(error);
    } finally {
      setIsSending(false);
    }
  }

  function handleChange(e: any) {
    const { name, value } = e.target;
    if (e.target.files?.[0]) fileToUpload = e.target.files?.[0];
    setNewPoster({
      ...newPoster,
      [name]: value,
    });
  }

  async function handleSubmit(event: any) {
    event.preventDefault();
    setIsSending(true);
    // Ensure a file is selected
    if (!fileToUpload) {
      alert("Niste izabrali datoteku!");
      return;
    }

    // Check if the file is an image (optional, but recommended)
    if (!fileToUpload.type.startsWith("application/pdf")) {
      alert("Izabrana datoteka nije PDF!");
      return;
    }

    // Convert the file to base64
    const base64 = await convertBase64(fileToUpload);
    if (typeof base64 === "string") {
      let poster: PostPoster = {
        idKonferencija: Number(localStorage.getItem("conferenceId")),
        filePath: base64.split(",")[1],
        imePoster: encodeURI(newPoster.imePoster),
        imeAutor: newPoster.imeAutor,
        prezimeAutor: newPoster.prezimeAutor,
        posterEmail: newPoster?.posterEmail,
      };
      postPoster(poster).then(() => {
        getPosters().then((data) => {
          setPosters(data);
        });
      });
    }
  }

  function countdown() {
    var countDownDate = new Date(dateEnd).getTime();
    // Update the count down every 1 second

    // Get today's date and time
    var now = new Date().getTime();

    // Find the distance between now and the count down date
    var distance = countDownDate - now;
    // Time calculations for days, hours, minutes and seconds
    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
    var hours = Math.floor(
      (distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)
    );
    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((distance % (1000 * 60)) / 1000);

    // Output the result in an element with id="demo"
    setTimeLeft(days + "d " + hours + "h " + minutes + "m " + seconds + "s ");

    // If the count down is over, write some text
    if (distance < 0) {
      setVotingOver("true");
    }
  }

  useEffect(() => {
    getPosters().then((data) => {
      setPosters(data);
    });
    const interval = setInterval((): any => {
      if (votingOver === "false") {
        countdown();
      } else {
        clearInterval(interval);
      }
    }, 1000);
  }, []);

  return (
    <>
      <ConferenceNavbar />
      {confStarted == "false" &&
      (userRole != ADMIN || userRole != SUPERUSER) ? (
        <ConferenceNotYetStarted />
      ) : isLoading ? (
        <Loading />
      ) : (
        <div className="poster-grid mx-auto w-75">
          {showEdits && (
            <div
              className="poster-container"
              onClick={() => {
                setShowPosterForm(true);
              }}
            >
              <i className="fa-solid fa-square-plus fa-4x"></i>
            </div>
          )}
          {posters.map((poster, index) => (
            <div
              className="poster-grid-element"
              key={poster.imePoster}
              onClick={() => setSelectedIndex(index)}
            >
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
                  {poster.imeAutor} {poster.prezimeAutor}
                </div>
              </div>
              {showEdits && (
                <Button
                  variant="danger"
                  className="delete-poster"
                  onClick={() => {
                    deletePoster(poster.idPoster);
                  }}
                  disabled={isDeleting}
                >
                  {isDeleting && selectedIndex == index && (
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
            </div>
          ))}
        </div>
      )}

      {/* Poster view */}
      <Modal show={showPoster} onHide={() => setShowPoster(false)} size="xl">
        <Modal.Header closeButton>
          <Modal.Title>{chosenPoster.imePoster}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <embed
            src={`${BASE64_PDF},${chosenPoster.filePath}`}
            title={chosenPoster.imePoster}
            className="poster-embed"
          />
        </Modal.Body>
        <Modal.Footer>
          {showVoting && (
            <Overlay
              rootClose
              show={showVotingModal}
              placement="top"
              target={target}
            >
              <Popover id="popover-basic">
                <Popover.Header as="h3">Jeste li sigurni</Popover.Header>
                <Popover.Body>
                  <Button
                    className="mx-2"
                    variant="success"
                    onClick={() => {
                      sendVote(chosenPoster);
                      setShowVotingModal(false);
                    }}
                  >
                    Da
                  </Button>
                  <Button
                    variant="danger"
                    onClick={() => setShowVotingModal(false)}
                  >
                    Ne
                  </Button>
                </Popover.Body>
              </Popover>
            </Overlay>
          )}
          {showVoting && (
            <Button
              variant="success"
              onClick={(event: any) => {
                setShowVotingModal(true);
                setTarget(event.target);
              }}
              disabled={isSending || !enableVoting}
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
              {enableVoting ? "Glasaj" : "Glas zabilježen"}
            </Button>
          )}
          <Button
            variant="primary"
            href={`data:application/pdf;base64,${chosenPoster.filePath}`}
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
          setNewPoster(empty_post_poster);
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
                name="filePath"
                value={newPoster?.filePath}
                onChange={handleChange}
                required
              ></Form.Control>
            </Form.Group>
            <Form.Group className="my-3">
              <Form.Label>Ime postera</Form.Label>
              <Form.Control
                type="text"
                name="imePoster"
                value={newPoster?.imePoster}
                onChange={handleChange}
                required
              ></Form.Control>
            </Form.Group>
            <Form.Group className="my-3">
              <Form.Label>Ime autora</Form.Label>
              <Form.Control
                type="text"
                name="imeAutor"
                value={newPoster?.imeAutor}
                onChange={handleChange}
                required
              ></Form.Control>
            </Form.Group>
            <Form.Group className="my-3">
              <Form.Label>Prezime autora</Form.Label>
              <Form.Control
                type="text"
                name="prezimeAutor"
                value={newPoster?.prezimeAutor}
                onChange={handleChange}
                required
              ></Form.Control>
            </Form.Group>
            <Form.Group className="my-3">
              <Form.Label>Email autora</Form.Label>
              <Form.Control
                type="email"
                name="posterEmail"
                value={newPoster?.posterEmail}
                onChange={handleChange}
                required
              ></Form.Control>
            </Form.Group>
            <Button type="submit" disabled={isSending}>
              {isSending && (
                <Spinner
                  as="span"
                  animation="grow"
                  size="sm"
                  role="status"
                  aria-hidden="true"
                />
              )}
              U redu
            </Button>
          </Form>
        </Modal.Body>
      </Modal>

      <Toast
        show={showToast && !isLoading && confStarted == "true"}
        onClose={() => setShowToast(false)}
        animation
        style={{
          position: "absolute",
          top: 80,
          right: 20,
          width: "10rem",
        }}
      >
        <Toast.Header>
          <strong className="me-auto">
            {votingOver === "true" ? "Glasanje završeno" : "Glasanje u tijeku"}
          </strong>
        </Toast.Header>
        {votingOver === "false" && <Toast.Body>{timeLeft}</Toast.Body>}
      </Toast>
    </>
  );
}

export default Posters;
