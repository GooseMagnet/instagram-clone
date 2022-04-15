import axios from "axios";

const client = axios.create({});

export const request = ({ ...options }) => {
  const onSuccess = (response) => response;
  return client(options)
    .then(onSuccess)
    .catch((err) => err);
};
