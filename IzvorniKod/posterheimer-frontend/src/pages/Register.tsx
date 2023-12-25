import React, { useState } from "react";
import {
  Container,
  Form,
  Button,
  ButtonGroup,
  Alert,
  Toast,
  ToastBody,
} from "react-bootstrap";
import { useLocation, useNavigate } from "react-router-dom";
import ReCAPTCHA from "react-google-recaptcha";
import Titlebar from "../components/Titlebar";
import ConferenceNavbar from "../components/ConferenceNavbar";
import "../styles.css";

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
  const [validationError, setValidationError] = useState("");
  const [showPassword, setShowPassword] = useState(false);

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

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(formData.email)) {
      setValidationError("Pogrešan format adrese e-pošte");
      return;
    }

    if (formData.password !== confirmPassword) {
      setValidationError("Unesene lozinku su različite");
      return;
    }

    // const credentials = localStorage.getItem("credentials");

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
      <div className="card p-4 mt-5 app-content">
        <h1>Registracija</h1>
        <Form onSubmit={handleSubmit}>
          <Form.Group className="mb-3" controlId="formBasicEmail">
            <Form.Label>Email adresa</Form.Label>
            <Form.Control
              type="email"
              name="email"
              placeholder="Unesite email"
              value={formData.email}
              onChange={handleChange}
              required
            />
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Lozinka</Form.Label>
            <Form.Control
              type={showPassword ? "text" : "password"}
              name="password"
              placeholder="Unesite lozinku"
              value={formData.password}
              onChange={handleChange}
              required
            ></Form.Control>
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Potvrdite lozinku</Form.Label>
            <Form.Control
              type={showPassword ? "text" : "password"}
              name="password"
              placeholder="Ponovite lozinku"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              required
            ></Form.Control>
          </Form.Group>

          <Form.Group controlId="formShowPassword">
            <Form.Check
              type="checkbox"
              label="Prikaži lozinku"
              checked={showPassword}
              onChange={() => setShowPassword(!showPassword)}
            />
          </Form.Group>

          <ReCAPTCHA sitekey="6LesATwpAAAAAKiOaz64lxp0XYd8a4KcOmcF1loc" />

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
};

export default Register;
