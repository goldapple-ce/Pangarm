import { http, HttpResponse } from "msw";
import ERR_MSG from "./ErrorMessage";

type SignUpRequestBody = {
  email: string;
  password: string;
  name: string;
  gender: number;
  job: string;
};

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

    return HttpResponse.json(
      {
        code,
        message,
      },
      {
        status,
      }
    );
  }),

  http.post("member/login", () => {}),

  http.post("member/reissue", () => {}),
  http.post("member/precedent", () => {}),
  http.get("member/precedent", () => {}),
  http.post("member/keyword-subscribe", () => {}),
];
