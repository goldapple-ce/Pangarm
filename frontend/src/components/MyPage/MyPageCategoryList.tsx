import ErrorEmptyAnimation from "../Error/ErrorEmptyAnimation";
import Error500Animation from "../Error/Error500Animation";
import MyPageCategory from "./MyPageCategory";
import { postUnsubscribeNewsCategory } from "@/services/authService";
import { useMutation } from "@tanstack/react-query";

type MyPageCategoryListProps = {
  categoryList: string[];
  handleCategoryClick: (category: string) => void;
};

export default function MyPageCategoryList({
  categoryList,
  handleCategoryClick,
}: MyPageCategoryListProps) {
  const { mutate } = useMutation({
    mutationFn: postUnsubscribeNewsCategory,
    onError: (error) => {
      console.log(error);
      alert("구독 해제 중에 오류가 발생했습니다.");
    },
    onSuccess: (data) => {
      console.log(data);
      alert("구독 해제 성공");
    },
  });

  const handleUnsubscibeClick = (category: string) => {
    mutate(category);
  };

  return (
    <div className="shadow-inner-md m-2 w-3/12 flex-row justify-center rounded-lg border border-lightgray bg-lightblue shadow-black">
      {categoryList ? (
        categoryList.length === 0 ? (
          categoryList.map((value) => (
            <MyPageCategory
              handleCategoryClick={handleCategoryClick}
              handleUnsubscibeClick={handleUnsubscibeClick}
              category={value}
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
