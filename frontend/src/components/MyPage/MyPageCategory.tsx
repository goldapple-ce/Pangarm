import { useEffect, useState } from "react";
import { TfiClose } from "react-icons/tfi";
type MyPageCategoryProps = {
  category: string;
  handleCategoryClick: (category: string) => void;
  handleUnsubscribeClick: (category: string) => void;
  currentCategory: string;
};
export default function MyPageCategory({
  category,
  handleCategoryClick,
  handleUnsubscribeClick,
  currentCategory,
}: MyPageCategoryProps) {
  const [isClicked, setIsClicked] = useState<boolean>(false);

  useEffect(() => {
    setIsClicked(category === currentCategory);
  }, [currentCategory, category]);
  return (
    <div
      className={`mx-1 mb-1 mt-2 flex items-center justify-between rounded-md border transition-colors ease-in-out
        ${isClicked ? `border-navy bg-navy p-2 text-white` : `border-lightgray bg-white p-2  hover:bg-lightgray`}`}
      onClick={() => {
        handleCategoryClick(category);
      }}
    >
      <p className="font-Content">{category}</p>
      <TfiClose
        className="h-4 w-4"
        onClick={() => {
          handleUnsubscribeClick(category);
        }}
      />
    </div>
  );
}
