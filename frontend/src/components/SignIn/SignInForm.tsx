import { Link, useNavigate } from "react-router-dom";
import { SubmitHandler, useForm } from "react-hook-form";
import { useMutation } from "@tanstack/react-query";
import axios, { HttpStatusCode } from "axios";

import { SignInFormInput } from "@/types";
import { useAuthStore } from "@/stores/authStore";
import { signIn } from "@/services/authService";

export default function SignInForm() {
  const {
    register,
    handleSubmit,
    setError,
    formState: { errors },
  } = useForm<SignInFormInput>();
  const setSignedIn = useAuthStore((state) => state.setSignedIn);
  const navigate = useNavigate();

  const { mutate } = useMutation({
    mutationFn: signIn,
    onSuccess: (response) => {
      const headers = response.headers;
      const { accessToken, refreshToken } = JSON.parse(headers.authorization);

      const status = response.status;

      if (status === HttpStatusCode.Ok) {
        setSignedIn();
        localStorage.setItem("accessToken", accessToken);
        localStorage.setItem("refreshToken", refreshToken);
        navigate("/");
      }
    },
    onError: (error) => {
      if (axios.isAxiosError(error)) {
        const response = error.response;
        if (response) {
          const { businessCode } = response.data;
          // console.log(businessCode, errorMessage);

          if (businessCode === "G003") {
            setError("root.signInError", {
              message: "이메일 또는 비밀번호를 잘못 입력했습니다.",
            });
          }
        } else {
          // 네트워크 오류 또는 오류 응답이 없는 경우의 처리
          console.error("An error occurred:", error.message);
        }
      } else {
        // Axios 오류가 아닌 다른 종류의 오류 처리
        console.error("An unexpected error occurred:", error);
      }
    },
  });

  const onSubmit: SubmitHandler<SignInFormInput> = (data: SignInFormInput) => {
    mutate(data);
  };

  return (
    <form
      className="h-full w-1/2 border border-lightgray p-20 pb-10 shadow-md"
      onSubmit={handleSubmit(onSubmit)}
    >
      <div className="mb-4 font-TitleLight text-4xl">로그인</div>
      <div>
        <div className="flex flex-col gap-2">
          <input
            {...register("email")}
            type="email"
            placeholder="이메일을 입력해주세요"
            className="w-full rounded-md bg-lightblue p-3"
            required
          />
          <input
            {...register("password")}
            type="password"
            placeholder="비밀번호을 입력해주세요"
            className="w-full rounded-md bg-lightblue p-3"
            autoComplete="off"
            required
          />
        </div>
        <p className="text-md text-red-600">
          {errors?.root?.signInError.message}
        </p>
      </div>

      <div className="mt-20">
        <button className="flex w-full items-center justify-center rounded-xl bg-navy p-2 text-2xl text-white">
          로그인
        </button>
        <Link to={"/signup"}>
          <div className="mt-2 w-full text-end text-lightgray transition duration-300 ease-in-out hover:text-gray">
            아직 회원이 아니신가요?
          </div>
        </Link>
      </div>
    </form>
  );
}
