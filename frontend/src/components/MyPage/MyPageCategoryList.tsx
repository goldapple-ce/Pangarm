import Swal from "sweetalert2";
import { useMutation } from "@tanstack/react-query";

import { postUnsubscribeNewsCategory } from "@/services/authService";

import ErrorEmptyAnimation from "@/components/Error/ErrorEmptyAnimation";
import Error500Animation from "@/components/Error/Error500Animation";
import MyPageCategory from "@/components/MyPage/MyPageCategory";

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
  const { mutate } = useMutation({
    mutationFn: postUnsubscribeNewsCategory,
    onError: (error) => {
      console.log(error);
      Swal.fire({
        text: "구독 해제중에 오류가 발생했습니다.",
        icon: "error",
      });
    },
    onSuccess: (data) => {
      console.log(data);
      Swal.fire({
        text: "구독 해제에 성공했습니다.",
        icon: "success",
      });
    },
  });

  const handleUnsubscribeClick = (category: string) => {
    mutate(category);
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
          <div className="flex h-full w-full items-center justify-center">
            <ErrorEmptyAnimation />
          </div>
        )
      ) : (
        <Error500Animation />
      )}
    </div>
  );
}
