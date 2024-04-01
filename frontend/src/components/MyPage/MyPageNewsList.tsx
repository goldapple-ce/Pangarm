import { getCategoryNewsList } from "@/services/newsService";
import { useQuery } from "@tanstack/react-query";

type MyPageNewsListProps = {
  currentCategory: string;
};

export default function MyPageNewsList({
  currentCategory,
}: MyPageNewsListProps) {
  const { data, error, isLoading } = useQuery({
    queryKey: [currentCategory],
    queryFn: () => getCategoryNewsList(currentCategory, 0, 10),
  });

  if (isLoading) {
    return <div></div>;
  }

  if (error) {
    return <div></div>;
  }

  const newsList = data?.data.data;
  console.log(newsList);
  return (
    <div className="m-2 w-9/12 bg-lightgray ">
      {/* {newsList.map((value) => {
        <CategoryNewsListItem key={value} />;
      })} */}
    </div>
  );
}
