import { getCategoryNewsList } from "@/services/newsService";
import { useQuery } from "@tanstack/react-query";
import MyPageNewsListItem from "./MyPageNewsListItem";

type MyPageNewsListProps = {
  currentCategory: string;
};

interface CategoryNewsListItemProps {
  id: string;
  title: string;
  content: string;
  imageUrl: string;
  createdAt: string;
}

export default function MyPageNewsList({
  currentCategory,
}: MyPageNewsListProps) {
  const { data, error, isLoading } = useQuery({
    queryKey: ["categoryNewsList", currentCategory],
    queryFn: () => getCategoryNewsList(currentCategory, 0, 10),
  });

  if (isLoading) {
    return <div></div>;
  }

  if (error) {
    return <div></div>;
  }
  if (currentCategory === "") {
    return (
      <div className="flex h-full w-9/12 items-center justify-center font-TitleMedium text-2xl">
        카테고리를 선택해주세요
      </div>
    );
  }

  if (data?.data.data.length === 0) {
    return (
      <div className="flex h-full w-9/12 items-center justify-center font-TitleMedium text-2xl">
        {currentCategory} 카테고리의 기사가 없습니다...
      </div>
    );
  }

  return (
    <div className=" m-2 flex w-9/12 flex-wrap overflow-y-scroll">
      {data?.data.data.map(
        ({
          id,
          title,
          content,
          imageUrl,
          createdAt,
        }: CategoryNewsListItemProps) => (
          <MyPageNewsListItem
            key={id}
            id={id}
            title={title}
            content={content}
            imageUrl={imageUrl}
            createdAt={createdAt}
          />
        ),
      )}
    </div>
  );
}
