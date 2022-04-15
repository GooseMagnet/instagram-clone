import { Link, useParams } from "react-router-dom";
import { usePost } from "../../hooks/usePost";
import { IoChatbubbleOutline, IoPaperPlaneOutline } from "react-icons/io5";
import { MdVerified } from "react-icons/md";
import { BsThreeDots } from "react-icons/bs";
import { BiBookmark } from "react-icons/bi";
import { IoMdHeartEmpty } from "react-icons/io";
import { VscSmiley } from "react-icons/vsc";

import "./style.css";

const Post = () => {
  const postId = useParams().postId;
  const { post } = usePost(postId);

  // Get comments
  const comments = [
    {
      id: 1,
      name: "galimatias",
      comment:
        "The great @bas blessing us with a summer pack this friday find me on track #4" +
        "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quasi inventore est debitis consequuntur reprehenderit odit, vel tempora voluptas, adipisci deserunt ab iusto perspiciatis facere quaerat quidem, autem maxime impedit. Odio?",
      date: new Date(),
    },
    {
      id: 2,
      name: "pocketelephantmusi",
      comment: "We missed you gali",
      date: new Date(),
    },
    {
      id: 3,
      name: "aneeshchegapa",
      comment: "The great @bas blessing yussss <3",
      date: new Date(),
    },
    {
      id: 4,
      name: "toasterino",
      comment: "Yessssssssssss!!!!!!!!",
      date: new Date(),
    },
    {
      id: 5,
      name: "toasterino",
      comment: "Yessssssssssss!!!!!!!!",
      date: new Date(),
    },
    {
      id: 6,
      name: "toasterino",
      comment: "Yessssssssssss!!!!!!!!",
      date: new Date(),
    },
    {
      id: 7,
      name: "toasterino",
      comment: "Yessssssssssss!!!!!!!!",
      date: new Date(),
    },
    {
      id: 8,
      name: "toasterino",
      comment: "Yessssssssssss!!!!!!!!",
      date: new Date(),
    },
    {
      id: 9,
      name: "toasterino",
      comment: "Yessssssssssss!!!!!!!!",
      date: new Date(),
    },
    {
      id: 10,
      name: "toasterino",
      comment: "Yessssssssssss!!!!!!!!",
      date: new Date(),
    },
    {
      id: 11,
      name: "toasterino",
      comment: "Yessssssssssss!!!!!!!!",
      date: new Date(),
    },
    {
      id: 12,
      name: "toasterino",
      comment: "Yessssssssssss!!!!!!!!",
      date: new Date(),
    },
    {
      id: 13,
      name: "toasterino",
      comment: "Yessssssssssss!!!!!!!!",
      date: new Date(),
    },
    {
      id: 14,
      name: "toasterino",
      comment: "Yessssssssssss!!!!!!!!",
      date: new Date(),
    },
    {
      id: 15,
      name: "toasterino",
      comment: "Yessssssssssss!!!!!!!!",
      date: new Date(),
    },
    {
      id: 16,
      name: "toasterino",
      comment: "Yessssssssssss!!!!!!!!",
      date: new Date(),
    },
    {
      id: 17,
      name: "toasterino",
      comment: "Yessssssssssss!!!!!!!!",
      date: new Date(),
    },
    {
      id: 18,
      name: "toasterino",
      comment: "Yessssssssssss!!!!!!!!",
      date: new Date(),
    },
    {
      id: 19,
      name: "toasterino",
      comment: "Yessssssssssss!!!!!!!!",
      date: new Date(),
    },
    {
      id: 20,
      name: "toasterino",
      comment: "Yessssssssssss!!!!!!!!",
      date: new Date(),
    },
    {
      id: 21,
      name: "toasterino",
      comment: "Yessssssssssss!!!!!!!!",
      date: new Date(),
    },
    {
      id: 22,
      name: "toasterino",
      comment: "Yessssssssssss!!!!!!!!",
      date: new Date(),
    },
    {
      id: 23,
      name: "toasterino",
      comment: "Yessssssssssss!!!!!!!!",
      date: new Date(),
    },
  ];

  return (
    <section className="post">
      <div className="media">
        <img src={post.path} alt="media" />
      </div>
      <div className="text">
        <section className="poster">
          <div>
            <Link to="/goose">
              <img src="/johnmayer.jpg" alt="profile" />
            </Link>
            <span>galimatias</span>
            <MdVerified color="3A89D4" size={22} />•
            <Link to="#">Following</Link>
          </div>
          <BsThreeDots size={20} />
        </section>
        <section className="comments">
          <ul>
            <Comments comments={comments} />
          </ul>
        </section>
        <section className="actions">
          <div className="actions__icons">
            <div>
              <IoMdHeartEmpty size={25} />
              <IoChatbubbleOutline size={25} />
              <IoPaperPlaneOutline size={25} />
            </div>
            <BiBookmark size={25} />
          </div>
          <span>
            Liked by goose and others
            <br />2 Days ago
          </span>
        </section>
        <section className="add-comment">
          <form>
            <div>
              <VscSmiley size={25} />
              <input type="text" placeholder="Add a comment..." />
            </div>
            <Link to="#">Post</Link>
          </form>
        </section>
      </div>
    </section>
  );
};

const Comments = ({ comments }) =>
  comments.map((c) => (
    <li key={c.id} className="comment">
      <div className="comment__picture">
        <Link to="/goose">
          <img src="/johnmayer.jpg" alt="profile" />
        </Link>
      </div>
      <div className="comment__text">
        <p>
          {c.name + " "}
          <MdVerified color="3A89D4" size={14} />
          {" " + c.comment}
          <br />
          {c.date.toString()}
        </p>
      </div>
    </li>
  ));

export default Post;
