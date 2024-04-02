import { useState } from "react";
import PrecedentItem from "../PrecedentItem";
import { useQuery } from "@tanstack/react-query";
import PrecedentDetail from "../Precedent/PrecedentDetail";
import { AxiosResponse } from "axios";
import ErrorEmptyAnimation from "../Error/ErrorEmptyAnimation";
import Error500Animation from "../Error/Error500Animation";
import LoadingAnimation from "../LoadingAnimation";
import { PrecedentItemType } from "@/types";

type MyPagePrecedentProps = {
  getPrecedent: () => Promise<AxiosResponse>;
  queryKey: string;
};

export default function MyPagePrecedent({
  getPrecedent,
  queryKey,
}: MyPagePrecedentProps) {
  const [isDetailOpen, setDetailOpen] = useState<boolean>(false);
  const [detailNo, setDetailNo] = useState<string>("");

  const { data, error, isLoading } = useQuery({
    queryKey: [queryKey],
    queryFn: () => getPrecedent(),
  });

  const showDetail = (c: string) => {
    setDetailOpen(true);
    setDetailNo(c);
  };

  const closeDetail = () => {
    setDetailOpen(false);
  };

  if (isLoading)
    return (
      <div>
        <LoadingAnimation />
      </div>
    );
  if (error)
    return (
      <div>
        <Error500Animation />
      </div>
    );
  console.log(`####### ${queryKey}`);
  console.log(data);
  const precedentData = data?.data.data.precedentList;
  console.log(precedentData);
  return (
    <div className="flex max-h-[60vh] flex-wrap overflow-y-scroll">
      {isDetailOpen && (
        <PrecedentDetail closeDetail={closeDetail} detailNo={detailNo} />
      )}
      {precedentData ? (
        precedentData.length !== 0 ? (
          precedentData.map(
            ({
              id,
              caseNumber,
              caseName,
              summary,
              keywordList,
              createAt,
              viewed,
              bookmarked,
            }: PrecedentItemType) => (
              <PrecedentItem
                id={id}
                caseNumber={caseNumber}
                caseName={caseName}
                summary={summary}
                keywordList={keywordList}
                createAt={createAt}
                viewed={viewed}
                bookmarked={bookmarked}
                showDetail={showDetail}
              />
            ),
          )
        ) : (
          <ErrorEmptyAnimation />
        )
      ) : (
        <Error500Animation />
      )}
    </div>
  );
}
