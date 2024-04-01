import { useState } from "react";
import { useForm } from "react-hook-form";

import Logo from "@/assets/imgs/Logo.svg?react";

import MainSearchForm from "@/components/Main/MainSearchForm";
import HistoryList from "@/components/Main/HistoryList";

type Input = {
  situation: string;
};

export default function MainMember() {
  const {
    register,
    handleSubmit,
    reset,
    setValue,
    watch,
    formState: { isValid },
  } = useForm<Input>({ mode: "onChange" });

  const [selectedSituation, setSelectedSituation] = useState("");

  const handleSelectSituation = (situation: string) => {
    setSelectedSituation(situation);
    setValue("situation", situation);
  };

  return (
    <div className="mx-[300px] flex flex-col items-center gap-6">
      <Logo />
      <div className="font-TitleMedium text-3xl text-yellow">판가름</div>

      <div className="flex gap-2">
        <div className="w-1/2">
          <p className="mb-2 text-center font-TitleMedium text-3xl text-gray">
            검색 기록
          </p>
          <HistoryList onSelectSituation={handleSelectSituation} />
        </div>
        <div className="w-1/2">
          <p className="mb-2 text-center font-TitleMedium text-3xl text-gray">
            판례 찾기
          </p>
          <MainSearchForm
            register={register}
            handleSubmit={handleSubmit}
            reset={reset}
            watch={watch}
            setValue={() => setValue}
            isValid={isValid}
            selectedSituation={selectedSituation}
          />
        </div>
      </div>
    </div>
  );
}
