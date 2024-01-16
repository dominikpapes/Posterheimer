import { useEffect, useState } from "react";
import { Modal, Form, Button, Spinner, Alert } from "react-bootstrap";
import ConferencesList from "../components/ConferencesList";
import Titlebar from "../components/Titlebar";
import { useNavigate } from "react-router-dom";
import Loading from "../components/Loading";

const SUPERUSER = import.meta.env.VITE_SUPERUSER;

interface Conference {
  idKonferencija: number;
  imeKonferencija: string;
}

interface LoginData {
  username: string;
  password: string;
}

interface Credentials {
  jwtToken: string;
  role: string;
  ime: string;
  prezime: string;
}

function Home() {
  const [conferences, setConferences] = useState<Conference[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [isLoggingIn, setIsLoggingIn] = useState(false);
  const [hasError, setHasError] = useState(false);
  const [showEdits, setShowEdits] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  async function getConferences() {
    const response = await fetch("/api/konferencije");
    const data = await response.json();
    return data;
  }

  async function login(dataToSend: LoginData) {
    try {
      const response = await fetch("/api/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(dataToSend),
      });

      const data = await response.json();
      console.log(data);
      return data;
    } catch (error) {
      setHasError(true);
      console.log(error);
    } finally {
      setIsLoggingIn(false);
    }
  }

  function onSelectKonferencija() {}

  async function superuserLogin(e: any) {
    e.preventDefault();
    setIsLoggingIn(true);
    const data = {
      username: username,
      password: password,
    };
    const credentials: Credentials = await login(data);
    if (credentials?.role === SUPERUSER) {
      localStorage.setItem("jwtToken", credentials.jwtToken);
      localStorage.setItem("userRole", credentials.role);
      setShowEdits(true);
      setShowModal(false);
    }
  }

  useEffect(() => {
    setIsLoading(true);
    getConferences().then((data) => {
      setConferences(data);
      setIsLoading(false);
    });
    localStorage.clear();
  }, []);

  return (
    <>
      <Titlebar></Titlebar>

      {isLoading ? (
        <Loading />
      ) : (
        <div className="container text-center">
          <ConferencesList
            conferences={conferences}
            heading="Dostupne konferencije"
            onSelectItem={onSelectKonferencija}
            showDelete={showEdits}
          ></ConferencesList>
          {showEdits && (
            <i
              className="fa-solid fa-square-plus fa-4x"
              title="Nova konferencija"
              onClick={() => navigate("/newConference")}
            ></i>
          )}
        </div>
      )}
      <i
        className="fa-solid fa-gear superuser fa-2x"
        onClick={() => {
          setShowModal(true);
        }}
      ></i>

      {showEdits && (
        <i
          className="fa-solid fa-right-from-bracket fa-2x superuser-logout"
          onClick={() => {
            localStorage.clear();
            setUsername("");
            setPassword("");
            setShowEdits(false);
          }}
        ></i>
      )}

      <Modal
        show={showModal}
        onHide={() => {
          setShowModal(false);
          setHasError(false);
          setUsername("");
          setPassword("");
        }}
      >
        <Modal.Header closeButton>
          <Modal.Title>Prijava natkorisnika</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={superuserLogin}>
            <Form.Group className="mb-3" controlId="username">
              <Form.Label>Email:</Form.Label>
              <Form.Control
                type="text"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="password">
              <Form.Label>Lozinka:</Form.Label>
              <Form.Control
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </Form.Group>
            <Button variant="primary" type="submit" disabled={isLoggingIn}>
              {isLoggingIn && (
                <Spinner
                  as="span"
                  animation="grow"
                  size="sm"
                  role="status"
                  aria-hidden="true"
                />
              )}
              Prijava
            </Button>
          </Form>
          <Alert show={hasError} className="mt-2" variant="danger">
            Pogrešni podaci za prijavu
          </Alert>
        </Modal.Body>
      </Modal>
    </>
  );
}

export default Home;
