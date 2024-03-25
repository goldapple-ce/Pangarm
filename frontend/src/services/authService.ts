import instance from "@/utils/http-commons";
import { SignInFormInput, SignUpFormInput } from "@/types";

export const signIn = async (signInData: SignInFormInput) => {
  const response = await instance.post("/member/sign-in", signInData);
  return response;
};

export const signUp = async (signUpData: SignUpFormInput) => {
  const response = await instance.post("/member/sign-up", signUpData);
  return response;
}