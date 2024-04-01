import { useQuery } from "@tanstack/react-query";

import { SearchHistory } from "@/types";
import { getSearchHistory } from "@/services/authService";

import HistoryListItem from "@/components/Main/HistoryListItem";

interface HistoryListProps {
  onSelectSituation: (situation: string) => void;
}

export default function HistoryList({ onSelectSituation }: HistoryListProps) {
  const { data, isLoading } = useQuery({
    queryKey: [],
    queryFn: getSearchHistory,
    select: (data) => {
      const historyList = data.data.data.precedentSearchList;
      return historyList;
    },
  });

  if (isLoading) {
    return <div>Loading...</div>;
  }

  return (
    <div className="flex h-[450px] w-1/2 flex-col gap-3 overflow-y-scroll rounded-md border border-lightgray">
      {data.length === 0 && (
        <div className="flex h-full w-full items-center justify-center text-xl font-bold text-lightgray">
          검색 기록이 없습니다.
        </div>
      )}
      {data.length > 0 &&
        data.map(({ id, situation }: SearchHistory) => (
          <HistoryListItem
            key={id}
            situation={situation}
            onSelectSituation={onSelectSituation}
          />
        ))}
    </div>
  );
}
