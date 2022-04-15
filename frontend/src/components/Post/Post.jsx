import "./style.css";
import { useParams } from "react-router-dom";

const Post = () => {
  const postId = useParams().postId;
  return <h1>Post {postId}</h1>;
};

export default Post;
