import { request } from "../api/Axios";
import { useQuery } from "react-query";

const url = "/sessions/me";

const getCurrentUser = () => request({ url });

export const useCurrentUser = () => {
  const { data } = useQuery(url, getCurrentUser);
  return { user: data?.data };
};
