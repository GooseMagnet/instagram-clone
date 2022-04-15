import {
  AiFillHome,
  AiOutlinePlusSquare,
  AiOutlineCompass,
} from "react-icons/ai";
import { IoPaperPlaneOutline } from "react-icons/io5";
import { IoMdHeartEmpty } from "react-icons/io";
import { IoSearchOutline } from "react-icons/io5";

import "./style.css";
import { Link } from "react-router-dom";

const Navigation = ({ user }) => {
  const profileThumbnail = user ? (
    <AuthenticatedProfilePicture user={user} />
  ) : (
    <UnauthenticatedProfilePicture />
  );

  return (
    <nav className="navigation">
      <div>
        <Link to="/">
          <img src="/wordmark-sharp.png" alt="instagram" />
        </Link>
        <div className="navigation__search">
          <IoSearchOutline size={20} />
          <input type="text" placeholder="Search" />
        </div>
        <ul>
          <li>
            <Link to="/">
              <AiFillHome size={25} />
            </Link>
          </li>
          <li>
            <IoPaperPlaneOutline size={25} />
          </li>
          <li>
            <AiOutlinePlusSquare size={25} />
          </li>
          <li>
            <AiOutlineCompass size={25} />
          </li>
          <li>
            <IoMdHeartEmpty size={25} />
          </li>
          <li>{profileThumbnail}</li>
        </ul>
      </div>
    </nav>
  );
};

const AuthenticatedProfilePicture = ({ user }) => (
  <Link to={`/${user.username}`}>
    <img src={`${user.avatar}`} alt="profile" className="navigation__avatar" />
  </Link>
);

const UnauthenticatedProfilePicture = () => (
  <Link to={`/`}>
    <img src="/no-avatar.png" alt="profile" className="navigation__avatar" />
  </Link>
);

export default Navigation;
