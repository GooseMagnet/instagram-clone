import { Routes, Route, Outlet } from "react-router-dom";
import React, { Suspense } from "react";

import Post from "./components/Post/Post";
import Profile from "./components/Profile/Profile";
import Login from "./components/Login/Login";
import Navigation from "./components/Navigation/Navigation";
import { useCurrentUser } from "./hooks/useCurrentUser";

import Loader from "./components/Loader/Loader";

const App = () => {
  const { user } = useCurrentUser();

  return user ? (
    <AuthenticatedApp user={user} />
  ) : (
    <Suspense fallback={<Loader />}>
      <UnauthenticatedApp />
    </Suspense>
  );
};

const AuthenticatedApp = ({ user }) => (
  <>
    <Navigation user={user} />
    <main className="main">
      <div>
        <Routes>
          <Route path="/" element={<Outlet />}>
            <Route exact path="/" element={<Home />} />
            <Route exact path="/:username" element={<Profile />} />
            <Route path="/posts/:postId" element={<Post />} />
          </Route>
        </Routes>
      </div>
    </main>
  </>
);

const UnauthenticatedApp = () => (
  <>
    <Routes>
      <Route exact path="/" element={<Login />} />
      <Route path="/" element={<WithNavigation />}>
        <Route exact path="/:username" element={<Profile />} />
        <Route path="/posts/:postId" element={<Post />} />
      </Route>
    </Routes>
  </>
);

const WithNavigation = () => (
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
  return <h1>Home</h1>;
};

export default App;
