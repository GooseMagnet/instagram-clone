import { request } from "../api/Axios";
import { useQuery } from "react-query";

export const useFollowing = (userId) => {
  const url = `/users/${userId}/following`;
  const { data } = useQuery(url, () => request({ url: url }));
  return { following: data.data };
};
