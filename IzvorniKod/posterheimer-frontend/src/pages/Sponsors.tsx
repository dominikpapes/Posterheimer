import React, { useState } from "react";
import { Form, Modal, Button } from "react-bootstrap";
import ConferenceNavbar from "../components/ConferenceNavbar";
import harvard from "../assets/sponsors/harvard.png";
import mit from "../assets/sponsors/mit.png";
import fer from "../assets/sponsors/fer.png";

const VISITOR = import.meta.env.VITE_VISITOR;
const REGISTERED = import.meta.env.VITE_REGISTERED;
const ADMIN = import.meta.env.VITE_ADMIN;
const SUPERUSER = import.meta.env.VITE_SUPERUSER;

const userRole = localStorage.getItem("userRole");
const showEdits = userRole === ADMIN || userRole === SUPERUSER;

interface Sponsor {
  name: string;
  logo: string;
  link: string;
  idKonferencija: number;
}

const initialMockSponsors: Sponsor[] = [
  {
    name: "fer",
    logo: fer,
    link: "https://www.fer.unizg.hr/",
    idKonferencija: 1,
  },
  {
    name: "harvard",
    logo: harvard,
    link: "https://www.harvard.edu/",
    idKonferencija: 1,
  },
  {
    name: "mit",
    logo: mit,
    link: "https://www.mit.edu/",
    idKonferencija: 1,
  },
];

export default function Sponsors() {
  const [showPosterForm, setShowPosterForm] = useState(false);
  const [newSponsor, setNewSponsor] = useState({
    name: "",
    logo: "",
    link: "",
    idKonferencija: 1,
  });
  const [mockSponsors, setMockSponsors] = useState(initialMockSponsors);

  function handleChange(e: React.ChangeEvent<HTMLInputElement>) {
    const { name, value } = e.target;
    setNewSponsor((prevSponsor) => ({
      ...prevSponsor,
      [name]: value,
    }));
  }

  function handleFileChange(e: React.ChangeEvent<HTMLInputElement>) {
    const file = e.target.files?.[0];
    if (file) {
      setNewSponsor((prevSponsor) => ({
        ...prevSponsor,
        logo: URL.createObjectURL(file),
      }));
    }
  }

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    setMockSponsors([...mockSponsors, { ...newSponsor }]);
    setNewSponsor({
      name: "",
      logo: "",
      link: "",
      idKonferencija: 1,
    });
    setShowPosterForm(false);
  }

  function handleDelete(name: string) {
    const updatedSponsors = mockSponsors.filter(
      (sponsor) => sponsor.name !== name
    );
    setMockSponsors(updatedSponsors);
  }

  return (
    <>
      <ConferenceNavbar />
      <div className="poster-grid app-content">
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
        {mockSponsors.map((sponsor) => (
          <div className="poster-grid-element" key={sponsor.name}>
            <div
              className="poster-container"
              onClick={() => {
                window.open(sponsor.link, "_blank");
              }}
            >
              <i>
                <img
                  src={sponsor.logo}
                  alt={sponsor.name}
                  style={{ width: "100%", height: "100%", objectFit: "cover" }}
                />
              </i>
            </div>
            {showEdits && (
              <Button
                variant="danger"
                className="delete-poster"
                onClick={() => handleDelete(sponsor.name)}
              >
                Obriši
              </Button>
            )}
          </div>
        ))}
      </div>
      {/* Poster form */}
      <Modal
        show={showPosterForm}
        onHide={() => {
          setNewSponsor({
            name: "",
            logo: "",
            link: "",
            idKonferencija: 1,
          });
          setShowPosterForm(false);
        }}
      >
        <Modal.Header closeButton>
          <Modal.Title>Dodaj pokrovitelja</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleSubmit}>
            <Form.Group className="my-3">
              <Form.Label>Ime</Form.Label>
              <Form.Control
                type="text"
                name="name"
                value={newSponsor.name}
                onChange={handleChange}
              />
            </Form.Group>
            <Form.Group className="my-3">
              <Form.Label>Slika (URL or File)</Form.Label>
              <Form.Control
                type="text"
                name="logo"
                value={newSponsor.logo}
                onChange={handleChange}
              />
              <Form.Control
                type="file"
                accept="image/*"
                onChange={handleFileChange}
              />
            </Form.Group>
            <Form.Group className="my-3">
              <Form.Label>Link</Form.Label>
              <Form.Control
                type="text"
                name="link"
                value={newSponsor.link}
                onChange={handleChange}
              />
            </Form.Group>
            <Button variant="primary" type="submit">
              Spremi
            </Button>
          </Form>
        </Modal.Body>
      </Modal>
    </>
  );
}
