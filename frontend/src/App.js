import { Routes, Route, Outlet } from "react-router-dom";
import Profile from "./components/Profile/Profile";
import Login from "./components/Login/Login";
import Navigation from "./components/Navigation/Navigation";
import { QueryClientProvider, QueryClient } from "react-query";
import { Suspense } from "react";
import Post from "./components/Post/Post";

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      suspense: true,
    },
  },
});

const App = () => (
  <QueryClientProvider client={queryClient}>
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route path="/" element={<Main />}>
        <Route exact path="/" element={<Home />} />
        <Route exact path="/:username" element={<Profile />} />
        <Route path="/posts/:postId" element={<Post />} />
      </Route>
    </Routes>
  </QueryClientProvider>
);

const Main = () => (
  <>
    <Suspense fallback={<h1>Loading...</h1>}>
      <Navigation />
      <main className="main">
        <div>
          <Outlet />
        </div>
      </main>
    </Suspense>
  </>
);

const Home = () => {
  return <h1>Home</h1>;
};

export default App;
