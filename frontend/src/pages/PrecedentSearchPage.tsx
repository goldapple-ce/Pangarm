import { useState, useEffect } from "react";
import { getPrecedent } from "../services/precedent";
import { PrecedentItem } from "../types";
import PrecedentSearchBar from "../components/Precedent/PrecedentSearchBar";
import PrecedentSearchCondition from "../components/Precedent/PrecedentSearchCondition";
import PrecedentList from "../components/Precedent/PrecedentList";
import { SearchProvider } from "../components/Precedent/SearchContext";

export default function PrecedentSearchPage() {
  const [precedentList, setPrecedentList] = useState<PrecedentItem[]>([]);

  useEffect(() => {
    const fetchPrecedents = async () => {
      try {
        // 비동기 함수 내에서 await 사용
        const res = await getPrecedent("something", 1, 10);
        if (res && res.data) {
          setPrecedentList(res.data);
        }
      } catch (error) {
        console.error("Error fetching precedents:", error);
      }
    };

    fetchPrecedents();
  }, []);

  return (
    <SearchProvider>
      <div className="mx-3 mt-3 w-full flex-row items-center justify-center gap-6 px-72 pt-12 ">
        <div className="mx-5 flex-row">
          <p className="font-SubTitle text-3xl">다시 검색하기</p>
          <PrecedentSearchBar></PrecedentSearchBar>
        </div>
        <div className="mx-5 mt-12 flex">
          <div>
            <PrecedentList precedentList={precedentList}></PrecedentList>
          </div>
          <div className=" border-lightGray/60 mx-7 my-[1%] w-[0.1%] border-[1px]"></div>
          <div>
            <PrecedentSearchCondition></PrecedentSearchCondition>
          </div>
        </div>
      </div>
    </SearchProvider>
  );
}
