import { request } from "../api/Axios";
import { useQuery } from "react-query";

export const useProfile = (username) => {
  const { data } = useQuery(`users/${username}`, () =>
    request({ url: `/users/${username}` })
  );

  return { profile: data.data };
};
