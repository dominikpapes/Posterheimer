import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import Home from "./pages/Home";
import Conference from "./pages/Conference";

const AppRouter = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/conference" element={<Conference />} />
      </Routes>
    </Router>
  );
};

export default AppRouter;
