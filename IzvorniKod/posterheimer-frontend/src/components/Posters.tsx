import { useState } from "react";
import pdf1 from "../assets/posters/PROGI_2023Posterheimer_rev1(1).pdf";
import pdf2 from "../assets/posters/PROGI_2023Posterheimer_rev1(2).pdf";
import PosterView from "./PosterView";

function Posters() {
  const posterUris = [pdf1, pdf2];

  return (
    <>
      <div>Posteri</div>
      <ul className="list-group">
        {posterUris.map((posterUri, index) => (
          <li key={index}>
            <PosterView posterUri={posterUri} />
          </li>
        ))}
      </ul>
    </>
  );
}

export default Posters;
