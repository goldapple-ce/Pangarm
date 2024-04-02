import instance from "@/utils/http-commons";
import { EditFormInput, SignInFormInput, SignUpFormInput } from "@/types";

// 회원가입
export const signIn = async (signInData: SignInFormInput) => {
  const response = await instance.post("/member/signin", signInData);
  return response;
};

// 로그인
export const signUp = async (signUpData: SignUpFormInput) => {
  const response = await instance.post("/member/signup", signUpData);
  return response;
};

// 토큰 재발급
export const reissue = async (refreshToken: string) => {
  const response = await instance.post("/member/reissue", {
    refreshToken,
  });
  return response;
};

// 회원 정보 조회
export const getMemberInfo = async () => {
  const response = await instance.get("/member");
  return response;
};

// 판례 추천
export const getRecommendPrecedent = async () => {
  const response = await instance.get("/member");
  return response;
};

// 뉴스 카테고리 구독
export const postSubscribeNewsCategory = async (category: string) => {
  const response = await instance.post(
    `/member/category-subscribe?category_id=${category}`,
  );
  return response;
};

// 뉴스 카테고리 구독 해제
export const postUnsubscribeNewsCategory = async (category: string) => {
  const response = await instance.post(
    `/member/category-unsubscribe?category_id=${category}`,
  );
  return response;
};

// 판례 북마크
export const putSubscribeBookmark = async (precedentId: number) => {
  const response = await instance.put(`/member/precedent`, null, {
    params: {
      id: precedentId,
    },
  });
  return response;
};

// 검색 히스토리
export const getSearchHistory = async () => {
  const response = await instance.get("/member/search/history");
  return response;
};

export const getSubscribedCategory = async () => {
  const response = await instance.get("/member/category");
  return response;
};

// 회원 정보 수정
export const editInfo = async (editInfoData: EditFormInput) => {
  const response = await instance.post("", editInfoData);
  return response;
};
// 카테고리 구독 헤제
export const postUnsubscribeCategory = async (categoryId: number) => {
  const response = await instance.post(
    `/member/category-unsubscribe/${categoryId}`,
  );
  return response;
};
