import React from "react";
import { Spinner } from "react-bootstrap";

function Loading() {
  return (
    <>
      <div className="text-center">
        <Spinner className="my-5"></Spinner>
      </div>
    </>
  );
}

export default Loading;
