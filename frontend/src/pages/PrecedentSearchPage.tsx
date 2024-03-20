import { useState, useEffect } from "react";
import { getPrecedent } from "../services/precedent";
import { PrecedentItem } from "../types";
import PrecedentListSearchBar from "../components/PrecedentSearch/PrecedentListSearchBar";
import PrecedentListSearchCondition from "../components/PrecedentSearch/PrecedentListSearchCondition";
import PrecedentList from "../components/PrecedentSearch/PrecedentList";

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
    <div className=" mx-3 mt-3 w-full flex-row items-center justify-center gap-6 px-72 pt-12 ">
      <div className="mx-5 flex-row">
        <p className="font-SubTitle text-3xl">다시 검색하기</p>
        <PrecedentListSearchBar></PrecedentListSearchBar>
      </div>
      <div className=" mx-5 mt-12 flex">
        <div>
          <PrecedentList precedentList={precedentList}></PrecedentList>
        </div>
        <div className=" border-lightGray/60 mx-7 my-[1%] w-[0.1%] border-[1px]"></div>
        <div>
          <PrecedentListSearchCondition></PrecedentListSearchCondition>
        </div>
      </div>
    </div>
  );
}
