import { Link, useNavigate } from "react-router-dom";
import { SubmitHandler, useForm } from "react-hook-form";
import { useMutation } from "@tanstack/react-query";

import { SignInFormInput } from "@/types";
import { useAuthStore } from "@/stores/authStore";
import { signIn } from "@/services/authService";

export default function SignInForm() {
  const { register, handleSubmit } = useForm<SignInFormInput>();
  const setSignedIn = useAuthStore((state) => state.setSignedIn);
  const navigate = useNavigate();

  const { mutate } = useMutation({
    mutationFn: signIn,
    onSuccess: (response) => {
      const { data, message } = response.data;
      const { accessToken, refreshToken } = data;
      const status = response.status;

      if (status === 200) {
        setSignedIn();
        localStorage.setItem("accessToken", accessToken);
        localStorage.setItem("refreshToken", refreshToken);
        console.info(message);
        navigate("/");
      } else {
        console.error(message);
      }
    },
    onError: (error) => {
      console.error(error);
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
