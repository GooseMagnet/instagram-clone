import { request } from "../api/Axios";
import { useQuery } from "react-query";

export const useCountPosts = (userId) => {
  const url = `/users/${userId}/posts/count`;
  const { data } = useQuery(url, () => request({ url: url }));
  return { count: data?.data };
};
