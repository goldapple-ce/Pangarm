import instance from "@/utils/http-commons";

// 뉴스 전체 리스트
export const getNewsList = async (page: number, size: number) => {
  const response = await instance.get(`/news?page=${page}&size=${size}`);
  return response;
};

// 카테고리 별 뉴스 리스트
export const getCategoryNewsList = async (
  category: string,
  page: number,
  size: number,
) => {
  const response = await instance.get(`/news`, {
    params: {
      category: category,
      page: page,
      size: size,
    },
  });
  return response;
};

// 뉴스 상세
export const getNewsDetail = async (newsId: string) => {
  const response = await instance.get(`/news/${newsId}`);
  return response;
};

// 카테고리 구독 헤제
export const postUnsubscribeCategory = async (category: string) => {
  const response = await instance.post(
    `/member/category-unsubscribe`,
    { category },
    {
      headers: {
        "Content-Type": "text/plain",
      },
    },
  );
  return response;
};

// 카테고리 등록

// 카테고리 삭제

// 카테고리 수정
