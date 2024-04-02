import ErrorEmptyAnimation from "../Error/ErrorEmptyAnimation";
import Error500Animation from "../Error/Error500Animation";
import MyPageCategory from "./MyPageCategory";
import { postUnsubscribeCategory } from "@/services/authService";
import { useMutation, useQuery } from "@tanstack/react-query";
import { getCategoryList } from "@/services/newsService";

type MyPageCategoryListProps = {
  categoryList: string[];
  handleCategoryClick: (category: string) => void;
  currentCategory: string;
};

export default function MyPageCategoryList({
  categoryList,
  handleCategoryClick,
  currentCategory,
}: MyPageCategoryListProps) {
  const { data } = useQuery({
    queryKey: ["categoryList"],
    queryFn: getCategoryList,
  });

  const { mutate } = useMutation({
    mutationFn: postUnsubscribeCategory,
    onError: (error) => {
      console.log(error);
      alert("구독 해제 중에 오류가 발생했습니다.");
    },
    onSuccess: (data) => {
      console.log(data);
      alert("구독 해제 성공");
    },
  });

  const handleUnsubscribeClick = (category: string) => {
    const index = data?.data.data.indexOf(category);
    mutate(index);
  };

  return (
    <div className="shadow-inner-md m-2 w-3/12 flex-row justify-center overflow-y-scroll rounded-lg border border-lightgray bg-lightblue shadow-black">
      {categoryList ? (
        categoryList.length !== 0 ? (
          categoryList.map((value) => (
            <MyPageCategory
              handleCategoryClick={handleCategoryClick}
              handleUnsubscribeClick={handleUnsubscribeClick}
              category={value}
              currentCategory={currentCategory}
              key={value}
            />
          ))
        ) : (
          <ErrorEmptyAnimation />
        )
      ) : (
        <Error500Animation />
      )}
    </div>
  );
}
