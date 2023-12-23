import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import Home from "./pages/Home";
import Conference from "./pages/Conference";
import Register from "./pages/Register";
import Posters from "./pages/Posters";
import Photos from "./pages/Photos";

const AppRouter = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/conference" element={<Conference />} />
        <Route path="/posters" element={<Posters />} />
        <Route path="/photos" element={<Photos />} />
        <Route path="/register" element={<Register />} />
      </Routes>
    </Router>
  );
};

export default AppRouter;
