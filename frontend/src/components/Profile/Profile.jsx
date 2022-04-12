import React from "react";
import { Link, useParams } from "react-router-dom";
import "./style.css";
import {
  BsFilePersonFill,
  BsGrid3X3Gap,
  BsFillPersonCheckFill,
} from "react-icons/bs";
import { FiPlayCircle } from "react-icons/fi";
import { MdOutlinePersonPin, MdVerified } from "react-icons/md";
import { BiChevronDown, BiMoviePlay } from "react-icons/bi";
import { AiOutlinePlayCircle } from "react-icons/ai";

import pic1 from "../../assets/iOS-13-Wallpapers (1).jpeg";
import pic2 from "../../assets/iOS-13-Wallpapers (2).jpeg";
import pic3 from "../../assets/iOS-13-Wallpapers (3).jpeg";
import pic4 from "../../assets/iOS-13-Wallpapers (4).jpeg";
import pic5 from "../../assets/iOS-13-Wallpapers (5).jpeg";
import pic6 from "../../assets/iOS-13-Wallpapers (6).jpeg";
import pic7 from "../../assets/iOS-13-Wallpapers (7).jpeg";
import pic8 from "../../assets/iOS-13-Wallpapers (8).jpeg";

const Profile = () => {
  // TODO - Request for profile
  const username = useParams().username;

  const pictures = [pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8];

  const [activeFilter, setActiveFilter] = React.useState(0);

  return (
    <section className="profile">
      <header>
        <img src="johnmayer.jpg" alt="profile" />
        <div className="profile__info">
          <div>
            <h2>{username}</h2>
            <MdVerified color="3A89D4" />
            <button>Message</button>
            <button>
              <BsFillPersonCheckFill />
            </button>
            <button>
              <BiChevronDown />
            </button>
          </div>
          <ul>
            <li>
              <span>1,648 posts</span>
            </li>
            <li>
              <span>5.5m followers</span>
            </li>
            <li>
              <span>1,699 following</span>
            </li>
          </ul>
          <div className="profile__info__description">
            <p>Followed by johnkennmortensen, aleclehrman, tommisch +10 more</p>
          </div>
        </div>
      </header>
      <Filters filter={activeFilter} />
      <PhotosGrid photos={pictures} />
    </section>
  );
};

const Filters = ({ filter }) => {
  return (
    <ul className="filters">
      <li className={filter === 0 ? "selected" : ""}>
        <BsGrid3X3Gap />
        <span>POSTS</span>
      </li>
      <li className={filter === 1 ? "selected" : ""}>
        <BiMoviePlay />
        <span>REELS</span>
      </li>
      <li className={filter === 2 ? "selected" : ""}>
        <AiOutlinePlayCircle />
        <span>VIDEOS</span>
      </li>
      <li className={filter === 3 ? "selected" : ""}>
        <MdOutlinePersonPin />
        <span>TAGGED</span>
      </li>
    </ul>
  );
};

const PhotosGrid = ({ photos }) => {
  const grid = photos.map((p, i) => (
    <img
      key={i}
      src={p}
      alt={`number-${i}`}
      width="100%"
      className="grid__item"
    />
  ));

  return <section className="photos">{grid}</section>;
};

export default Profile;
