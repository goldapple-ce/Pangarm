import React, { useEffect } from "react";
import { AxiosResponse } from "axios";

import PrecedentListItem from "@/components/Precedent/PrecedentListItem";
import { SelectPivot } from "@/constants";
import PrecedentListItemOrderSelect from "@/components/Precedent/PrecedentListItemOrderSelect";
import { postPrecedentSearchType } from "@/services/precedentService";
import { UseMutateFunction } from "@tanstack/react-query";
import { useSearch } from "@/components/Precedent/SearchContext";
import PrecedentLoadingAnimation from "@/components/PrecedentLoadingAnimation";
import Error500Animation from "@/components/Error/Error500Animation";
import { PrecedentItemType } from "@/types";
import { useSituationStore } from "@/stores/situationStore";

interface PrecedentListProps {
  precedentList: PrecedentItemType[];
  isLoading: boolean;
  isError: boolean;
  mutate: UseMutateFunction<
    AxiosResponse,
    Error,
    postPrecedentSearchType,
    unknown
  >;
  resultCount: number;
}

// export default function PrecedentList({ precedentList }: PrecedentListProps) {
export default function PrecedentList({
  precedentList,
  isLoading,
  isError,
  mutate,
  resultCount,
}: PrecedentListProps) {
  const { filters } = useSearch();
  const situation = useSituationStore((state) => state.situation);

  useEffect(() => {
    mutate({ situation: situation, page: 0, size: 100, filter: filters });
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
              검색 결과 총 <span className="text-yellow">{resultCount}</span> 건
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
              precedentList.map((precedentData: PrecedentItemType) => (
                <React.Fragment key={precedentData.caseNumber}>
                  <PrecedentListItem precedentData={precedentData} />
                  <hr className="opacity-30" />
                </React.Fragment>
              ))}
          </div>
        </>
      )}
    </div>
  );
}
