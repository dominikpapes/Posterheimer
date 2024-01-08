import { useEffect, useState } from "react";
import { Navbar, Container, Modal, Form, Button } from "react-bootstrap";
import ConferencesList from "../components/ConferencesList";
import Titlebar from "../components/Titlebar";
import { useNavigate } from "react-router-dom";

const SUPERUSER_ID = import.meta.env.VITE_SUPERUSER_ID;
const SUPERUSER_IME = import.meta.env.VITE_SUPERUSER_IME;
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

const mock_conference = [
  {
    idKonferencija: 1,
    imeKonferencija: "Mock Conference 1",
  },
  {
    idKonferencija: 2,
    imeKonferencija: "Mock Conference 2",
  },
];

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
    console.log(error);
  }
}

function Home() {
  const [conferences, setConferences] = useState<Conference[]>([]);
  const [showEdits, setShowEdits] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  let userRole;
  const navigate = useNavigate();

  useEffect(() => {
    getConferences().then((data) => {
      console.log(data);
      setConferences(data);
    });
    localStorage.clear();
    // setConferences(mock_conference);
  }, []);

  function onSelectKonferencija() {}
  async function superuserLogin(e: any) {
    e.preventDefault();
    const data = {
      username: username,
      password: password,
    };
    const credentials: Credentials = await login(data);
    if (credentials.role === SUPERUSER) {
      localStorage.setItem("jwtToken", credentials.jwtToken);
      localStorage.setItem("userRole", credentials.role);
      setShowEdits(true);
      setShowModal(false);
    }
  }

  return (
    <>
      <Titlebar></Titlebar>
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
            setShowEdits(false);
          }}
        ></i>
      )}

      <Modal show={showModal} onHide={() => setShowModal(false)}>
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
            <Button variant="primary" type="submit">
              Login
            </Button>
          </Form>
        </Modal.Body>
      </Modal>
    </>
  );
}

export default Home;
