import { request } from "../api/Axios";
import { useQuery } from "react-query";

const getUserContents = (userId) => {
  return request({ url: `/users/${userId}/contents` });
};

export const useUserContents = (userId) => {
  const { data } = useQuery(`users/${userId}/contents`, () =>
    getUserContents(userId)
  );
  return { contents: data.data };
};
