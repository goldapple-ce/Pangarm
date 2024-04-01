import ErrorEmptyAnimation from "../Error/ErrorEmptyAnimation";
import Error500Animation from "../Error/Error500Animation";
import MyPageCategory from "./MyPageCategory";

type MyPageCategoryListProps = {
  categoryList: string[];
  handleCategoryClick: (category: string) => void;
};

export default function MyPageCategoryList({
  categoryList,
  handleCategoryClick,
}: MyPageCategoryListProps) {
  return (
    <div className="shadow-inner-md m-2 w-3/12 flex-row justify-center rounded-lg border border-lightgray bg-lightblue shadow-black">
      {categoryList ? (
        categoryList.length === 0 ? (
          categoryList.map((value) => (
            <MyPageCategory
              handleCategoryClick={handleCategoryClick}
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
