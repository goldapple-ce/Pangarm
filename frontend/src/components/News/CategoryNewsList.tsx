import React, { useEffect } from "react";

import { useInView } from "react-intersection-observer";
import { useInfiniteQuery } from "@tanstack/react-query";

import { News } from "@/types";
import { getCategoryNewsList } from "@/services/newsService";

import CategoryNewsListItem from "@/components/News/CategoryNewsListItem";

interface CategoryNewsListProps {
  category: string;
}

export default function CategoryNewsList({ category }: CategoryNewsListProps) {
  const { data, hasNextPage, isLoading, fetchNextPage } = useInfiniteQuery({
    queryKey: ["getNewsListOfCategory", category],
    queryFn: ({ pageParam = 0 }) => getCategoryNewsList(category, pageParam, 3),
    initialPageParam: 0,
    getNextPageParam: (_lastPage, allPages) => {
      return allPages.length + 1;
    },
  });

  const [ref, inView] = useInView();

  useEffect(() => {
    if (inView && hasNextPage) {
      fetchNextPage();
    }
  }, [inView, fetchNextPage, hasNextPage]);

  if (isLoading) {
    return <div>Loading...</div>;
  }

  if (data?.pages.every((el) => el.data.data.length == 0)) {
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
      {data?.pages.map((group, i) => (
        <React.Fragment key={i}>
          {group.data.data.map((newsItem: News) => (
            <CategoryNewsListItem
              key={newsItem.id}
              id={newsItem.id}
              title={newsItem.title}
              content={newsItem.content}
              imageUrl={newsItem.imageUrl}
              createdAt={newsItem.createdAt}
            />
          ))}
        </React.Fragment>
      ))}
      <div ref={ref} className="h-2 w-full" />
    </div>
  );
}
