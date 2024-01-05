import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import Home from "./pages/Home";
import Conference from "./pages/Conference";
import Register from "./pages/Register";
import Posters from "./pages/Posters";
import Photos from "./pages/Photos";
import Patrons from "./components/Patrons";
import NewConference from "./pages/NewConference";
import UsersList from "./pages/UsersList";

const AppRouter = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/conference" element={<Conference />} />
        <Route path="/posters" element={<Posters />} />
        <Route path="/photos" element={<Photos />} />
        <Route path="/patrons" element={<Patrons />} />
        <Route path="/register" element={<Register />} />
        <Route path="/newConference" element={<NewConference />} />
        <Route path="/users" element={<UsersList />} />
      </Routes>
    </Router>
  );
};

export default AppRouter;
