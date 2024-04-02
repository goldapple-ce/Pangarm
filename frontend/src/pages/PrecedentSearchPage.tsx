import { useState } from "react";
import PrecedentSearchBar from "@/components/Precedent/PrecedentSearchBar";
import PrecedentSearchCondition from "@/components/Precedent/PrecedentSearchCondition";
import PrecedentList from "@/components/Precedent/PrecedentList";
import { SearchProvider } from "@/components/Precedent/SearchContext";
import PrecedentDetail from "@/components/Precedent/PrecedentDetail";
import { postPrecedentSearch } from "@/services/precedentService";
import { useMutation } from "@tanstack/react-query";
import { PrecedentItemType } from "@/types";

export default function PrecedentSearchPage() {
  const [detailVisible, setDetailVisible] = useState<boolean>(false);
  const [detailCaseNo, setDetailCaseNo] = useState<string>("");

  const [precedentList, setPrecedentList] = useState<PrecedentItemType[]>([]);
  const [precedentItem, setPrecedentItem] = useState<PrecedentItemType>();
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [isError, setIsError] = useState<boolean>(false);
  const [resultCount, setResultCount] = useState<number>(0);
  const [keywordList, setKeywordList] = useState<string[]>([]);
  const showDetail = (precedentData: PrecedentItemType) => {
    setDetailVisible(true);
    setDetailCaseNo(precedentData.caseNumber);
    setPrecedentItem(precedentData);
  };

  const closeDetail = () => {
    setDetailVisible(false);
  };

  const handleLoading = () => {
    setIsLoading(true);
  };

  const { mutate } = useMutation({
    mutationFn: postPrecedentSearch,
    onSuccess: (res) => {
      setIsLoading(false);
      setIsError(false);
      setResultCount(res.data.data.precedentList.totalElements);
      setKeywordList(res.data.data.keywordList);
      console.log(res);
      setPrecedentList(res.data.data.precedentList.content);
    },
    onError: (err) => {
      setIsLoading(false);
      setIsError(true);
      console.log(err);
    },
  });

  return (
    <SearchProvider>
      <div>
        {detailVisible && precedentItem && (
          <PrecedentDetail
            closeDetail={closeDetail}
            caseNo={detailCaseNo}
            precedentData={precedentItem}
          />
        )}
        <div
          className={`mx-3 mt-3 w-full flex-row items-center justify-center gap-6 px-72 ${detailVisible ? " overflow-hidden " : ""}`}
        >
          <div className="mx-5 flex-row">
            <p className="font-SubTitle text-3xl">다시 검색하기</p>
            <PrecedentSearchBar mutate={mutate} handleLoading={handleLoading} />
          </div>
          <div className="mx-5 mt-12 flex">
            <PrecedentList
              showDetail={showDetail}
              precedentList={precedentList}
              isLoading={isLoading}
              isError={isError}
              mutate={mutate}
              resultCount={resultCount}
            />
            <div className=" mx-7 w-[1px] bg-lightgray" />
            <div>
              <PrecedentSearchCondition keywordList={keywordList} />
            </div>
          </div>
        </div>
      </div>
    </SearchProvider>
  );
}
