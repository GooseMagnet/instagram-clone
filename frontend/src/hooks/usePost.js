import { request } from "../api/Axios";
import { useQuery } from "react-query";

const getPost = (postId) => {
  return request({ url: `/posts/${postId}` });
};

export const usePost = (postId) => {
  const { data } = useQuery(`posts/${postId}`, () =>
    getPost(postId)
  );
  return { post: data.data };
};
