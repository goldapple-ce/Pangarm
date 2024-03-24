import axios from "axios";

const instance = axios.create({
  baseURL: "",
  timeout: 1000,
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

instance.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;

    // 인증, 인가가 굳이 필요하지 않은 요청은 토큰 검사를 생략한다.
    if(originalRequest.skipAuth) {
      return Promise.reject(error);
    }

    if (error.response.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;
      const refreshToken = localStorage.getItem("refreshToken");
      try {
        const { data } = await instance.post("/member/reissue", {
          refreshToken,
        });
        localStorage.setItem("accessToken", data.accessToken);
        instance.defaults.headers.common["Authorization"] =
          `Bearer ${data.accessToken}`;
        return instance(originalRequest);
      } catch (refreshError) {
        console.error("토큰 재발급 실패: ", refreshError);
      }
    }

    return Promise.reject(error);
  },
);

export default instance;
