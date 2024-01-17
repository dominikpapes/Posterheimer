import React, { useEffect, useState } from "react";
import { Form, Modal, Button, Spinner, Alert } from "react-bootstrap";
import ConferenceNavbar from "../components/ConferenceNavbar";
import Loading from "../components/Loading";
import PleaseLogin from "../components/PleaseLogin";

interface GetSponsor {
  idPokrovitelj: string;
  imePokrovitelja: string;
  promotivniMaterijal: string;
  urlPromo: string;
}

interface PostSponsor {
  imePokrovitelja: string;
  promotivniMaterijal: string;
  urlPromo: string;
  idKonferencija: string;
}

const VISITOR = import.meta.env.VITE_VISITOR;
const REGISTERED = import.meta.env.VITE_REGISTERED;
const ADMIN = import.meta.env.VITE_ADMIN;
const SUPERUSER = import.meta.env.VITE_SUPERUSER;

const BASE64_JPG = "data:image/jpg;base64";

const userRole = localStorage.getItem("userRole");
const showEdits = userRole === ADMIN || userRole === SUPERUSER;

const empty_get_sponsor: GetSponsor = {
  imePokrovitelja: "",
  promotivniMaterijal: "",
  urlPromo: "",
  idPokrovitelj: "",
};

const empty_post_sponsor: PostSponsor = {
  imePokrovitelja: "",
  promotivniMaterijal: "",
  urlPromo: "",
  idKonferencija: "",
};

let fileToUpload: File;

export default function Sponsors() {
  const [isLoading, setIsLoading] = useState(true);
  const [isSending, setIsSending] = useState(false);
  const [isDeleting, setIsDeleting] = useState(false);
  const [showSponsorForm, setShowSponsorForm] = useState(false);
  const [newSponsor, setNewSponsor] = useState<PostSponsor>(empty_post_sponsor);
  const [sponsors, setSponsors] = useState<GetSponsor[]>([empty_get_sponsor]);
  const [hasError, setHasError] = useState(false);
  const [selectedIndex, setSelectedIndex] = useState(-1);

  let promoExistsError = false;

  const userRole = localStorage.getItem("userRole") || "";
  const showEdits = userRole === ADMIN || userRole === SUPERUSER;
  const showLoginPrompt = userRole === VISITOR;

  async function getSponsors() {
    try {
      setIsLoading(true);
      const conferenceId = localStorage.getItem("conferenceId");
      const token = localStorage.getItem("jwtToken");
      const response = await fetch(
        `/api/pokrovitelji/idKonferencija/${conferenceId}`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      const data = await response.json();
      return data;
    } catch (error) {
      console.log(error);
    } finally {
      setIsLoading(false);
    }
  }

  async function postSponsor(sponsor: PostSponsor) {
    try {
      setIsSending(true);
      const token = localStorage.getItem("jwtToken");
      const response = await fetch(`/api/pokrovitelji`, {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(sponsor),
      });
      const data = await response.json();
    } catch (error) {
      promoExistsError = true;
      setHasError(true);
      console.log(error);
    } finally {
      setIsSending(false);
      if (!promoExistsError) setShowSponsorForm(false);
    }
  }

  function convertBase64(file: any) {
    return new Promise((resolve, reject) => {
      const fileReader = new FileReader();
      fileReader.readAsDataURL(file);

      fileReader.onload = () => {
        resolve(fileReader.result);
      };

      fileReader.onerror = (error) => {
        reject(error);
      };
    });
  }

  function handleChange(e: any) {
    const { name, value } = e.target;
    if (e.target.files?.[0]) fileToUpload = e.target.files?.[0];
    setNewSponsor({
      ...newSponsor,
      [name]: value,
    });
  }

  async function handleSubmit(event: any) {
    event.preventDefault();
    promoExistsError = false;
    setHasError(false);
    console.log(fileToUpload);

    // Ensure a file is selected
    if (!fileToUpload) {
      alert("Niste izabrali datoteku!");
      return;
    }

    // Check if the file is an image (optional, but recommended)
    if (!fileToUpload.type.startsWith("image/")) {
      alert("Izabrana datoteka nije slika!");
      return;
    }

    // Convert the file to base64
    const base64 = await convertBase64(fileToUpload);
    if (typeof base64 === "string") {
      let sponsor: PostSponsor = {
        idKonferencija: localStorage.getItem("conferenceId") || "",
        promotivniMaterijal: base64.split(",")[1],
        imePokrovitelja: newSponsor.imePokrovitelja,
        urlPromo: newSponsor.urlPromo,
      };
      postSponsor(sponsor).then(() => {
        if (!promoExistsError)
          getSponsors().then((data) => {
            setSponsors(data);
          });
      });
    }
  }

  async function deleteSponsor(sponsorId: string) {
    try {
      setIsDeleting(true);
      const token = localStorage.getItem("jwtToken");
      const response = await fetch(`/api/pokrovitelji/id/${sponsorId}`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
    } catch (error) {
      console.log(error);
    } finally {
      setIsDeleting(false);
    }
    setSponsors((prev) => prev.filter((o) => o.idPokrovitelj !== sponsorId));
  }

  useEffect(() => {
    if (!showLoginPrompt)
      getSponsors().then((data) => {
        setSponsors(data);
      });
  }, []);

  return (
    <>
      <ConferenceNavbar />
      {showLoginPrompt ? (
        <PleaseLogin />
      ) : isLoading ? (
        <Loading />
      ) : (
        <div className="poster-grid mx-auto w-75">
          {showEdits && (
            <div
              className="poster-container"
              onClick={() => {
                setShowSponsorForm(true);
              }}
            >
              <i className="fa-solid fa-square-plus fa-4x"></i>
            </div>
          )}
          {sponsors.map((sponsor, index) => (
            <div
              className="poster-grid-element"
              key={index}
              onClick={() => setSelectedIndex(index)}
            >
              <div
                className="poster-container"
                onClick={() => {
                  window.open(sponsor.urlPromo, "_blank");
                }}
              >
                <i>
                  <img
                    src={`${BASE64_JPG},${sponsor.promotivniMaterijal}`}
                    alt={sponsor.imePokrovitelja}
                    style={{
                      width: "100%",
                      height: "100%",
                      objectFit: "cover",
                    }}
                  />
                </i>
              </div>
              {showEdits && (
                <Button
                  variant="danger"
                  className="delete-poster"
                  onClick={() => deleteSponsor(sponsor.idPokrovitelj)}
                  disabled={isDeleting}
                >
                  {isDeleting && selectedIndex == index && (
                    <Spinner
                      as="span"
                      animation="grow"
                      size="sm"
                      role="status"
                      aria-hidden="true"
                    />
                  )}
                  Obriši
                </Button>
              )}
            </div>
          ))}
        </div>
      )}

      {/* Sponsor form */}
      <Modal
        show={showSponsorForm}
        onHide={() => {
          setNewSponsor(empty_post_sponsor);
          setShowSponsorForm(false);
          setHasError(false);
        }}
      >
        <Modal.Header closeButton>
          <Modal.Title>Dodaj pokrovitelja</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleSubmit}>
            <Form.Group className="my-3">
              <Form.Label>Logotip</Form.Label>
              <Form.Control
                type="file"
                name="promotivniMaterijal"
                value={newSponsor.promotivniMaterijal}
                onChange={handleChange}
                accept="image/*"
              />
            </Form.Group>
            <Form.Group className="my-3">
              <Form.Label>Ime</Form.Label>
              <Form.Control
                type="text"
                name="imePokrovitelja"
                value={newSponsor.imePokrovitelja}
                onChange={handleChange}
              />
            </Form.Group>
            <Form.Group className="my-3">
              <Form.Label>Link</Form.Label>
              <Form.Control
                type="text"
                name="urlPromo"
                value={newSponsor.urlPromo}
                onChange={handleChange}
              />
            </Form.Group>
            <Button variant="primary" type="submit" disabled={isSending}>
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
          </Form>

          <Alert show={hasError} className="mt-2" variant="danger">
            Pokrovitelj s istim imenom već postoji
          </Alert>
        </Modal.Body>
      </Modal>
    </>
  );
}
