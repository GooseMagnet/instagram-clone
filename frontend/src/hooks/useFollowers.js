import { request } from "../api/Axios";
import { useQuery } from "react-query";

export const useFollowers = (userId) => {
  const url = `/users/${userId}/followers`;
  const { data } = useQuery(url, () => request({ url: url }));
  return { followers: data.data };
};
