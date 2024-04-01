import { useNavigate } from "react-router-dom";
import {
  SubmitHandler,
  UseFormRegister,
  UseFormHandleSubmit,
  UseFormReset,
  UseFormWatch,
} from "react-hook-form";

import { GrRefresh } from "react-icons/gr";

import { useSituationStore } from "@/stores/situationStore";

type Input = {
  content: string;
};

interface MainSearchFormProps {
  register: UseFormRegister<Input>;
  handleSubmit: UseFormHandleSubmit<Input>;
  reset: UseFormReset<Input>;
  watch: UseFormWatch<Input>;
  isValid: boolean;
}

export default function MainSearchForm({
  register,
  handleSubmit,
  reset,
  watch,
  isValid,
}: MainSearchFormProps) {
  const watchContent = watch("content", "");
  const setContent = useSituationStore((state) => state.setSituation);
  const navigate = useNavigate();

  const onSubmit: SubmitHandler<Input> = (data) => {
    setContent(data.content);
    navigate("/precedent-search");

    // TODO: 상황을 제출했을 때 할 일
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <div className="relative">
        <textarea
          {...register("content", { required: "내용을 입력해주세요." })}
          cols={100}
          rows={12}
          className="w-full resize-none rounded-lg border border-lightgray bg-lightblue p-12"
          placeholder="상황을 250자 이내로 입력해주세요."
          maxLength={250}
        />
        <div
          className="absolute right-3 top-3.5 flex cursor-pointer flex-col items-center text-gray hover:text-black"
          onClick={() => reset()}
        >
          <GrRefresh size={15} />
          <p className="text-[8px]">다시쓰기</p>
        </div>

        <div className="absolute bottom-3.5 right-3 text-lightgray">
          {watchContent.length}/250
        </div>
      </div>
      <input
        type="submit"
        className="w-full cursor-pointer rounded-lg bg-navy py-4 font-SubTitle text-xl font-bold text-white shadow-lg shadow-lightgray disabled:cursor-default disabled:opacity-50"
        value="판례 찾기"
        disabled={!isValid}
      />
    </form>
  );
}
