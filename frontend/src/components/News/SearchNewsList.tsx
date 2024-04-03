import { News } from "@/types";

import SearchNewsListItem from "@/components/News/SearchNewsListItem";

interface SearchNewsListProps {
  newsList: News[];
}

export default function SearchNewsList({
  newsList,
}: SearchNewsListProps) {
  return (
    <div className="mt-10 flex w-full flex-wrap justify-between">
      {newsList.map(({ id, title, content, imageUrl, createdAt }: News) => (
        <SearchNewsListItem
          key={id}
          id={id}
          title={title}
          content={content}
          imageUrl={imageUrl}
          createdAt={createdAt}
        />
      ))}
    </div>
  );
}
