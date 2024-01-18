import React from "react";
import { Card } from "react-bootstrap";

function ConferenceNotYetStarted() {
  const dateStartStr = localStorage.getItem("dateStart") || "";
  const dateStart = new Date(dateStartStr);
  const dateOptions: Intl.DateTimeFormatOptions = {
    weekday: "short", // 'short' or 'long' for short or full weekday names
    year: "numeric",
    month: "short",
    day: "numeric",
    hour: "numeric",
    minute: "numeric",
    second: "numeric",
  };
  return (
    <Card
      data-bs-theme="light"
      className="mx-auto my-5 text-center not-started"
    >
      <Card.Title className="mx-auto">
        Konferencija još nije započela
      </Card.Title>
      <Card.Body>
        <Card.Text>
          Konferencija započinje:{" "}
          {dateStart.toLocaleString("hr-HR", dateOptions)}
        </Card.Text>
      </Card.Body>
    </Card>
  );
}

export default ConferenceNotYetStarted;
