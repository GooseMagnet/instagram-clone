import { request } from "../api/Axios";
import { useQuery } from "react-query";

export const useCurrentUser = () => {
  const url = '/users/me';
  const { data } = useQuery(url, () => request({ url: url }));
  return { comments: data.data.content };
};
