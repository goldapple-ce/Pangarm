interface HistoryListItemProps {
  situation: string;
  onSelectSituation: (situation: string) => void;
}

export default function HistoryListItem({
  situation,
  onSelectSituation,
}: HistoryListItemProps) {
  return (
    <div
      className="bg-lightnavy mt-1 w-full cursor-pointer"
      onClick={() => onSelectSituation(situation)}
    >
      <p className="mx-3 mt-1 line-clamp-2 h-16 rounded-md border border-lightgray p-2 font-SubTitle shadow-md transition-colors hover:bg-lightgray ">
        <span className="line-clamp-2">{situation}</span>
      </p>
    </div>
  );
}
