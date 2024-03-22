import { GrRefresh } from "react-icons/gr";

import { SearchKeywordExampleList } from "@/constant";
import PrecedentSearchKeywordToggleButton from "@/components/Precedent/PrecedentSearchKeywordToggleButton";
import PrecedentSearchDateRangePicker from "@/components/Precedent/PrecedentSearchDateRangePicker";
import { useSearch } from "@/components/Precedent/SearchContext";
import PrecedentSearchToggleSlider from "@/components/Precedent/PrecedentSearchToggleSlider";
import PrecedentSearchSimilaritySlider from "@/components/Precedent/PrecedentSearchSimilaritySlider";

// import { FiltersType } from "../../types";

// function isKeyOfFiltersType(key: string, obj: FiltersType): key is keyof FiltersType {
//   return key in obj;
// }

export default function PrecedentSearchCondition() {
  const { filters, setFilters } = useSearch();

  // const handleClick = () => {
  //   setFilters({
  //     keywords: [],
  //     startDate: "",
  //     endDate: "",
  //     isViewed: false,
  //     isBookmarked: false,
  //     minSimilarity: 50,
  //   });
  // };

  const setDate = (date: string, value: string) => {
    setFilters((t) => {
      return {
        ...t,
        [date]: value,
      };
    });
  };

  const setSimilarity = (value: number[]) => {
    setFilters((t) => {
      t["minSimilarity"] = value[0];
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

      <div className="">
        <p className="font-Content text-lg">키워드</p>
      </div>
      <div>
        {SearchKeywordExampleList.map((value) => (
          <PrecedentSearchKeywordToggleButton
            key={value}
            content={value}
          ></PrecedentSearchKeywordToggleButton>
        ))}
      </div>
      <hr className="mb-2 mt-4" />

      <div className="">
        <p className="font-Content text-lg">선고 기간</p>
      </div>
      <div className="flex items-center justify-between px-1">
        <PrecedentSearchDateRangePicker
          setDate={setDate}
          date={filters.startDate}
          label="startDate"
        />{" "}
        ~{" "}
        <PrecedentSearchDateRangePicker
          setDate={setDate}
          date={filters.endDate}
          label="endDate"
        />
      </div>
      <hr className="my-4" />

      <div className="">
        <p className="font-Content text-lg">제외하고 보기</p>
      </div>
      <div className="mt-2 flex justify-center px-1">
        <PrecedentSearchToggleSlider content="이미 본 판례"></PrecedentSearchToggleSlider>
        <PrecedentSearchToggleSlider content="북마크 판례"></PrecedentSearchToggleSlider>
      </div>
      <hr className="my-4" />

      <div className="flex items-center font-Content text-lg">
        최소 유사도 &nbsp;
        <p className="font-yellow">{filters.minSimilarity} </p> % 이상
      </div>
      <div className="mt-1 px-1 ">
        <PrecedentSearchSimilaritySlider setSimilarity={setSimilarity} />
      </div>
    </div>
  );
}
