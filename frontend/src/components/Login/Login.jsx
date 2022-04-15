import { Link } from "react-router-dom";

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

export default Login;
