import { FiltersType } from "@/types";
import instance from "../utils/http-commons";
// import { useQuery } from "@tanstack/react-query";

// const QUERY_KEY = "precedent";

// 판례 검색

export type postPrecedentSearchType = {
  situation: string;
  page: number;
  size: number;
  filter: FiltersType;
};

export const postPrecedentSearch = async ({
  situation,
  page,
  size,
  filter,
}: postPrecedentSearchType) => {
  const response = await instance.post(
    `/precedent/search`,
    {
      situation: situation,
      keywordList: filter.keywordList,
      duration: filter.duration,
      preclude: filter.preclude,
      minimumSimularity: filter.minimumSimilarity,
    },
    {
      params: {
        page: page,
        size: size,
      },
    },
  );
  // console.log(response);
  return response;
};

// 판례 요약
export const getPrecedentSummary = async (caseNumber: string) => {
  const response = await instance.get(`/precedent/search/summary`, {
    params: {
      caseNumber: caseNumber,
    },
  });
  return response;
};

// 판례 상세
export const getPrecedentDetail = async (caseNumber: string) => {
  const response = await instance.get(`/precedent/search/detail`, {
    params: {
      caseNumber: caseNumber,
    },
  });
  return response;
};

// 판례 다운로드
export const getPrecedentDownload = async (caseId: number) => {
  const response = await instance.get(`/precedent/download`, {
    params: {
      id: caseId,
    },
  });
  return response;
};

// 북마크한 판례 보기
export const getBookmarkedPrecedent = async () => {
  const response = await instance.get(`/precedent/bookmarked`);
  return response;
};

// 본 판례 보기
export const getViewedPrecedent = async () => {
  const response = await instance.get(`/precedent/viewed`);
  return response;
};

// 판례 북마크

// 판례 요약 보기

// 판례 전문 보기
