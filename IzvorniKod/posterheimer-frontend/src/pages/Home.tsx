import { useEffect, useState } from "react";
import ListGroup from "../components/ListGroup";

function Home() {
  const [conferences, setConferences] = useState([]);

  useEffect(() => {
    fetch("api/konferencije")
      .then((res) => res.json())
      .then((conferences) => setConferences(conferences));
  }, []);

  const onSelectKonferencija = () => {};
  return (
    <div className="container text-center">
      <ListGroup
        conferences={conferences}
        heading="Dostupne konferencije"
        onSelectItem={onSelectKonferencija}
      ></ListGroup>
    </div>
  );
}

export default Home;
