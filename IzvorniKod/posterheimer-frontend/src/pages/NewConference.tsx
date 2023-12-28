import React, { useState } from "react";
import Titlebar from "../components/Titlebar";
import { Button, ButtonGroup, Form } from "react-bootstrap";

interface Poster {
  title: string;
  author: string;
  file: Blob;
}

interface Conference {
  name: string;
  location: string;
  dateStart: string;
  dateEnd: string;
  posters: Poster[];
  photos: Blob[];
}

const emptyConference: Conference = {
  name: "",
  location: "",
  dateStart: "",
  dateEnd: "",
  posters: [],
  photos: [],
};

function NewConference() {
  const [newConference, setNewConference] =
    useState<Conference>(emptyConference);

  const handleChange = (e: any) => {
    const { name, value } = e.target;
    setNewConference({
      ...newConference,
      [name]: value,
    });
  };

  function handleSubmit() {}

  return (
    <>
      <Titlebar />
      <div className="card p-4 mt-5 app-content">
        <h1>Nova konferencija</h1>
        <Form onSubmit={handleSubmit}>
          <Form.Group className="mb-3" controlId="formConferenceName">
            <Form.Label>Ime konferencije</Form.Label>
            <Form.Control
              type="text"
              name="text"
              placeholder="Unesite naziv konferencije"
              value={newConference?.name}
              onChange={handleChange}
              required
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="formConferenceLocation">
            <Form.Label>Lokacija</Form.Label>
            <Form.Control
              type="location"
              name="text"
              placeholder="Unesite lokaciju"
              value={newConference?.location}
              onChange={handleChange}
              required
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="formBasicEmail">
            <Form.Label>Vrijeme početka konferencije</Form.Label>
            <Form.Control
              type="date"
              name="date"
              placeholder="Unesite ime"
              value={newConference?.dateStart}
              onChange={handleChange}
              required
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="formBasicEmail">
            <Form.Label>Vrijeme završetka konferencije</Form.Label>
            <Form.Control
              type="date"
              name="date"
              placeholder="Unesite ime"
              value={newConference?.dateEnd}
              onChange={handleChange}
              required
            />
          </Form.Group>

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
    </>
  );
}

export default NewConference;
