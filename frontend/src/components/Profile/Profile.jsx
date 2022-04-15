import React from "react";

import { Link, useParams } from "react-router-dom";
import { BsFillPersonCheckFill } from "react-icons/bs";
import { MdVerified } from "react-icons/md";
import { BiChevronDown } from "react-icons/bi";

import { useProfile } from "../../hooks/useProfile";
import { usePosts } from "../../hooks/usePosts";
import Filters from "../GridFilters/GridFilters";

import "./style.css";
import { useFollowers } from "../../hooks/useFollowers";
import { useFollowing } from "../../hooks/useFollowing";

const Profile = () => {
  const username = useParams().username;
  const { profile } = useProfile(username);
  const { posts } = usePosts(profile.id);
  const { followers } = useFollowers(profile.id);
  const { following } = useFollowing(profile.id);
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
              <span>{posts.length} posts</span>
            </li>
            <li>
              <span>{followers.count} followers</span>
            </li>
            <li>
              <span>{following.count} following</span>
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
      <PhotosGrid photos={posts} />
    </section>
  );
};

const PhotosGrid = ({ photos }) => {
  const grid = photos.map((p, i) => {
    const imgPath = p.path;
    const lastSlash = imgPath.lastIndexOf("/");
    const extension = imgPath.lastIndexOf(".");
    const fileId = imgPath.substring(lastSlash + 1, extension);

    return (
      <Link key={fileId} to={`/posts/${fileId}`}>
        <img src={imgPath} alt={i} width="100%" className="grid__item" />
      </Link>
    );
  });

  return <section className="photos">{grid}</section>;
};

export default Profile;
