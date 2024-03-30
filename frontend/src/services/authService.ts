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

// 회원 정보 조회
export const getUserInfo = async () => {
  const response = await instance.get("/member");
  return response;
};

// 판례 추천
export const getRecommendPrecedent = async () => {
  const response = await instance.get("/member");
  return response;
};

// 뉴스 구독
export const postSubscribeNewsKeyword = async (categoryId: number) => {
  const response = await instance.post(
    "/member/category-subscribe",
    { categoryId },
    {
      headers: {
        "Content-Type": "text/plain",
      },
    },
  );
  return response;
};

// 판례 북마크
export const postSubscribeBookmark = async (precedentId: string) => {
  const response = await instance.post(`/member/precedent`, {
    params: {
      id: precedentId,
    },
  });
  return response;
};

// 검색 히스토리
export const getSearchHistory = async () => {
  const response = await instance.get("api/member/search/history");
  return response;
};

// 회원 정보 수정
export const editInfo = async (editInfoData: EditFormInput) => {
  const response = await instance.post("", editInfoData);
  return response;
};
