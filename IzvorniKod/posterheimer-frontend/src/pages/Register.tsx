import React, { useState } from "react";
import { Container, Form, Button } from "react-bootstrap";
import { useLocation, useNavigate } from "react-router-dom";

const Register = () => {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
    confirmPassword: "",
  });

  const navigate = useNavigate();
  const location = useLocation();

  const conference = location.state;

  const handleChange = (e: any) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleCancel = (e: any) => {
    navigate("/");
  };

  const handleSubmit = (e: any) => {
    e.preventDefault();
    // Add your registration logic here
    const credentials = localStorage.getItem("credentials");
    const dataToSend = {
      email: formData.email,
      lozinka: formData.password,
      ime: "imeKorisnik",
      prezime: "prezimeKorisnik",
      admin: false,
      visitor: false,
      konferencija: conference,
    };

    console.log(dataToSend);

    fetch("/api/korisnici", {
      method: "POST", // or 'POST', 'PUT', etc.
      headers: {
        "Content-Type": "application/json", // Specify the content type if sending JSON data
        Authorization: `Basic ${credentials}`, // Add authorization token if required
        // Add other headers as needed
      },
      body: JSON.stringify(dataToSend),
      // Other options like body, mode, etc.
    })
      .then((data) => {
        console.log(data);
      })
      .catch((error) => {
        console.error("Problem with fetch:", error);
      });

    console.log("Form submitted:", formData);
    // You can perform registration logic, API calls, etc. here
  };

  return (
    <div className="card w-25 p-4 mt-5 mx-auto">
      <Form onSubmit={handleSubmit}>
        <div className="h2 p-2">Registracija: {conference.imeKonferencija}</div>
        <Form.Group controlId="formEmail">
          <Form.Label>Email address</Form.Label>
          <Form.Control
            type="email"
            placeholder="Unesite email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            required
          />
        </Form.Group>

        <Form.Group controlId="formPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Unesite lozinku"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
        </Form.Group>

        <Form.Group controlId="formConfirmPassword">
          <Form.Label>Confirm Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Potvrdite lozinku"
            name="confirmPassword"
            value={formData.confirmPassword}
            onChange={handleChange}
            required
          />
        </Form.Group>

        <Button variant="primary" type="submit" className="my-2">
          Registriraj se
        </Button>
        <Button variant="primary" className="my-2" onClick={handleCancel}>
          Odustani
        </Button>
      </Form>
    </div>
  );
};

export default Register;
