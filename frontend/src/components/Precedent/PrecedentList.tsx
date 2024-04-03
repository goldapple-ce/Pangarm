import React, { useEffect, useRef } from "react";
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
import ErrorEmptyAnimation from "../Error/ErrorEmptyAnimation";
import { useInView } from "react-intersection-observer";

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
  const size = useRef<number>(10);
  const [ref, inView] = useInView();

  useEffect(() => {
    if (situation !== "")
      mutate({
        situation: situation,
        page: 0,
        size: size.current,
        filter: filters,
      });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  useEffect(() => {
    if (inView) {
      size.current = size.current + 10;
      mutate({
        situation: situation,
        page: 0,
        size: size.current,
        filter: filters,
      });
    }
  }, [filters, inView, mutate, situation]);

  return (
    <div className="w-8/12 flex-row">
      {situation === "" ? (
        <div className="flex h-full flex-col items-center justify-center">
          <p className="font-TitleLight text-3xl">검색어를 입력해주세요</p>
        </div>
      ) : isLoading ? (
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
      ) : resultCount === 0 ? (
        <ErrorEmptyAnimation />
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
              precedentList.map((precedentData: PrecedentItemType) => {
                precedentData.similarity = Math.min(
                  precedentData.similarity,
                  100,
                );
                return (
                  <React.Fragment key={precedentData.caseNumber}>
                    <PrecedentListItem precedentData={precedentData} />
                    <hr className="opacity-30" />
                  </React.Fragment>
                );
              })}
          </div>

          <div ref={ref} className="h-2 w-full" />
        </>
      )}
    </div>
  );
}
