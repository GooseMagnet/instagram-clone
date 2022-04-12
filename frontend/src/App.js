import { Routes, Route, Outlet, Link } from "react-router-dom";
import Profile from "./components/Profile";
import Footer from "./components/Footer";

const App = () => (
  <Routes>
    {/* Logged in Routes */}
    <Route path="/" element={<LoggedIn />}>
      <Route exact path="/" element={<Home />} />
      <Route path="/:username" element={<Profile />} />
    </Route>

    {/* Logged out Routes */}
    <Route path="/password/reset" element={<PasswordReset />} />
    <Route path="/login" element={<Login />} />
  </Routes>
);

const Home = () => {
  // Fetch photos from all this user follows, sorted chronologically
  return <h1>Home</h1>;
};

const LoggedIn = () => (
  <>
    <Outlet />
    <Footer />
  </>
);

const Login = () => {
  return (
    <section className="login">
      <img src="wordmark.png" alt="Instagram" />
      <form className="login__form">
        <input type="text" placeholder="Phone number, username, or email" />
        <input type="password" placeholder="Password" />
        <button type="submit">Log In</button>
        <div className="login__or" style={{ display: "flex" }}>
          <div>―――――――</div>
          <div style={{ padding: "4px 8px 4px 8px" }}>OR</div>
          <div>―――――――</div>
        </div>
        <button className="login__facebook">Log in with Facebook</button>
        <Link to={"/password/reset"}>Forgot password?</Link>
      </form>
      <div className="signup">
        <p>Don't have an account?</p>
        <Link to={"/signup"}>Sign up</Link>
      </div>
      <div className="download">
        <p>Get the app.</p>
        <img src="/download-appstore.png" alt="Download on the App Store" />
        <img src="/download-googleplay.png" alt="Get it on Google Play" />
      </div>
    </section>
  );
};

const PasswordReset = () => <h1>Reset Password</h1>;

export default App;
