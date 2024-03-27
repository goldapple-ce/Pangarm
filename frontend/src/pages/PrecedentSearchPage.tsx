import { useState, useEffect } from "react";
import { postPrecedentSearch } from "@/services/precedentService";
import { PrecedentItem } from "@/types";
import PrecedentSearchBar from "@/components/Precedent/PrecedentSearchBar";
import PrecedentSearchCondition from "@/components/Precedent/PrecedentSearchCondition";
import PrecedentList from "@/components/Precedent/PrecedentList";
import { SearchProvider } from "@/components/Precedent/SearchContext";
import PrecedentDetail from "@/components/Precedent/PrecedentDetail";

export default function PrecedentSearchPage() {
  const [precedentList, setPrecedentList] = useState<PrecedentItem[]>([]);
  const [detailVisible, setDetailVisible] = useState<boolean>(false);
  const [detailCaseNo, setDetailCaseNo] = useState<string>("");
  const showDetail = (caseNo: string) => {
    setDetailVisible(true);
    setDetailCaseNo(caseNo);
  };

  const closeDetail = () => {
    setDetailVisible(false);
  };

  useEffect(() => {
    const fetchPrecedents = async () => {
      try {
        const res = await postPrecedentSearch("something", 1, 10);
        if (res && res.data && res.data.data) {
          setPrecedentList(res.data.data);
        }
      } catch (error) {
        console.error("Error fetching precedents:", error);
      }
    };

    fetchPrecedents();
  }, []);

  return (
    <SearchProvider>
      <div>
        {detailVisible && (
          <PrecedentDetail
            closeDetail={closeDetail}
            detailCaseNo={detailCaseNo}
          />
        )}
        <div
          className={`mx-3 mt-3 w-full flex-row items-center justify-center gap-6 px-72 ${detailVisible ? " overflow-hidden " : ""}`}
        >
          <div className="mx-5 flex-row">
            <p className="font-SubTitle text-3xl">다시 검색하기</p>
            <PrecedentSearchBar></PrecedentSearchBar>
          </div>
          <div className="mx-5 mt-12 flex">
            <div>
              <PrecedentList
                precedentList={precedentList}
                showDetail={showDetail}
              />
            </div>
            <div className=" border-lightGray/60 mx-7 my-[1%] w-[0.1%] border-[1px]"></div>
            <div>
              <PrecedentSearchCondition></PrecedentSearchCondition>
            </div>
          </div>
        </div>
      </div>
    </SearchProvider>
  );
}
