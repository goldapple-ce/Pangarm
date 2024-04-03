import { GrRefresh } from "react-icons/gr";

import PrecedentSearchKeywordToggleButton from "@/components/Precedent/PrecedentSearchKeywordToggleButton";
import PrecedentSearchDateRangePicker from "@/components/Precedent/PrecedentSearchDateRangePicker";
import { useSearch } from "@/components/Precedent/SearchContext";
import PrecedentSearchToggleSlider from "@/components/Precedent/PrecedentSearchToggleSlider";
import PrecedentSearchSimilaritySlider from "@/components/Precedent/PrecedentSearchSimilaritySlider";

export default function PrecedentSearchCondition({
  keywordList,
}: {
  keywordList: string[];
}) {
  const { filters, setFilters } = useSearch();

  const initializeFilter = () => {
    setFilters({
      keywordList: [],
      duration: {
        startDate: "",
        endDate: "",
      },
      preclude: {
        isViewed: true,
        isBookmarked: true,
      },
      minimumSimilarity: 50,
    });
  };

  const addKeyword = (keyword: string) => {
    setFilters((t) => {
      return {
        ...t,
        keywordList: [keyword, ...t.keywordList],
      };
    });
  };

  const deleteKeyword = (keyword: string) => {
    setFilters((t) => {
      return {
        ...t,
        keywordList: t.keywordList.filter((e) => e !== keyword),
      };
    });
  };

  const setDate = (date: string, value: string) => {
    setFilters((t) => {
      // console.log(date);
      // console.log(date, value);
      return {
        ...t,
        duration: {
          ...t.duration,
          [date]: value,
        },
      };
    });
  };

  const setSimilarity = (value: number[]) => {
    setFilters((t) => {
      t["minimumSimilarity"] = value[0];
      return { ...t };
    });
  };

  const setToggleFalse = (label: string) => {
    setFilters((t) => {
      // console.log(filters.preclude);
      return {
        ...t,
        preclude: {
          ...t.preclude,
          [label]: false,
        },
      };
    });
  };

  const setToggleTrue = (label: string) => {
    setFilters((t) => {
      // console.log(filters.preclude);
      return {
        ...t,
        preclude: {
          ...t.preclude,
          [label]: true,
        },
      };
    });
  };

  return (
    <div className="sticky top-10 h-[500px] w-[300px]">
      <div className="flex items-center justify-between p-1">
        <p className="font-SubTitle text-xl">필터</p>
        <GrRefresh
          className=" hover:cursor-pointer"
          onClick={initializeFilter}
        />
      </div>
      <hr className="my-2" />

      <div className="">
        <p className="font-Content text-lg">키워드</p>
      </div>
      <div>
        {keywordList ? (
          keywordList.length !== 0 ? (
            keywordList.map((value) => (
              <PrecedentSearchKeywordToggleButton
                key={value}
                isContains={filters.keywordList.includes(value)}
                content={value}
                addKeyword={addKeyword}
                deleteKeyword={deleteKeyword}
              ></PrecedentSearchKeywordToggleButton>
            ))
          ) : (
            <div>키워드가 존재하지 않습니다.</div>
          )
        ) : (
          <div>키워드가 존재하지 않습니다.</div>
        )}
      </div>
      <hr className="mb-2 mt-4" />

      <div className="">
        <p className="font-Content text-lg">선고 기간</p>
      </div>
      <div className="flex items-center justify-between px-1">
        <PrecedentSearchDateRangePicker
          setDate={setDate}
          date={filters.duration.startDate}
          label="startDate"
        />{" "}
        ~{" "}
        <PrecedentSearchDateRangePicker
          setDate={setDate}
          date={filters.duration.endDate}
          label="endDate"
        />
      </div>
      <hr className="my-4" />

      <div className="">
        <p className="font-Content text-lg">제외하고 보기</p>
      </div>
      <div className="mt-2 flex justify-center px-1">
        <PrecedentSearchToggleSlider
          content="이미 본 판례"
          label="isViewed"
          isToggled={filters.preclude.isViewed}
          setToggleTrue={setToggleTrue}
          setToggleFalse={setToggleFalse}
        />
        <PrecedentSearchToggleSlider
          content="북마크 판례"
          label="isBookmarked"
          isToggled={filters.preclude.isBookmarked}
          setToggleTrue={setToggleTrue}
          setToggleFalse={setToggleFalse}
        />
      </div>
      <hr className="my-4" />

      <div className="flex items-center font-Content text-lg">
        최소 유사도 &nbsp;
        <p className="font-yellow">{filters.minimumSimilarity} </p> % 이상
      </div>
      <div className="mt-1 px-1 ">
        <PrecedentSearchSimilaritySlider
          setSimilarity={setSimilarity}
          minimunSimilarity={filters.minimumSimilarity}
        />
      </div>
    </div>
  );
}
