import React from "react";
import { Link } from "react-router-dom";
import { AiFillFacebook } from "react-icons/ai";

import "./style.css";
import { useMutation, useQueryClient } from "react-query";
import { request } from "../../api/Axios";

const Login = () => {
  const [username, setUsername] = React.useState("");
  const [password, setPassword] = React.useState("");

  const queryClient = useQueryClient();

  const login = (username, password) => {
    request({
      url: "/sessions",
      method: "POST",
      data: { usernameOrEmail: username, password },
      withCredentials: true,
    });
  };

  const { mutate } = useMutation("sessions", () => login(username, password), {
    // onSuccess: () => {
    //   queryClient.removeQueries("/sessions/me", { exact: true });
    // },
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    mutate(username, password);
  };

  return (
    <main className="main">
      <div>
        <section className="login">
          <div>
            <img src="/wordmark.png" alt="Instagram" />
            <form className="login__form" onSubmit={handleSubmit}>
              <input
                type="text"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                placeholder="Phone number, username, or email"
              />
              <input
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Password"
              />
              <button
                className="login__button"
                type="submit"
                disabled={!(username && password)}
              >
                Log In
              </button>
              <OauthSection />
            </form>
          </div>
          <SignupSection />
          <DownloadSection />
        </section>
      </div>
    </main>
  );
};

const OauthSection = () => (
  <>
    <div className="login__or" style={{ display: "flex" }}>
      <div className="login__or__line">―――――――――</div>
      <div style={{ padding: "4px 8px 4px 8px" }}>OR</div>
      <div className="login__or__line">―――――――――</div>
    </div>
    <Link className="facebook" to="#">
      {" "}
      <AiFillFacebook size={20} />
      Log in with Facebook
    </Link>
    <Link className="forgot" to={"/password/reset"}>
      Forgot password?
    </Link>
  </>
);

const SignupSection = () => (
  <div className="signup">
    <p>Don't have an account?</p>
    <Link to={"/signup"}>Sign up</Link>
  </div>
);

const DownloadSection = () => (
  <section className="download">
    <p>Get the app.</p>
    <div>
      <img src="/download-appstore.png" alt="Download on the App Store" />
      <img src="/download-googleplay.png" alt="Get it on Google Play" />
    </div>
  </section>
);

export default Login;
