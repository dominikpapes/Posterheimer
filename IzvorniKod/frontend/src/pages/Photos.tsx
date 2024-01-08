import ConferenceNavbar from "../components/ConferenceNavbar";
import "../styles.css";
import addImage from "../../public/add-image.png";

import img1 from "../assets/photos/img1.jpg";
import img2 from "../assets/photos/img2.jpg";
import img3 from "../assets/photos/img3.jpg";
import img4 from "../assets/photos/img4.jpg";
import img5 from "../assets/photos/img5.jpg";
import img6 from "../assets/photos/img6.jpg";
import img7 from "../assets/photos/img7.jpg";
import img8 from "../assets/photos/img8.jpg";
import { useEffect, useState } from "react";
import { Button, Form, Modal } from "react-bootstrap";
import PleaseLogin from "../components/PleaseLogin";

const VISITOR = import.meta.env.VITE_VISITOR;
const REGISTERED = import.meta.env.VITE_REGISTERED;
const ADMIN = import.meta.env.VITE_ADMIN;
const SUPERUSER = import.meta.env.VITE_SUPERUSER;

const BASE_64_JPG = "data:image/jpg;base64";

interface NewPhoto {
  idKonferencija: number;
  filePath: string;
}

interface Photo {
  idFotografija: number;
  filePath: string;
}

const empty_photo: NewPhoto = {
  filePath: "",
  idKonferencija: 0,
};

async function getPhotos() {
  const conferenceId = localStorage.getItem("conferenceId");
  const token = localStorage.getItem("jwtToken");
  const response = await fetch(
    `/api/fotografije/idKonferencija/${conferenceId}`,
    {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    }
  );
  const data = await response.json();
  console.log(data);
  return data;
}

async function postPhoto(photo: NewPhoto) {
  console.log("Photo to send", photo);
  const token = localStorage.getItem("jwtToken");
  const response = await fetch(`/api/fotografije`, {
    method: "POST",
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": "application/json",
    },
    body: JSON.stringify(photo),
  });
  const data = await response.json();
  console.log(data);
}

let fileToUpload: File;

function Photos() {
  const [modal, setModal] = useState(false);
  const [showFormModal, setShowFormModal] = useState(false);
  const [tempImgSrc, setTempImgSrc] = useState("");
  const [tempIdx, setTempIdx] = useState(-1);
  const [newPhoto, setNewPhoto] = useState<NewPhoto>(empty_photo);

  const [photos, setPhotos] = useState<Photo[]>([]);

  const userRole = localStorage.getItem("userRole") || "";
  const conferenceId = localStorage.getItem("conferenceId") || "";
  const jwtToken = localStorage.getItem("jwtToken") || "";

  const showEdits = userRole === ADMIN || userRole === SUPERUSER;
  const showLoginPrompt = userRole === VISITOR;

  function getImg(imgSrc: string, imgIdx: number) {
    setTempImgSrc(imgSrc);
    setTempIdx(imgIdx);
    setModal(true);
  }

  function nextImage() {
    const nextIndex = (tempIdx + 1) % photos.length;
    setTempIdx(nextIndex);
    setTempImgSrc(photos[nextIndex].filePath);
  }

  function previousImage() {
    let nextIndex = tempIdx - 1;
    if (nextIndex < 0) nextIndex = photos.length - 1;
    setTempIdx(nextIndex);
    setTempImgSrc(photos[nextIndex].filePath);
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

  async function handleSubmit(event: any) {
    event.preventDefault();

    console.log(fileToUpload);

    // Ensure a file is selected
    if (!fileToUpload) {
      alert("Niste izabrali datoteku!");
      return;
    }

    // Check if the file is an image (optional, but recommended)
    if (!fileToUpload.type.startsWith("image/")) {
      alert("Izabrana datoteka nije fotografija!");
      return;
    }

    // Convert the file to base64
    const base64 = await convertBase64(fileToUpload);
    if (typeof base64 === "string") {
      console.log(base64);
      setNewPhoto({ idKonferencija: Number(conferenceId), filePath: base64 });
      postPhoto(newPhoto);
    }
  }

  function deletePhoto() {}

  function handleChange(e: any) {
    const { name, value } = e.target;
    fileToUpload = e.target.files[0];
    console.log(fileToUpload);
    setNewPhoto({
      ...newPhoto,
      [name]: value,
    });
  }

  useEffect(() => {
    getPhotos().then((data) => setPhotos(data));
  }, []);

  return (
    <>
      <ConferenceNavbar />
      {showLoginPrompt ? (
        <PleaseLogin />
      ) : (
        <div className="gallery">
          <div className="image-container">
            {showEdits && (
              <>
                <img src={addImage} onClick={() => setShowFormModal(true)} />
              </>
            )}
            {photos?.map((item, index) => (
              <img
                key={item.idFotografija}
                src={`${BASE_64_JPG},${item.filePath}`}
                onClick={() => getImg(item.filePath, index)}
              />
            ))}
          </div>
        </div>
      )}

      {/* Preview photo */}
      <div className={modal ? "model open" : "model"}>
        <img src={`${BASE_64_JPG},${tempImgSrc}`} />
        <div className="photo-control">
          <a
            href={tempImgSrc}
            download="Slika"
            target="_blank"
            rel="noreferrer"
          >
            <i className="fa-solid fa-arrow-down fa-3x"></i>
          </a>
          <i
            className="fa-solid fa-xmark fa-3x"
            onClick={() => setModal(false)}
          ></i>
        </div>
        <i
          className="fa-solid fa-chevron-left fa-3x left"
          onClick={() => previousImage()}
        ></i>
        <i
          className="fa-solid fa-chevron-right fa-3x right"
          onClick={() => nextImage()}
        ></i>
        <i
          className="fa-solid fa-trash-can fa-3x delete-photo"
          onClick={() => deletePhoto()}
        ></i>
      </div>

      {/* <a
        href="https://www.flaticon.com/free-icons/add-image"
        title="add image icons"
      >
        Add image icons created by nawicon - Flaticon
      </a> */}

      {/* Add new photo modal */}
      <Modal
        show={showFormModal}
        onHide={() => {
          setShowFormModal(false);
          setNewPhoto(empty_photo);
        }}
      >
        <Modal.Header closeButton>
          <Modal.Title>Dodaj fotografiju</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleSubmit}>
            <Form.Group className="my-3">
              <Form.Label>Fotografija</Form.Label>
              <Form.Control
                type="file"
                name="filePath"
                value={newPhoto.filePath}
                onChange={handleChange}
              ></Form.Control>
            </Form.Group>
            <Button type="submit">U redu</Button>
          </Form>
        </Modal.Body>
      </Modal>
    </>
  );
}

export default Photos;
