import React from "react";
import Filters from "../GridFilters/GridFilters";
import NotFound from "../NotFound/NotFound";

import { Link, useParams } from "react-router-dom";
import { BsFillPersonCheckFill } from "react-icons/bs";
import { MdVerified } from "react-icons/md";
import { BiChevronDown } from "react-icons/bi";
import { useProfile } from "../../hooks/useProfile";
import { usePosts } from "../../hooks/usePosts";
import { useFollowers } from "../../hooks/useFollowers";
import { useFollowing } from "../../hooks/useFollowing";
import { useCountPosts } from "../../hooks/useCountPosts";

import "./style.css";

const Profile = () => {
  const { username } = useParams();

  const { profile } = useProfile(username);
  const { count } = useCountPosts(profile?.id);
  const { followers } = useFollowers(profile?.id);
  const { following } = useFollowing(profile?.id);
  const { posts } = usePosts(profile?.id);
  const [activeFilter, setActiveFilter] = React.useState(0);

  if (!profile) {
    return <NotFound />;
  }

  return (
    <section className="profile">
      <header>
        <img src={profile.avatarPath} alt="profile" />
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
              <span>{count.count} posts</span>
            </li>
            <li>
              <span>{followers.count} followers</span>
            </li>
            <li>
              <span>{following.count} following</span>
            </li>
          </ul>
          <div className="profile__info__description">
            <p>{profile.description}</p>
          </div>
        </div>
      </header>
      {posts ? (
        <>
          <Filters
            activeFilter={activeFilter}
            onSetActiveFilter={setActiveFilter}
          />
          <PhotosGrid photos={posts} />
        </>
      ) : (
        <PrivateProfile />
      )}
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

const PrivateProfile = () => (
  <section className="privateprofile">
    <span>This Account is Private</span>
    <span>Follow to see their photos and videos.</span>
  </section>
);

export default Profile;
