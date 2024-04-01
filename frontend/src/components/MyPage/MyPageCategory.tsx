import { useMutation } from "@tanstack/react-query";
import { TfiClose } from "react-icons/tfi";
type MyPageCategoryProps = {
  category: string;
  handleCategoryClick: (category: string) => void;
};
export default function MyPageCategory({
  category,
  handleCategoryClick,
}: MyPageCategoryProps) {
  const { mutate } = useMutation({});

  return (
    <div
      className="mx-3 mb-1 mt-2 flex"
      onClick={() => {
        handleCategoryClick(category);
      }}
    >
      <p className="font-Content">{category}</p>
      <TfiClose className="h-5 w-5" />
    </div>
  );
}
