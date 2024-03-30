export default function SimilarNewsListItem() {
  return (
    <div className="flex h-28 w-full gap-2 border-b border-slate-200 py-2">
      <div className="flex w-2/3 flex-col justify-between">
        <div>
          <p className="line-clamp-2">
            뉴스 제목 뉴스 제목 뉴스 제목 뉴스 제목 뉴스 제목 뉴스 제목 뉴스
            제목 뉴스 제목 뉴스 제목 뉴스 제목
          </p>
          <p className="text-sm text-lightgray">2024. 03. 08 오전 10:31</p>
        </div>

        <p className="text-gray">#사기</p>
      </div>
      <div className="h-full w-1/3 bg-lightgray" />
    </div>
  );
}
