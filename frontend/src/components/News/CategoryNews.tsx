import { useRef, useState } from "react";
import { useQuery } from "@tanstack/react-query";

import { BiSearch } from "react-icons/bi";
import { ArrowDownIcon, ArrowUpIcon } from "@radix-ui/react-icons";

import { getNewsCategoryList } from "@/services/newsService";

import CategoryNewsList from "@/components/News/CategoryNewsList";
import CategoryButton from "@/components/News/CategoryButton";
import LoadingAnimation from "@/components/LoadingAnimation";

export default function CategoryNews() {
  const [selectedCategory, setSelectedCategory] = useState("");
  const [showMore, setShowMore] = useState(false);
  const [keyword, setKeyword] = useState("");

  const searchCategoryInput = useRef<HTMLInputElement>(null);
  const onMove = () => {
    searchCategoryInput.current?.scrollIntoView({
      behavior: "smooth",
      block: "start",
    });
  };

  const { data, isLoading } = useQuery({
    queryKey: ["newsCategoryList"],
    queryFn: getNewsCategoryList,
    select: (data) => {
      const newsCategoryList = data.data.data;
      return newsCategoryList;
    },
  });

  if (isLoading) {
    return <LoadingAnimation />;
  }

  const filteredCategoryList = data.filter((category: string) =>
    category.includes(keyword),
  );

  return (
    <div>
      {/* Header */}
      <div className="font-TitleBold text-2xl" ref={searchCategoryInput}>
        <span className="text-navy">카테고리</span>
        <span> 별 뉴스</span>
      </div>

      <hr className="my-3 border" />

      {/* Category */}
      <div className="relative pr-10">
        <div
          className={`relative flex gap-3 ${showMore ? "overflow-x-hidden" : "flex-wrap"}`}
        >
          {/* Category Search */}
          <div className="relative mr-4 flex-shrink-0">
            <input
              type="text"
              className="w-auto rounded-md border px-2 py-1 pr-8 focus:outline-none"
              placeholder="카테고리 검색"
              value={keyword}
              onChange={(e) => setKeyword(e.target.value)}
              onClick={onMove}
            />
            <BiSearch
              size={20}
              className="absolute right-2.5 top-1/2 -translate-y-1/2 transform"
            />
          </div>

          {/* Category ButtonList */}
          {filteredCategoryList.map((category: string, index: number) => (
            <CategoryButton
              key={index}
              category={category}
              isSelected={category === selectedCategory}
              onSelect={setSelectedCategory}
            />
          ))}
        </div>
        <button
          className="absolute right-0 top-0.5 flex h-7 w-7 items-center justify-center rounded-xl border-2 border-navy"
          onClick={() => setShowMore((prev) => !prev)}
        >
          {showMore ? <ArrowDownIcon /> : <ArrowUpIcon />}
        </button>
      </div>

      <CategoryNewsList category={selectedCategory} />
    </div>
  );
}
