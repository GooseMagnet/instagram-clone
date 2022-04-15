import React from "react";

import { Link, useParams } from "react-router-dom";
import { BsFillPersonCheckFill } from "react-icons/bs";
import { MdVerified } from "react-icons/md";
import { BiChevronDown } from "react-icons/bi";

import { useProfile } from "../../hooks/getProfile";
import { useUserContents } from "../../hooks/getProfileContent";
import Filters from "../GridFilters/GridFilters";

import "./style.css";

const Profile = () => {
  const username = useParams().username;
  const { profile } = useProfile(username);
  const { contents } = useUserContents(profile.id);
  const [activeFilter, setActiveFilter] = React.useState(0);

  return (
    <section className="profile">
      <header>
        <img src={profile?.avatarPath} alt="profile" />
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
            <p>{profile?.profile?.description}</p>
          </div>
        </div>
      </header>
      <Filters
        activeFilter={activeFilter}
        onSetActiveFilter={setActiveFilter}
      />
      <PhotosGrid photos={contents.content} />
    </section>
  );
};

const PhotosGrid = ({ photos }) => {
  const grid = photos.map((p, i) => {
    const path = p.filePath;
    const lastSlash = path.lastIndexOf("/");
    const extension = path.lastIndexOf(".");
    const fileId = path.substring(lastSlash + 1, extension);

    return (
      <Link key={fileId} to={`/posts/${fileId}`}>
        <img src={path} alt={i} width="100%" className="grid__item" />
      </Link>
    );
  });

  return <section className="photos">{grid}</section>;
};

export default Profile;
