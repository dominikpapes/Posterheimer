import React, { createRef, useRef, useState } from "react";
import {
  Container,
  Form,
  Button,
  ButtonGroup,
  Alert,
  Toast,
  ToastBody,
  FloatingLabel,
} from "react-bootstrap";
import { Link, useLocation, useNavigate } from "react-router-dom";
import ReCAPTCHA from "react-google-recaptcha";
import Titlebar from "../components/Titlebar";
import ConferenceNavbar from "../components/ConferenceNavbar";
import "../styles.css";

const SITE_KEY = "6LesATwpAAAAAKiOaz64lxp0XYd8a4KcOmcF1loc";

interface RegistrationData {
  email: string;
  lozinka: string;
  ime: string;
  prezime: string;
  idKonferencije: number;
  admin: boolean;
  visitor: boolean;
}

const emptyRegistrationData: RegistrationData = {
  email: "",
  lozinka: "",
  ime: "",
  prezime: "",
  idKonferencije: 0,
  admin: false,
  visitor: false,
};

function Register() {
  const [formData, setFormData] = useState<RegistrationData>(
    emptyRegistrationData
  );
  const [confirmPassword, setConfirmPassword] = useState("");
  const [validationError, setValidationError] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  const recaptcha = useRef<ReCAPTCHA>();

  const navigate = useNavigate();

  function registerNewUser(dataToSend: RegistrationData) {
    dataToSend.idKonferencije = Number(localStorage.getItem("conferenceId"));
    const jwtToken = localStorage.getItem("jwtToken");
    console.log(dataToSend);
    fetch("/api/korisnici", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${jwtToken}`,
      },
      body: JSON.stringify(dataToSend),
    })
      .then((data) => {
        console.log(data);
      })
      .catch((error) => {
        console.error("Problem with fetch:", error);
      });
  }

  async function verifyCaptcha(captchaValue: string) {
    let jwtToken = localStorage.getItem("jwtToken");
    let dataToSend = { captchaValue: captchaValue };
    const response = await fetch("/api/verify", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${jwtToken}`,
      },
      body: JSON.stringify(dataToSend),
    });
    const data = await response.json();
    console.log(data);
    return data;
  }

  const handleChange = (e: any) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  async function handleSubmit(e: any) {
    e.preventDefault();

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const onlyLettersRegex = /^[A-Za-zšđćčŠĐĆČ]+$/;
    if (!emailRegex.test(formData.email)) {
      setValidationError("Pogrešan format adrese e-pošte");
      return;
    }

    if (formData.lozinka !== confirmPassword) {
      setValidationError("Unesene lozinke su različite");
      return;
    }

    if (
      !onlyLettersRegex.test(formData.ime) ||
      !onlyLettersRegex.test(formData.prezime)
    ) {
      setValidationError("Ime i prezime smije sadržavati samo slova");
      return;
    }
    const captchaValue = recaptcha.current?.getValue();

    if (!captchaValue) {
      alert("Molim Vas potvrdite reCAPTCHA!");
    } else {
      // make form submission
      const captchaVerification = await verifyCaptcha(captchaValue);
      if (captchaVerification.success) {
        registerNewUser(formData);
        alert("Form submission successful!");
      } else alert("Uh Oh");
    }
    navigate("/");
  }

  return (
    <>
      <ConferenceNavbar />
      <div className="card p-4 mt-5 mx-auto app-content">
        <h1>Registracija</h1>
        <Form onSubmit={handleSubmit}>
          <Form.Group className="mb-3" controlId="formIme">
            <FloatingLabel controlId="formIme" label="Ime">
              <Form.Control
                type="text"
                name="ime"
                placeholder="Unesite ime"
                value={formData.ime}
                onChange={handleChange}
                required
              />
            </FloatingLabel>
          </Form.Group>
          <Form.Group className="mb-3" controlId="formPrezime">
            <FloatingLabel controlId="formPrezime" label="Prezime">
              <Form.Control
                type="text"
                name="prezime"
                placeholder="Unesite prezime"
                value={formData.prezime}
                onChange={handleChange}
                required
              />
            </FloatingLabel>
          </Form.Group>
          <Form.Group className="mb-3" controlId="formBasicEmail">
            <FloatingLabel controlId="formBasicEmail" label="Email">
              <Form.Control
                type="email"
                name="email"
                placeholder="Unesite email"
                value={formData.email}
                onChange={handleChange}
                required
              />
            </FloatingLabel>
          </Form.Group>

          <div className="mx-2">
            <Form.Group className="mb-3" controlId="formBasicPassword">
              <Form.Label>Lozinka</Form.Label>
              <Form.Control
                type={showPassword ? "text" : "password"}
                name="lozinka"
                placeholder="Unesite lozinku"
                value={formData.lozinka}
                onChange={handleChange}
                required
              ></Form.Control>
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicPassword">
              <Form.Label>Potvrdite lozinku</Form.Label>
              <Form.Control
                type={showPassword ? "text" : "password"}
                name="lozinka"
                placeholder="Ponovite lozinku"
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
                required
              ></Form.Control>
            </Form.Group>
          </div>

          <Form.Group controlId="formShowPassword">
            <Form.Check
              type="checkbox"
              label="Prikaži lozinku"
              checked={showPassword}
              onChange={() => setShowPassword(!showPassword)}
            />
          </Form.Group>

          <ReCAPTCHA
            ref={recaptcha as React.RefObject<ReCAPTCHA>}
            sitekey={SITE_KEY}
          />

          <ButtonGroup className="mt-2">
            <Button variant="primary" type="submit" className="ml-2">
              U redu
            </Button>

            <Button variant="secondary" href="/conference">
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

export default Register;
