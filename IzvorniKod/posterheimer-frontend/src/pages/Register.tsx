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
  Modal,
  Spinner,
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
  const [isSending, setIsSending] = useState(false);
  const [confirmPassword, setConfirmPassword] = useState("");
  const [validationError, setValidationError] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  const [passwordError, setPasswordError] = useState("");
  const [emailError, setEmailError] = useState("");
  const [nameError, setNameError] = useState("");
  const [surnameError, setSurnameError] = useState("");
  const [captchaError, setCaptchaError] = useState("");
  const [showAlert, setShowAlert] = useState(false);

  const [registrationError, setRegistrationError] = useState("");

  const recaptcha = useRef<ReCAPTCHA>();
  let hasErrors = false;

  const navigate = useNavigate();

  async function registerNewUser(dataToSend: RegistrationData) {
    try {
      setIsSending(true);
      dataToSend.idKonferencije = Number(localStorage.getItem("conferenceId"));
      const jwtToken = localStorage.getItem("jwtToken");
      console.log(dataToSend);
      const response = await fetch("/api/korisnici", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${jwtToken}`,
        },
        body: JSON.stringify(dataToSend),
      });
      if (!response.ok) {
        throw new Error();
      }
    } catch (error) {
      console.log("Problem ", error);
      setShowAlert(true);
    } finally {
      setIsSending(false);
    }
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
    try {
      e.preventDefault();
      setEmailError("");
      setNameError("");
      setSurnameError("");
      setPasswordError("");
      setCaptchaError("");
      setShowAlert(false);
      hasErrors = false;
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      const onlyLettersRegex = /^[A-Za-zšđćčŠĐĆČ]+$/;
      if (!emailRegex.test(formData.email)) {
        setEmailError("Pogrešan format adrese e-pošte");
        hasErrors = true;
      }

      const passwordRegex = /^(?=.*[A-Z])(?=.*\d).{8,}$/;

      if (!passwordRegex.test(formData.lozinka)) {
        setPasswordError(
          "Lozinka mora biti dugačka barem 8 znakova, sadržavati barem jedno veliko slovo i barem jedan broj"
        );
        hasErrors = true;
      }

      if (formData.lozinka !== confirmPassword) {
        setPasswordError("Unesene lozinke su različite");
        hasErrors = true;
      }

      if (!onlyLettersRegex.test(formData.ime)) {
        setNameError("Ime smije sadržavati samo slova");
        hasErrors = true;
      }

      if (!onlyLettersRegex.test(formData.prezime)) {
        setSurnameError("Prezime smije sadržavati samo slova");
        hasErrors = true;
      }
      const captchaValue = recaptcha.current?.getValue();

      if (!captchaValue) {
        setCaptchaError("Molim Vas potvrdite reCAPTCHA!");
        hasErrors = true;
      } else {
        // make form submission
        const captchaVerification = await verifyCaptcha(captchaValue);
        if (!captchaVerification.success) {
          setCaptchaError(
            "ReCAPTCHA nije uspjela, molimo Vas pokušajte ponovno."
          );
          hasErrors = true;
        }
      }
      if (!hasErrors) {
        await registerNewUser(formData);
      }
    } catch (error) {
      setShowAlert(true);
    }
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
                disabled={isSending}
                required
              />
            </FloatingLabel>
            <Alert show={nameError != ""} variant="danger" className="mt-2">
              {nameError}
            </Alert>
          </Form.Group>
          <Form.Group className="mb-3" controlId="formPrezime">
            <FloatingLabel controlId="formPrezime" label="Prezime">
              <Form.Control
                type="text"
                name="prezime"
                placeholder="Unesite prezime"
                value={formData.prezime}
                onChange={handleChange}
                disabled={isSending}
                required
              />
            </FloatingLabel>
            <Alert show={surnameError != ""} variant="danger" className="mt-2">
              {surnameError}
            </Alert>
          </Form.Group>
          <Form.Group className="mb-3" controlId="formBasicEmail">
            <FloatingLabel controlId="formBasicEmail" label="Email">
              <Form.Control
                type="email"
                name="email"
                placeholder="Unesite email"
                value={formData.email}
                onChange={handleChange}
                disabled={isSending}
                required
              />
            </FloatingLabel>
            <Alert show={emailError != ""} variant="danger" className="mt-2">
              {emailError}
            </Alert>
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
                disabled={isSending}
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
                disabled={isSending}
                required
              ></Form.Control>
              <Alert
                show={passwordError != ""}
                variant="danger"
                className="mt-2"
              >
                {passwordError}
              </Alert>
            </Form.Group>
          </div>

          <Form.Group controlId="formShowPassword">
            <Form.Check
              type="checkbox"
              label="Prikaži lozinku"
              checked={showPassword}
              onChange={() => setShowPassword(!showPassword)}
              disabled={isSending}
            />
          </Form.Group>

          <ReCAPTCHA
            ref={recaptcha as React.RefObject<ReCAPTCHA>}
            sitekey={SITE_KEY}
          />

          <Alert show={captchaError != ""} variant="danger" className="mt-2">
            {captchaError}
          </Alert>

          <ButtonGroup className="mt-2">
            <Button
              variant="primary"
              type="submit"
              className="ml-2"
              disabled={isSending}
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
              U redu
            </Button>

            <Button variant="secondary" href="/conference" disabled={isSending}>
              Odustani
            </Button>
          </ButtonGroup>
        </Form>
        <Alert
          show={showAlert}
          className="mt-2"
          variant="danger"
          dismissible
          onClose={() => setShowAlert(false)}
        >
          Greška prilikom registracije.
        </Alert>
      </div>
    </>
  );
}

export default Register;
