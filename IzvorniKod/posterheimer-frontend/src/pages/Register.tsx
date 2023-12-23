import React, { useState } from "react";
import { Container, Form, Button, ButtonGroup } from "react-bootstrap";
import { useLocation, useNavigate } from "react-router-dom";
import Titlebar from "../components/Titlebar";

interface RegistrationData {
  email: string;
  password: string;
  konferencijaId: number;
}

const emptyRegistrationData: RegistrationData = {
  email: "",
  password: "",
  konferencijaId: 0,
};

const Register = () => {
  const [formData, setFormData] = useState<RegistrationData>(
    emptyRegistrationData
  );
  const [confirmPassword, setConfirmPassword] = useState("");

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
    const credentials = localStorage.getItem("credentials");

    // console.log(dataToSend);

    // fetch("/api/korisnici", {
    //   method: "POST",
    //   headers: {
    //     "Content-Type": "application/json",
    //     Authorization: `Basic ${credentials}`,

    //   },
    //   body: JSON.stringify(dataToSend),
    // })
    //   .then((data) => {
    //     console.log(data);
    //   })
    //   .catch((error) => {
    //     console.error("Problem with fetch:", error);
    //   });

    // console.log("Form submitted:", formData);
    navigate("/");
  };

  return (
    <>
      <Titlebar></Titlebar>
      <div className="card w-25 p-4 mt-5 mx-auto">
        <h1>Registracija</h1>
        <Form>
          <Form.Group className="mb-3" controlId="formBasicEmail">
            <Form.Label>Email adresa</Form.Label>
            <Form.Control type="email" placeholder="Unesite email" />
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Lozinka</Form.Label>
            <Form.Control
              type="password"
              placeholder="Unesite lozinku"
            ></Form.Control>
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Potvrdite lozinku</Form.Label>
            <Form.Control
              type="password"
              placeholder="Ponovite lozinku"
            ></Form.Control>
          </Form.Group>

          <ButtonGroup>
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
};

export default Register;
