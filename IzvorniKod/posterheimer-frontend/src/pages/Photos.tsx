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

const VISITOR = import.meta.env.VITE_VISITOR;
const REGISTERED = import.meta.env.VITE_REGISTERED;
const ADMIN = import.meta.env.VITE_ADMIN;
const SUPERUSER = import.meta.env.VITE_SUPERUSER;

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
  const [tempImgSrc, setTempImgSrc] = useState("");
  const [tempIdx, setTempIdx] = useState(-1);

  const userRole = localStorage.getItem("userRole");
  const showEdits = userRole === ADMIN || userRole === SUPERUSER;

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

  useEffect(() => {
    // getPhotos()
  }, []);

  return (
    <>
      <ConferenceNavbar />
      <div className="gallery">
        <div className="image-container">
          {images.map((item, index) => (
            <img src={item.img} onClick={() => getImg(item.img, index)} />
          ))}
        </div>
        <div className="add-photo">
          <img src={addImage} />
        </div>
      </div>

      {/* Modal */}
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
      </div>

      <a
        href="https://www.flaticon.com/free-icons/add-image"
        title="add image icons"
      >
        Add image icons created by nawicon - Flaticon
      </a>
    </>
  );
}

export default Photos;
