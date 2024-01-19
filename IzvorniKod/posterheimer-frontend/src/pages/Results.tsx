import { useEffect, useState } from "react";
import ConferenceNavbar from "../components/ConferenceNavbar";
import Loading from "../components/Loading";

interface PosterGetWithVotes {
  imePoster: string;
  imeAutor: string;
  prezimeAutor: string;
  brGlasova: number;
}

const disableHorizontalScrollBar = {
  overflowX: "hidden",
} as React.CSSProperties;

export default function Results() {
  const [results, setResults] = useState<PosterGetWithVotes[]>([]);
  const [isLoading, setIsLoading] = useState(false);

  async function getResults() {
    try {
      const conferenceId = localStorage.getItem("conferenceId");
      const token = localStorage.getItem("jwtToken");
      const response = await fetch(
        `/api/posteri/idKonferencija/${conferenceId}/rezultati`,
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
      console.error(error);
    }
  }

  useEffect(() => {
    setIsLoading(true);
    getResults().then((data) => {
      setResults(data);
      setIsLoading(false);
    });
  }, []);

  const placeNames = ["1. Mjesto", "2. Mjesto", "3. Mjesto"];
  const styles = ["gold", "silver", "#cd7f32"];
  return (
    <>
      {console.log(results)}
      <ConferenceNavbar />
      {isLoading ? (
        <Loading />
      ) : (
        <div
          style={{ marginTop: "50px", marginBottom: "50px" }}
          className="text-center"
        >
          <h2 style={{ marginBottom: "20px" }}>Rezultati natjecanja</h2>

          <div className="row w-75 mx-auto" style={disableHorizontalScrollBar}>
            {results.slice(0, 3).map((result, index) => (
              <div
                key={index}
                className="col-md-4"
                style={{ marginBottom: "20px" }}
              >
                <div className="card text-center m-3">
                  <div className="card-body">
                    <h3 style={{ color: styles[index] }}>
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="16"
                        height="16"
                        fill="currentColor"
                        className="bi bi-award"
                        viewBox="0 0 16 16"
                      >
                        <path d="M9.669.864 8 0 6.331.864l-1.858.282-.842 1.68-1.337 1.32L2.6 6l-.306 1.854 1.337 1.32.842 1.68 1.858.282L8 12l1.669-.864 1.858-.282.842-1.68 1.337-1.32L13.4 6l.306-1.854-1.337-1.32-.842-1.68zm1.196 1.193.684 1.365 1.086 1.072L12.387 6l.248 1.506-1.086 1.072-.684 1.365-1.51.229L8 10.874l-1.355-.702-1.51-.229-.684-1.365-1.086-1.072L3.614 6l-.25-1.506 1.087-1.072.684-1.365 1.51-.229L8 1.126l1.356.702z" />
                        <path d="M4 11.794V16l4-1 4 1v-4.206l-2.018.306L8 13.126 6.018 12.1z" />
                      </svg>
                      {placeNames[index]}
                    </h3>
                    <p>{`${result.imePoster}`}</p>
                    <p>{`${result.imeAutor} ${result.prezimeAutor}`}</p>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      )}
    </>
  );
}
