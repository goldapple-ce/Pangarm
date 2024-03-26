import { useState, useEffect } from "react";
import { getPrecedent } from "../services/precedent";
import { PrecedentItem } from "../types";
import PrecedentSearchBar from "../components/Precedent/PrecedentSearchBar";
import PrecedentSearchCondition from "../components/Precedent/PrecedentSearchCondition";
import PrecedentList from "../components/Precedent/PrecedentList";
import { SearchProvider } from "../components/Precedent/SearchContext";
import PrecedentDetail from "@/components/Precedent/PrecedentDetail";
import { useLocation } from "react-router-dom";

export default function PrecedentSearchPage() {
  const [precedentList, setPrecedentList] = useState<PrecedentItem[]>([]);
  const [detailVisible, setDetailVisible] = useState<boolean>(false);
  const [detailCaseNo, setDetailCaseNo] = useState<string>("");
  const location = useLocation();

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
        console.log(location.state.content);
        const res = await getPrecedent(location.state.content, 1, 10);
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
      <div>
        {detailVisible && (
          <PrecedentDetail
            closeDetail={closeDetail}
            detailCaseNo={detailCaseNo}
          />
        )}
        <div
          className={`mx-[300px] mt-3 w-full flex-row items-center justify-center gap-6 ${detailVisible ? " overflow-hidden " : ""}`}
        >
          <div className="mx-5 flex-row">
            <p className="font-SubTitle text-3xl">다시 검색하기</p>
            <PrecedentSearchBar content={location.state.content} />
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
              <PrecedentSearchCondition />
            </div>
          </div>
        </div>
      </div>
    </SearchProvider>
  );
}
