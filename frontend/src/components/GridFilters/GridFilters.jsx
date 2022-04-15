import "./style.css";

import { MdOutlinePersonPin } from "react-icons/md";
import { AiOutlinePlayCircle } from "react-icons/ai";
import { BiMoviePlay } from "react-icons/bi";
import { BsGrid3X3Gap } from "react-icons/bs";

const icons = [
  <BsGrid3X3Gap />,
  <BiMoviePlay />,
  <AiOutlinePlayCircle />,
  <MdOutlinePersonPin />,
];

const titles = ["POSTS", "REELS", "VIDEOS", "TAGGED"];

const createFilters = (activeFilter, onSetActiveFilter) =>
  [...Array(4).keys()].map((i) => (
    <li
      key={i}
      className={activeFilter === i ? "selected" : ""}
      onClick={() => onSetActiveFilter(i)}
    >
      {icons[i]}
      {titles[i]}
    </li>
  ));

const Filters = ({ activeFilter, onSetActiveFilter }) => (
  <ul className="filters">{createFilters(activeFilter, onSetActiveFilter)}</ul>
);

export default Filters;
