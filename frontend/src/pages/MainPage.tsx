import Logo from "@/assets/imgs/Logo.svg?react";
import { Link } from "react-router-dom";
import { SubmitHandler, useForm } from "react-hook-form";
import { GrRefresh } from "react-icons/gr";

type Input = {
  content: string;
};

export default function MainPage() {
  const {
    register,
    handleSubmit,
    reset,
    formState: { isValid },
  } = useForm<Input>({ mode: "onChange" });

  const onSubmit: SubmitHandler<Input> = (data) => {
    console.log(data);

    // TODO: 상황을 제출했을 때 할 일
  };

  return (
    <div className="mx-[300px] flex flex-col items-center gap-6">
      <Logo />
      <div className="flex flex-col items-center font-TitleBold text-xl">
        <div>법에 익숙하지 않은 모두를 위해</div>
        <div>상황에 딱 맞는 판례를 찾아드려요</div>
      </div>

      <form onSubmit={handleSubmit(onSubmit)}>
        <div className=" relative">
          <textarea
            {...register("content", { required: "내용을 입력해주세요." })}
            cols={100}
            rows={12}
            className="w-full resize-none rounded-lg border border-lightgray bg-lightblue p-3 pr-12"
            placeholder="상황을 자세히 입력해주세요."
          />
          <div
            className="absolute right-3 top-3.5 flex cursor-pointer flex-col items-center"
            onClick={() => reset()}
          >
            <GrRefresh size={25} />
            <p className="text-[10px]">다시쓰기</p>
          </div>
        </div>
        <input
          type="submit"
          className="w-full cursor-pointer rounded-lg bg-navy py-4 font-SubTitle text-xl font-bold text-white shadow-lg shadow-lightgray disabled:cursor-default disabled:opacity-50"
          value="판례 찾기"
          disabled={!isValid}
        />
      </form>

      <Link
        to="/signup"
        className="text-lightgray transition duration-300 ease-in-out hover:text-gray"
      >
        회원가입하면 더 많은 기능을 이용하실 수 있어요!
      </Link>
    </div>
  );
}
