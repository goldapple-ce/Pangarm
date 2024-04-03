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
  const response = await instance.get(`/news/by-category`, {
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

// 뉴스 검색
export const getNewsListByKeyword = async (
  keyword: string,
  page: number,
  size: number,
) => {
  const response = await instance.get(`/news/search`, {
    params: {
      keyword,
      page,
      size,
    },
  });
  return response;
};

export const getCategoryList = async () => {
  const response = await instance.get(`/news/category-list`);
  return response;
};

// 뉴스 카테고리 리스트
export const getNewsCategoryList = async () => {
  const response = await instance.get("/news/category-list");
  return response;
};
