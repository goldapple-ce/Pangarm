import { useQuery } from "@tanstack/react-query";

import { getCategoryNewsList } from "@/services/newsService";

import CategoryNewsListItem from "@/components/News/CategoryNewsListItem";

interface CategoryNewsListProps {
  category: string;
}

export default function CategoryNewsList({ category }: CategoryNewsListProps) {
  const { data, isLoading } = useQuery({
    queryKey: ["categoryNewsList", category],
    queryFn: () => getCategoryNewsList(category, 0, 10),
  });

  if (isLoading) {
    return <div>Loading...</div>;
  }

  if (data?.data.data.length === 0) {
    return (
      <div className="flex w-full flex-col items-center justify-center py-6 text-3xl text-lightgray">
        {category === "" && <p>카테고리를 선택해주세요!</p>}
        {category !== "" && (
          <>
            <p>'{category}' 카테고리의</p>
            <p>뉴스가 없습니다</p>
          </>
        )}
      </div>
    );
  }

  return (
    <div className="mt-10 flex w-full flex-wrap justify-between">
      {data?.data.data.map(
        ({
          id,
          title,
          content,
          imageUrl,
          createdAt,
        }: {
          id: string;
          title: string;
          content: string;
          imageUrl: string;
          createdAt: string;
        }) => (
          <CategoryNewsListItem
            key={id}
            id={id}
            title={title}
            content={content}
            imageUrl={imageUrl}
            createdAt={createdAt}
          />
        ),
      )}
    </div>
  );
}
