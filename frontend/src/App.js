import { Routes, Route, Outlet } from "react-router-dom";
import Profile from "./components/Profile/Profile";
import Login from "./components/Login";
import Navigation from "./components/Navigation/Navigation";

const App = () => (
  <Routes>
    <Route path="/login" element={<Login />} />

    <Route path="/" element={<Main />}>
      <Route exact path="/" element={<Home />} />
      <Route path="/:username" element={<Profile />} />
    </Route>
  </Routes>
);

const Main = () => (
  <>
    <Navigation />
    <main className="main">
      <div>
        <Outlet />
      </div>
    </main>
  </>
);

const Home = () => {
  // Fetch photos from all this user follows, sorted chronologically
  return <h1>Home</h1>;
};

export default App;
