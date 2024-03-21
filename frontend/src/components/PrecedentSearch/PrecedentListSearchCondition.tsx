import React from "react";
import { GrRefresh } from "react-icons/gr";

import { SearchKeywordExampleList } from "../../constant";
import ToggleButton from "./ToggleButton";
import DateRangePicker from "./DateRangePicker";
import { useSearch } from "./SearchContext";
import ToggleButtonBig from "./ToggleButtonBig";
import SimilaritySlider from "./SimilaritySlider";

export default function PrecedentListSearchCondition() {
  const { filters, setFilters } = useSearch();

  const setDate = (date: string, value: string) => {
    setFilters((t) => {
      t[date] = value;
      return { ...t };
    });
  };

  const setSimilarity = (value: number) => {
    setFilters((t) => {
      t["minSimilarity"] = value;
      return { ...t };
    });
  };

  return (
    <div className="w-[300px]">
      <div className="flex items-center justify-between p-1">
        <p className="font-SubTitle text-xl">필터</p>
        <GrRefresh />
      </div>
      <hr className="my-2" />
      <div className="p-1">
        <p className="font-Content text-lg">키워드</p>
      </div>
      <div>
        {SearchKeywordExampleList.map((value) => (
          <ToggleButton key={value} content={value}></ToggleButton>
        ))}
      </div>
      <hr className="my-4" />
      <div className="px-1">
        <p className="font-Content text-lg">기간</p>
      </div>
      <div className="flex items-center justify-between">
        <DateRangePicker
          setDate={setDate}
          date={filters.startDate}
          label="startDate"
        />{" "}
        ~{" "}
        <DateRangePicker
          setDate={setDate}
          date={filters.endDate}
          label="endDate"
        />
      </div>
      <hr className="my-4" />
      <div>
        <p className="font-Content text-lg">제외하고 보기</p>
      </div>
      <div className="mt-2 flex justify-center">
        <ToggleButtonBig content="이미 본 판례"></ToggleButtonBig>
        <ToggleButtonBig content="북마크 판례"></ToggleButtonBig>
      </div>
      <hr className="my-4" />
      <div className="flex items-center font-Content text-lg">
        최소 유사도 &nbsp;
        <p className="font-yellow">{filters.minSimilarity} </p> %
      </div>
      <div className="mt-1">
        <SimilaritySlider setSimilarity={setSimilarity} />
      </div>
    </div>
  );
}
