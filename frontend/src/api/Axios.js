import axios from "axios";

const client = axios.create({});

export const request = ({ ...options }) => {
  const onSuccess = (response) => response;
  const onError = (error) => {
    return error;
  };
  return client(options).then(onSuccess).catch(onError);
};
