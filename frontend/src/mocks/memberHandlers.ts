import { http, HttpResponse } from "msw";

export const memberHandlers = [
  http.get("/member/login", () => {
    return HttpResponse.json();
  }),
];
