import { Link } from "react-router-dom";
import { SubmitHandler, useForm } from "react-hook-form";

interface FormInput {
  email: string;
  password: string;
}

export default function SignInForm() {
  const { register, handleSubmit } = useForm<FormInput>();

  const onSubmit: SubmitHandler<FormInput> = async (data: unknown) => {
    console.log(data);

    // TODO: 로그인 성공, 실패시에 할 일 추가
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
            required
          />
        </div>
      </div>

      <div className="mt-20">
        <button className="flex w-full items-center justify-center rounded-xl bg-navy p-2 text-2xl text-white">
          로그인
        </button>
        <Link to={"/signup"}>
          <div className="mt-2 w-full text-end text-lightgray">
            아직 회원이 아니신가요?
          </div>
        </Link>
      </div>
    </form>
  );
}
