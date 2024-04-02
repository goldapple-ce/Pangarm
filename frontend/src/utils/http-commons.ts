import axios from "axios";

const instance = axios.create({
  baseURL: "/api",
});

instance.interceptors.request.use(
  (config) => {
    const accessToken = localStorage.getItem("accessToken"); // 또는 쿠키에서 가져오기
    if (accessToken) {
      config.headers["Authorization"] = `Bearer ${accessToken}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  },
);

// instance.interceptors.response.use(
//   async (response) => {
//     // 정상 응답을 그대로 반환
//     return response;
//   },
//   async (error) => {
//     const originalRequest = error.config;

//     // 인증이 필요 없는 요청은 여기서 바로 에러를 반환
//     if (originalRequest.skipAuth) {
//       return Promise.reject(error);
//     }

//     // 이미 재시도한 요청은 또다시 시도하지 않음
//     if (originalRequest._retry) {
//       return Promise.reject(error);
//     }

//     if (error.response.status === HttpStatusCode.Unauthorized) {
//       originalRequest._retry = true; // 재시도 표시
//       const refreshToken = localStorage.getItem("refreshToken");

//       try {
//         const { data } = await instance.post("/member/reissue", {
//           refreshToken,
//         });

//         // 새로운 액세스 토큰으로 로컬 스토리지와 인스턴스 헤더 갱신
//         localStorage.setItem("accessToken", data.accessToken);
//         instance.defaults.headers.common["Authorization"] = `Bearer ${data.accessToken}`;

//         // 갱신된 토큰으로 원래의 요청 재시도
//         return instance(originalRequest);
//       } catch (refreshError) {
//         // 토큰 재발급 실패 시, 보다 구체적인 에러 핸들링을 수행
//         console.error("토큰 재발급 실패: ", refreshError);
//         // 필요한 경우, 여기서 사용자에게 로그아웃하거나 재로그인하도록 유도할 수 있음
//         return Promise.reject(refreshError);
//       }
//     }

//     // 기타 다른 상태 코드에 대한 에러 처리
//     return Promise.reject(error);
//   }
// );


export default instance;
