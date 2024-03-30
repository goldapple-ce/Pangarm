import SimilarNewsListItem from "@/components/NewsDetail/SimilarNewsListItem";

export default function SimilarNewsList() {
  return (
    <div className="flex flex-col gap-3">
      {Array.from({ length: 5 }).map((_, idx) => (
        <SimilarNewsListItem key={idx} />
      ))}
    </div>
  );
}
