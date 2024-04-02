import React, { useEffect, useState } from "react";
import PrecedentListItem from "@/components/Precedent/PrecedentListItem";
import { PrecedentItemType } from "@/types";
import { SelectPivot } from "@/constants";
import PrecedentListItemOrderSelect from "@/components/Precedent/PrecedentListItemOrderSelect";
import { postPrecedentSearch } from "@/services/precedentService";
import { useMutation } from "@tanstack/react-query";
import { useSearch } from "@/components/Precedent/SearchContext";
import PrecedentLoadingAnimation from "../PrecedentLoadingAnimation";
import Error500Animation from "../Error/Error500Animation";

interface PrecedentListProps {
  showDetail: (caseNo: string) => void;
}

// export default function PrecedentList({ precedentList }: PrecedentListProps) {
export default function PrecedentList({ showDetail }: PrecedentListProps) {
  const [precedentList, setPrecedentList] = useState<PrecedentItemType[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [isError, setIsError] = useState<boolean>(false);

  const { filters } = useSearch();
  const { mutate } = useMutation({
    mutationFn: postPrecedentSearch,
    onSuccess: (res) => {
      setIsLoading(false);
      setIsError(false);
      console.log(res);
      setPrecedentList(res.data.data.precedentList.content);
    },
    onError: (err) => {
      setIsLoading(false);
      setIsError(true);
      console.log(err);
    },
  });

  //TODO content, filter 종속
  useEffect(() => {
    mutate({ situation: "나는 대머리다.", page: 0, size: 10, filter: filters });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div className="w-8/12 flex-row">
      {isLoading ? (
        <div>
          <PrecedentLoadingAnimation />
        </div>
      ) : isError ? (
        <div className="flex-row justify-center">
          <Error500Animation />
          <p className="text-center font-TitleLight text-xl">
            무언가 잘못됐어요... 나중에 다시 시도해주세요
          </p>
        </div>
      ) : (
        <>
          <div className="mb-3 flex justify-between">
            <div className="text-xl">
              검색 결과 총{" "}
              <span className="text-yellow">{precedentList.length}</span> 건
            </div>

            <div>
              <PrecedentListItemOrderSelect
                placeholder={SelectPivot.placeholder}
                value={SelectPivot.value}
                label={SelectPivot.label}
              />
            </div>
          </div>
          <div>
            {precedentList &&
              precedentList.map(
                ({
                  id,
                  caseNumber,
                  caseName,
                  summary,
                  similarity,
                  keywordList,
                  createAt,
                  viewed,
                  bookmarked,
                }) => (
                  <React.Fragment key={id}>
                    <PrecedentListItem
                      caseNumber={caseNumber}
                      caseName={caseName}
                      summary={summary}
                      similarity={similarity}
                      keywordList={keywordList}
                      createAt={createAt}
                      viewed={viewed}
                      bookmarked={bookmarked}
                      showDetail={showDetail}
                    />
                    <hr className="opacity-30" />
                  </React.Fragment>
                ),
              )}
          </div>
        </>
      )}
    </div>
  );
}
