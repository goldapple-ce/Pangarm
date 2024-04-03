import { useRef, useState } from "react";

import { useInfiniteQuery } from "@tanstack/react-query";
import { BiSearch } from "react-icons/bi";
import { FaArrowCircleDown } from "react-icons/fa";

import { News } from "@/types";
import { getNewsListByKeyword } from "@/services/newsService";

import SearchNewsList from "@/components/News/SearchNewsList";
import ErrorEmptyAnimation from "@/components/Error/ErrorEmptyAnimation";

export default function SearchNews() {
  const inputRef = useRef<HTMLInputElement>(null);
  const [keyword, setKeyword] = useState("");
  const { data, hasNextPage, fetchNextPage } = useInfiniteQuery({
    queryKey: ["getNewsListByKeyword", keyword],
    queryFn: ({ pageParam = 0 }) => getNewsListByKeyword(keyword, pageParam, 6),
    initialPageParam: 0,
    getNextPageParam: (_lastPage, allPages) => {
      return allPages.length + 1;
    },
    select: (data) => {
      let newsList: News[] = [];

      data.pages.forEach(({ data }) => {
        newsList = [...newsList, ...data.data];
      });

      return newsList;
    },
    // enabled: false,
  });

  return (
    <div className="mb-20">
      {/* Header */}
      <div className="font-TitleBold text-2xl">
        <span>뉴스</span>
        <span className="text-navy"> 검색</span>
      </div>

      <hr className="my-3 border" />

      {/* Saerch bar */}
      <div className="relative">
        <input
          type="text"
          className="w-full rounded-xl border border-navy px-3 py-1 outline-none"
          ref={inputRef}
          onKeyDown={(e) => {
            if (e.key === "Enter") {
              setKeyword(inputRef.current!.value);
            }
          }}
        />
        <BiSearch
          size={20}
          className="absolute right-2.5 top-1/2 -translate-y-1/2 transform cursor-pointer"
          onClick={() => {
            setKeyword(inputRef.current!.value);
          }}
        />
      </div>

      {data && <SearchNewsList newsList={data} />}

      {(!data || data?.length === 0) && (
        <div className="flex w-full items-center justify-center ">
          <div className="w-72">
            <ErrorEmptyAnimation />
          </div>
        </div>
      )}

      {hasNextPage && (
        <div className="flex w-full justify-center">
          <button
            onClick={() => {
              fetchNextPage();
            }}
          >
            <FaArrowCircleDown color="navy" />
          </button>
        </div>
      )}
    </div>
  );
}
