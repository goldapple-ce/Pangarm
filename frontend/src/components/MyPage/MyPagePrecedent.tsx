import PrecedentItem from "../PrecedentItem";
import { useQuery } from "@tanstack/react-query";
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
  const { data, error, isLoading } = useQuery({
    queryKey: [queryKey],
    queryFn: () => getPrecedent(),
  });

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
