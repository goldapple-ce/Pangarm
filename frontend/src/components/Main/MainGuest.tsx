import { Link } from "react-router-dom";
import { useForm } from "react-hook-form";

import Logo from "@/assets/imgs/Logo.svg?react";

import MainSearchForm from "@/components/Main/MainSearchForm";

type Input = {
  situation: string;
};

export default function MainGuest() {
  const {
    register,
    handleSubmit,
    reset,
    watch,
    formState: { isValid },
  } = useForm<Input>({ mode: "onChange" });

  return (
    <div className="mx-[300px] flex flex-col items-center gap-6">
      <Logo />
      <div className="font-TitleMedium text-3xl text-yellow">판가름</div>

      <div className="flex flex-col items-center font-TitleBold text-xl">
        <div>법에 익숙하지 않은 모두를 위해</div>
        <div>상황에 딱 맞는 판례를 찾아드려요</div>
      </div>

      <MainSearchForm
        register={register}
        handleSubmit={handleSubmit}
        reset={reset}
        watch={watch}
        isValid={isValid}
      />
      <Link
        to="/signup"
        className="text-lightgray transition duration-300 ease-in-out hover:text-gray"
      >
        회원가입하면 더 많은 기능을 이용하실 수 있어요!
      </Link>
    </div>
  );
}
