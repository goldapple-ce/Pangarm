import NewsDetailCategoryListItem from "@/components/NewsDetail/NewsDetailCategoryListItem";

export default function NewsDetailCategoryList({
  categoryList,
}: {
  categoryList: string[];
}) {
  return (
    <div className="mb-3 ml-[300px] flex gap-3 text-gray">
      {categoryList.map((category: string, index: number) => (
        <NewsDetailCategoryListItem key={index} category={category} />
      ))}
    </div>
  );
}
