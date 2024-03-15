import { http, HttpResponse } from "msw";
import ERR_MSG from "./ErrorMessage";

type SignUpRequestBody = {
  email: string;
  password: string;
  name: string;
  gender: number;
  job: string;
};

type TokenData = {
  accessToken?: string;
  refreshToken?: string;
};

interface LoginRequestBody {
  email: string;
  password: string;
}

interface LoginResponseBody extends LoginRequestBody {
  data?: TokenData;
  code?: string;
  message: string;
}

const regexEmail = new RegExp("[a-z0-9]+@[a-z]+.[a-z]{2,3}");

export const memberHandlers = [
  // 회원가입
  http.post("/member/sign-up", async ({ request }) => {
    const data = (await request.json()) as SignUpRequestBody;
    const { email, password, name, job } = data;
    console.log(`[MSW-memberSignIn]`);
    console.log(data);

    let status = 200;
    let message = "회원가입에 성공했습니다.";
    let code = "SUCCESS";

    if (!regexEmail.test(email) || password.length === 0 || name.length === 0 || job.length === 0) {
      code = "M001";
      status = 409;
      message = ERR_MSG.M001;
    }

    if (status !== 200) {
      return HttpResponse.json(
        {
          code,
          message,
        },
        {
          status,
        }
      );
    }

    return HttpResponse.json(
      {
        message,
      },
      {
        status,
      }
    );
  }),

  // 로그인
  http.post<never, LoginRequestBody>("member/login", async ({ request }) => {
    const reqData = await request.json();
    const { email, password } = reqData;

    let code = "";
    let message = "";
    if (!regexEmail.test(email) || password.length === 0) {
      code = "G002";
      message = ERR_MSG.G002;
    }

    if (code.length === 0) {
      return HttpResponse.json(
        {
          code,
          message,
        },
        {
          status: 409,
        }
      );
    }

    return HttpResponse.json(
      {
        data: {
          accessToken: "SomeAccessToken",
          refreshToken: "SomeRefreshToken",
        },
        message: "로그인이 정상적으로 실행됐습니다.",
      },
      {
        status: 200,
      }
    );
  }),

  http.post("member/reissue", () => {}),
  http.post("member/precedent", () => {}),
  http.get("member/precedent", () => {}),
  http.post("member/keyword-subscribe", () => {}),
];
