import { useState } from "react";
import PrecedentSearchBar from "@/components/Precedent/PrecedentSearchBar";
import PrecedentSearchCondition from "@/components/Precedent/PrecedentSearchCondition";
import PrecedentList from "@/components/Precedent/PrecedentList";
import { SearchProvider } from "@/components/Precedent/SearchContext";
import PrecedentDetail from "@/components/Precedent/PrecedentDetail";

export default function PrecedentSearchPage() {
  const [detailVisible, setDetailVisible] = useState<boolean>(false);
  const [detailCaseNo, setDetailCaseNo] = useState<string>("");
  const showDetail = (caseNo: string) => {
    setDetailVisible(true);
    setDetailCaseNo(caseNo);
  };

  const closeDetail = () => {
    setDetailVisible(false);
  };

  return (
    <SearchProvider>
      <div>
        {detailVisible && (
          <PrecedentDetail closeDetail={closeDetail} detailNo={detailCaseNo} />
        )}
        <div
          className={`mx-3 mt-3 w-full flex-row items-center justify-center gap-6 px-72 ${detailVisible ? " overflow-hidden " : ""}`}
        >
          <div className="mx-5 flex-row">
            <p className="font-SubTitle text-3xl">다시 검색하기</p>
            <PrecedentSearchBar></PrecedentSearchBar>
          </div>
          <div className="mx-5 mt-12 flex">
            <PrecedentList showDetail={showDetail} />
            <div className=" mx-7 w-[1px] bg-lightgray" />
            <div>
              <PrecedentSearchCondition></PrecedentSearchCondition>
            </div>
          </div>
        </div>
      </div>
    </SearchProvider>
  );
}
