import instance from "../utils/http-commons";
// import { useQuery } from "@tanstack/react-query";

// const QUERY_KEY = "precedent";

// 판례 검색
export const postPrecedentSearch = async (
  content: string,
  page: number,
  size: number,
) => {
  const response = await instance.post(`/precedent`, {
    content: content,
    params: {
      page: page,
      size: size,
    },
  });
  return response;
};

// 판례 상세
export const getPrecedentDetail = async (caseNumber : string) => {
  const response = await instance.get(`/precedent`, {
    params: {
      caseNumber: caseNumber
    }
  });
  return response;
}


// 판례 다운로드
export const getPrecedentDownload = async (caseId: number) => {
  const response = await instance.get(`/precedent/download`, {
    params: {
      id: caseId
    }
  });
  return response;
}

// 북마크한 판례 보기
export const getBookmarkedPrecedent = async () => {
  const response = await instance.get(`/precedent/bookmarked`
  );
  return response;
}


// 본 판례 보기
export const getViewedPrecedent = async () => {
  const response = await instance.get(`/precedent/viewed`,);
  return response;
}


// 판례 요약 보기

// 판례 전문 보기
