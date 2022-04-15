import React from "react";

import { Link, useParams } from "react-router-dom";
import { usePost } from "../../hooks/usePost";
import { IoChatbubbleOutline, IoPaperPlaneOutline } from "react-icons/io5";
import { MdVerified } from "react-icons/md";
import { BsThreeDots } from "react-icons/bs";
import { BiBookmark } from "react-icons/bi";
import { IoMdHeartEmpty } from "react-icons/io";
import { VscSmiley } from "react-icons/vsc";
import { useComments } from "../../hooks/useComments";

import "./style.css";

const Post = () => {
  const postId = useParams().postId;
  const { post } = usePost(postId);
  const { comments } = useComments(postId);

  const [newComment, setNewComment] = React.useState("");

  return (
    <section className="post">
      <div className="media">
        <img src={post.path} alt="media" />
      </div>
      <div className="text">
        <section className="poster">
          <div>
            <Link to={`/${post.user.username}`}>
              <img src={`${post.user.avatar}`} alt="profile" />
            </Link>
            <Link to={`/${post.user.username}`}>
              <b>{post.user.username}</b>
            </Link>
            <MdVerified color="3A89D4" size={34} />•
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
          <form onSubmit={() => null}>
            <div>
              <VscSmiley size={25} />
              <input
                type="text"
                placeholder="Add a comment..."
                value={newComment}
                onChange={(e) => setNewComment(e.target.value)}
              />
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
    <li key={c.commentId} className="comment">
      <div className="comment__picture">
        <Link to={`/${c.user.username}`}>
          <img src={c.user.avatar} alt="profile" />
        </Link>
      </div>
      <div className="comment__text">
        <p>
          <Link to={`/${c.user.username}`}>
            <b>{c.user.username + " "}</b>
          </Link>
          <MdVerified color="3A89D4" size={12} />
          {" " + c.content}
          <br />
          {c.dateCreated}
        </p>
      </div>
    </li>
  ));

export default Post;
