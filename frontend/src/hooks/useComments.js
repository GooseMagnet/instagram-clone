import { request } from "../api/Axios";
import { useQuery } from "react-query";

export const useComments = (postId) => {
  const url = `/posts/${postId}.png/comments`;
  const { data } = useQuery(url, () => request({ url: url }));
  return { comments: data.data.content };
};
