import {
  AiFillHome,
  AiOutlinePlusSquare,
  AiOutlineCompass,
} from "react-icons/ai";
import { IoPaperPlaneOutline } from "react-icons/io5";
import { IoMdHeartEmpty } from "react-icons/io";
import { IoSearchOutline, IoPersonCircle } from "react-icons/io5";

import "./style.css";

const Navigation = () => {
  return (
    <nav className="navigation">
      <div>
        <img src="/wordmark-sharp.png" alt="instagram" />
        <div className="navigation__search">
          <IoSearchOutline size={20} />
          <input type="text" placeholder="Search" />
        </div>
        <ul>
          <li>
            <AiFillHome size={25} />
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
          <li>
            <IoPersonCircle size={25} color="lightgrey" />
          </li>
        </ul>
      </div>
    </nav>
  );
};

export default Navigation;
