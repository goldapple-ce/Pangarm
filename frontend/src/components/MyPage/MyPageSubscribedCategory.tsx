import { useState } from "react";
import { useQuery } from "@tanstack/react-query";

import MyPageCategoryList from "@/components/MyPage/MyPageCategoryList";
import MyPageNewsList from "@/components/MyPage/MyPageNewsList";
import { getSubscribedCategory } from "@/services/authService";
import Error500Animation from "@/components/Error/Error500Animation";
import LoadingAnimation from "../LoadingAnimation";

export default function MyPageSubscribedCategory() {
  const [currentCategory, setCurrentCategory] = useState<string>("");

  const { data, isLoading, error } = useQuery({
    queryKey: ["category"],
    queryFn: getSubscribedCategory,
  });

  const handleCategoryClick = (category: string) => {
    setCurrentCategory(category);
  };

  if (isLoading) {
    return (
      <div>
        <LoadingAnimation />
      </div>
    );
  }

  if (error) {
    console.log(error);
    return (
      <div>
        <Error500Animation />
      </div>
    );
  }

  return (
    <div className="flex h-[60vh] w-full">
      <MyPageCategoryList
        categoryList={data?.data.data}
        currentCategory={currentCategory}
        handleCategoryClick={handleCategoryClick}
      />
      <MyPageNewsList currentCategory={currentCategory} />
    </div>
  );
}
