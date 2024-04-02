import { useQuery } from "@tanstack/react-query";

import SimilarNewsList from "@/components/NewsDetail/SimilarNewsList";
import { getCategoryNewsList } from "@/services/newsService";

interface SimilarNewsProps {
  categoryList: string[];
  newsId: string | undefined;
}

export default function SimilarNews({
  categoryList,
  newsId,
}: SimilarNewsProps) {
  const { data, isLoading } = useQuery({
    queryKey: ["similarNews", ...categoryList],
    queryFn: () => getCategoryNewsList(categoryList[0], 0, 5),
  });

  if (isLoading) {
    return <div>Loading...</div>;
  }

  const filteredNewsList = data?.data.data.filter(
    ({ id }: { id: string }) => id !== newsId,
  );

  return (
    <div className="sticky top-[120px] h-fit w-4/12">
      <div className="mb-6 font-TitleMedium">
        <p className="text-3xl text-navy">비슷한 카테고리 기사</p>
        <p className="text-xl">도 함께 보세요</p>
      </div>

      <SimilarNewsList newsList={filteredNewsList} />
    </div>
  );
}
