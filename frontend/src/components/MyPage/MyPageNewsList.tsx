import React, { useEffect } from "react";

import { useInView } from "react-intersection-observer";
import { useInfiniteQuery } from "@tanstack/react-query";

import { News } from "@/types";
import { getCategoryNewsList } from "@/services/newsService";

import MyPageNewsListItem from "@/components/MyPage/MyPageNewsListItem";
import LoadingAnimation from "@/components/LoadingAnimation";
import Error500Animation from "@/components/Error/Error500Animation";

type MyPageNewsListProps = {
  currentCategory: string;
};

export default function MyPageNewsList({
  currentCategory,
}: MyPageNewsListProps) {
  const { data, hasNextPage, isLoading, fetchNextPage, error } =
    useInfiniteQuery({
      queryKey: ["getNewsListOfCategory", currentCategory],
      queryFn: ({ pageParam = 0 }) =>
        getCategoryNewsList(currentCategory, pageParam, 12),
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
    return (
      <div className="w-full">
        <LoadingAnimation />
      </div>
    );
  }

  if (error) {
    return (<div className="w-9/12"><Error500Animation /></div>);
  }

  if (currentCategory === "") {
    return (
      <div className="flex h-full w-9/12 items-center justify-center font-TitleMedium text-2xl">
        카테고리를 선택해주세요
      </div>
    );
  }

  if (data?.pages.every((el) => el.data.data.length == 0)) {
    return (
      <div className="flex h-full w-9/12 items-center justify-center font-TitleMedium text-2xl">
        {currentCategory} 카테고리의 기사가 없습니다...
      </div>
    );
  }

  return (
    <div className=" m-2 flex w-9/12 flex-wrap overflow-y-scroll">
      {data?.pages.map((group, i) => (
        <React.Fragment key={i}>
          {group.data.data.map((newsItem: News) => (
            <MyPageNewsListItem
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
