import React, { useEffect, useState } from "react";
import Titlebar from "../components/Titlebar";
import {
  Button,
  ButtonGroup,
  Card,
  CardTitle,
  Form,
  Toast,
} from "react-bootstrap";
import { useNavigate } from "react-router-dom";

interface Poster {
  title: string;
  author: string;
  file: Blob;
}

interface Conference {
  imeKonferencija: string;
  grad: string;
  adresa: string;
  zipCode: string;
  dateStart: string;
  dateEnd: string;
  videoUrl: string;
  genericUsername: string;
  genericPassword: string;
  adminUsername: string;
  adminPassword: string;
}

const emptyConference: Conference = {
  imeKonferencija: "",
  grad: "",
  adresa: "",
  zipCode: "",
  dateStart: "",
  dateEnd: "",
  videoUrl: "",
  genericUsername: "",
  genericPassword: "",
  adminUsername: "",
  adminPassword: "",
};

function PostConference(conference: Conference) {
  let id = 0;
  return id;
}

function NewConference() {
  const [newConference, setNewConference] =
    useState<Conference>(emptyConference);
  const [validationError, setValidationError] = useState("");
  const [visitorConfirmPassword, setVisitorConfirmPassword] = useState("");
  const [adminConfirmPassword, setAdminConfirmPassword] = useState("");

  const navigate = useNavigate();

  useEffect(() => {
    setNewConference(emptyConference);
  }, []);

  const handleChange = (e: any) => {
    const { name, value } = e.target;
    setNewConference({
      ...newConference,
      [name]: value,
    });
  };

  function handleSubmit(e: any) {
    e.preventDefault();

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!emailRegex.test(newConference.genericUsername)) {
      setValidationError("Pogrešan format adrese e-pošte posjetitelja");
      return;
    }

    if (!emailRegex.test(newConference.adminUsername)) {
      setValidationError("Pogrešan format adrese e-pošte administratora");
      return;
    }

    console.log(newConference);

    // PostConference(newConference);
  }

  return (
    <>
      <Titlebar />
      <div className="card p-4 my-3 app-content">
        <h1>Nova konferencija</h1>
        <Form onSubmit={handleSubmit}>
          <Card className="p-3 my-2">
            <Form.Group className="mb-3" controlId="formConferenceName">
              <Form.Label>Ime konferencije</Form.Label>
              <Form.Control
                type="text"
                name="imeKonferencija"
                placeholder="Unesite naziv konferencije"
                value={newConference?.imeKonferencija}
                onChange={handleChange}
                required
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formConferenceName">
              <Form.Label>Video prijenos konferencije</Form.Label>
              <Form.Control
                type="text"
                name="videoUrl"
                placeholder="Unesite URL videoprijenosa konferencije"
                value={newConference?.videoUrl}
                onChange={handleChange}
                required
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formConferenceCity">
              <Form.Label>Adresa</Form.Label>
              <Form.Control
                type="text"
                name="adresa"
                placeholder="Unesite adresu"
                value={newConference?.adresa}
                onChange={handleChange}
                required
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formConferenceLocation">
              <Form.Label>Grad</Form.Label>
              <Form.Control
                type="text"
                name="grad"
                placeholder="Unesite grad"
                value={newConference?.grad}
                onChange={handleChange}
                required
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formConferenceZipCode">
              <Form.Label>Poštanski broj</Form.Label>
              <Form.Control
                type="text"
                name="zipCode"
                placeholder="Unesite poštanski broj"
                value={newConference?.zipCode}
                onChange={handleChange}
                required
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formDateStart">
              <Form.Label>Vrijeme početka konferencije</Form.Label>
              <Form.Control
                type="datetime-local"
                name="dateStart"
                value={newConference?.dateStart.toString()}
                onChange={handleChange}
                required
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formDateEnd">
              <Form.Label>Vrijeme završetka konferencije</Form.Label>
              <Form.Control
                type="datetime-local"
                name="dateEnd"
                value={newConference?.dateEnd.toString()}
                onChange={handleChange}
                required
              />
            </Form.Group>
          </Card>

          <Card className="p-3">
            <CardTitle>Račun posjetitelja</CardTitle>
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Email adresa</Form.Label>
              <Form.Control
                type="email"
                name="genericUsername"
                placeholder="Unesite email posjetitelja"
                value={newConference.genericUsername}
                onChange={handleChange}
                required
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicPassword">
              <Form.Label>Lozinka</Form.Label>
              <Form.Control
                type="text"
                name="genericPassword"
                placeholder="Unesite lozinku posjetitelja"
                value={newConference.genericPassword}
                onChange={handleChange}
                required
              ></Form.Control>
            </Form.Group>
          </Card>

          <Card className="p-3 mt-2">
            <CardTitle>Račun administratora</CardTitle>
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Email adresa</Form.Label>
              <Form.Control
                type="email"
                name="adminUsername"
                placeholder="Unesite email administratora"
                value={newConference.adminUsername}
                onChange={handleChange}
                required
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicPassword">
              <Form.Label>Lozinka</Form.Label>
              <Form.Control
                type="text"
                name="adminPassword"
                placeholder="Unesite lozinku administratora"
                value={newConference.adminPassword}
                onChange={handleChange}
                required
              ></Form.Control>
            </Form.Group>
          </Card>

          <ButtonGroup className="mt-2">
            <Button variant="primary" type="submit" className="ml-2">
              U redu
            </Button>

            <Button variant="secondary" href="/">
              Odustani
            </Button>
          </ButtonGroup>
        </Form>
      </div>

      {validationError && (
        <div>
          <Toast
            onClose={() => setValidationError("")}
            show={validationError != ""}
            animation
            style={{
              position: "absolute",
              top: 20,
              right: 20,
            }}
          >
            <Toast.Header>
              <i className="fa-solid fa-triangle-exclamation"></i>
              <img
                src="holder.js/20x20?text=%20"
                className="rounded me-2"
                alt=""
              />
              <strong className="me-auto">Greška</strong>
            </Toast.Header>
            <Toast.Body>{validationError}</Toast.Body>
          </Toast>
        </div>
      )}
    </>
  );
}

export default NewConference;
