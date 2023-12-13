import { useEffect, useState } from "react";
import ConferencesList from "../components/ConferencesList";

function Home() {
  const [conferences, setConferences] = useState([]);

  useEffect(() => {
    fetch("/api/konferencije")
      .then((res) => res.json())
      .then((confs) => setConferences(confs));
  }, []);

  const onSelectKonferencija = () => {};
  return (
    <div className="container text-center">
      <ConferencesList
        conferences={conferences}
        heading="Dostupne konferencije"
        onSelectItem={onSelectKonferencija}
      ></ConferencesList>
    </div>
  );
}

export default Home;
