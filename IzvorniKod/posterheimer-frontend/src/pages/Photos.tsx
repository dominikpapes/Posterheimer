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
import { Form, Modal } from "react-bootstrap";
import PleaseLogin from "../components/PleaseLogin";

const VISITOR = import.meta.env.VITE_VISITOR;
const REGISTERED = import.meta.env.VITE_REGISTERED;
const ADMIN = import.meta.env.VITE_ADMIN;
const SUPERUSER = import.meta.env.VITE_SUPERUSER;

interface NewPhoto {
  filepath: string;
}

const empty_photo: NewPhoto = {
  filepath: "",
};

const images = [
  {
    id: 1,
    img: img1,
  },
  {
    id: 2,
    img: img2,
  },
  {
    id: 3,
    img: img3,
  },
  {
    id: 4,
    img: img4,
  },
  {
    id: 5,
    img: img5,
  },
  {
    id: 6,
    img: img6,
  },
  { id: 7, img: img7 },
  { id: 8, img: img8 },
];

function Photos() {
  const [modal, setModal] = useState(false);
  const [showFormModal, setShowFormModal] = useState(false);
  const [tempImgSrc, setTempImgSrc] = useState("");
  const [tempIdx, setTempIdx] = useState(-1);
  const [newPhoto, setNewPhoto] = useState<NewPhoto>(empty_photo);

  const userRole = localStorage.getItem("userRole");
  const showEdits = userRole === ADMIN || userRole === SUPERUSER;
  const showLoginPrompt = userRole === VISITOR;

  function getImg(imgSrc: string, imgIdx: number) {
    console.log("pic clicked");
    setTempImgSrc(imgSrc);
    setTempIdx(imgIdx);
    setModal(true);
  }

  function nextImage() {
    const nextIndex = (tempIdx + 1) % images.length;
    setTempIdx(nextIndex);
    setTempImgSrc(images[nextIndex].img);
  }

  function previousImage() {
    let nextIndex = tempIdx - 1;
    if (nextIndex < 0) nextIndex = images.length - 1;
    setTempIdx(nextIndex);
    setTempImgSrc(images[nextIndex].img);
  }

  function postPhoto() {}
  function deletePhoto() {}

  function handleChange(e: any) {
    const { name, value } = e.target;
    setNewPhoto({
      ...newPhoto,
      [name]: value,
    });
  }

  function handleSubmit() {}

  useEffect(() => {
    // getPhotos()
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
            {images.map((item, index) => (
              <img
                key={item.id}
                src={item.img}
                onClick={() => getImg(item.img, index)}
              />
            ))}
          </div>
        </div>
      )}

      {/* Preview photo */}
      <div className={modal ? "model open" : "model"}>
        <img src={tempImgSrc} />
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
                name="filepath"
                value={newPhoto.filepath}
                onChange={handleChange}
              ></Form.Control>
            </Form.Group>
          </Form>
        </Modal.Body>
      </Modal>
    </>
  );
}

export default Photos;
