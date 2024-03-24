import instance from "@/utils/http-commons";
import { SignInFormInput } from "@/types";

export const signIn = async (signInData: SignInFormInput) => {
  const response = await instance.post("/member/sign-in", signInData);
  return response;
};
