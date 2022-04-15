import React from "react";
import { Link } from "react-router-dom";

import "./style.css";

const NotFound = () => (
  <section className="notfound">
    <h2>Sorry, this page isn't available.</h2>
    <br />
    <span>
      The link you followed may be broken, or the page may have been removed.
      <Link to={"/"}>
        <span> Go back to Instagram.</span>
      </Link>
    </span>
  </section>
);

export default NotFound;
