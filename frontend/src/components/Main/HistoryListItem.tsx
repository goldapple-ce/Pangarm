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
      className="box-border h-10 w-full cursor-pointer border-b border-lightgray p-3"
      onClick={() => onSelectSituation(situation)}
    >
      <p className="line-clamp-4">{situation}</p>
    </div>
  );
}
