import LatestNewsList from "@/components/News/LatestNewsList";

export default function LatestNews() {
  return (
      <div className="mb-20">
        {/* Header */}
        <div className="font-TitleBold text-2xl">
          <span className="text-navy">최신</span>
          <span> 뉴스</span>
        </div>

        <hr className="my-3 border" />

        <LatestNewsList />
      </div>
  );
}
