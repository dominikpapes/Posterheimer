import ListGroup from "./components/ListGroup";

function Home() {
  let items = ["Konferencija 1", "Konferencija 2"];
  const onSelectKonferencija = () => {};
  return (
    <div className="container text-center">
      <ListGroup
        items={items}
        heading="Dostupne konferencije"
        onSelectItem={onSelectKonferencija}
      ></ListGroup>
    </div>
  );
}

export default Home;
