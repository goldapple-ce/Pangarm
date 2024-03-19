import instance from "../utils/http-commons";
// import { useQuery } from "@tanstack/react-query";

// const QUERY_KEY = "precedent";

export const getPrecedent = async (
  content: string,
  page: number,
  size: number,
) => {
  const body = {
    content,
  };
  const res = await instance.post(`/precedent?page=${page}&size=${size}`, body);

  return res.data;
};
