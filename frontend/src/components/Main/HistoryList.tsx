import HistoryListItem from "@/components/Main/HistoryListItem";

export default function HistoryList() {
  return (
    <div className="flex h-[450px] w-1/2 flex-col gap-3 overflow-y-scroll">
      {Array.from({ length: 10 }, (_, index) => (
        <HistoryListItem key={index} />
      ))}
    </div>
  );
}
