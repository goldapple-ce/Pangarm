import { useNavigate, useParams } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";

import { getNewsDetail } from "@/services/newsService";

import NewsDetail from "@/components/NewsDetail/NewsDetail";
import SimilarNews from "@/components/NewsDetail/SimilarNews";
import NewsDetailCategoryList from "@/components/NewsDetail/NewsDetailCategoryList";
import LoadingAnimation from "@/components/LoadingAnimation";

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

  if (isLoading) {
    return <LoadingAnimation />;
  }

  const { title, content, imageUrl, author, createdAt, categoryList } =
    data?.data.data || {};

  return (
    <>
      <NewsDetailCategoryList categoryList={categoryList} />

      <div className="mx-[300px] flex gap-5">
        <NewsDetail
          title={title}
          createdAt={createdAt}
          imageUrl={imageUrl}
          author={author}
          content={content}
        />

        <div className="w-[1px] bg-slate-100" />

        <SimilarNews categoryList={categoryList} newsId={newsId} />
      </div>
    </>
  );
}
