import { request } from "../api/Axios";
import { useQuery } from "react-query";

export const usePosts = (userId) => {
  const url = `users/${userId}/posts`;
  const { data } = useQuery(url, () => request({ url: url }));
  return { posts: data.data.content };
};
