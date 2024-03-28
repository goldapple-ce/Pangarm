import CategoryNewsListItem from "@/components/News/CategoryNewsListItem";

export default function CategoryNewsList() {
  return (
    <div className="flex w-full mt-10 flex-wrap justify-between">
      {Array.from({ length: 12 }).map((_, index) => <CategoryNewsListItem key={index}/>)}
    </div>
  );
}
