import instance from "@/utils/http-commons";
import { SignInFormInput, SignUpFormInput } from "@/types";

export const signIn = async (signInData: SignInFormInput) => {
  const response = await instance.post("/member/signin", signInData);
  return response;
};

export const signUp = async (signUpData: SignUpFormInput) => {
  const response = await instance.post("/member/signup", signUpData);
  return response;
}