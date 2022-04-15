import { request } from "../api/Axios";
import { useQuery } from "react-query";

const getPosts = (userId) => {
  return request({ url: `/users/${userId}/posts` });
};

export const usePosts = (userId) => {
  const { data } = useQuery(`users/${userId}/posts`, () =>
    getPosts(userId)
  );
  return { posts: data.data };
};
