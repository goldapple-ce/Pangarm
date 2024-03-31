import { useNavigate, useParams } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";

import { getNewsDetail } from "@/services/newsService";

import NewsDetail from "@/components/NewsDetail/NewsDetail";
import SimilarNews from "@/components/NewsDetail/SimilarNews";

export default function NewsDetailPage() {
  const navigate = useNavigate();
  const { newsId } = useParams();
  const { data, isLoading } = useQuery({
    queryKey: ["newsDetail", newsId],
    queryFn: () => {
      if (newsId === undefined) {
        navigate("/news");
        return;
      }
      return getNewsDetail(newsId);
    },
  });

  if(isLoading) {
    return <div>Loading...</div>
  }

  const {
    title,
    content,
    imageUrl,
    author,
    createdAt,
    categoryList,
  } = data?.data.data || {};

  return (
    <>
      {/* 카테고리 */}
      <div className="ml-[300px] flex gap-3 text-lg text-gray">
        {categoryList.map((category: string, index: number) => (
          <div key={index}>#{category}</div>
        ))}
      </div>

      <div className="mx-[300px] flex gap-5">
        <NewsDetail title={title} createdAt={createdAt} imageUrl={imageUrl} author={author} content={content} />

        <div className="w-[1px] bg-slate-100" />

        <SimilarNews categoryList={categoryList} newsId={newsId} />
      </div>
    </>
  );
}
