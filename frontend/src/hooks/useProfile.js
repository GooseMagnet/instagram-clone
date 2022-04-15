import { request } from "../api/Axios";
import { useQuery } from "react-query";

const getProfile = (username) => request({ url: `/users/${username}` })

export const useProfile = (username) => {
  const { data } = useQuery(['users', username], () => getProfile(username));
  return { profile: data?.data };
};
