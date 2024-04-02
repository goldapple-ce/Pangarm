import { News } from "@/types";
import SimilarNewsListItem from "@/components/NewsDetail/SimilarNewsListItem";

interface SimilarNewsListProps {
  newsList: News[];
}

export default function SimilarNewsList({ newsList }: SimilarNewsListProps) {
  return (
    <div className="flex flex-col gap-3">
      {newsList.map((news) => (
        <SimilarNewsListItem key={news.id} newsInfo={news} />
      ))}
    </div>
  );
}
