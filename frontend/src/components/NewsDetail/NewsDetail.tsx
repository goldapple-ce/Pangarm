import { formatDateWithYear } from "@/utils/formatUtils";

interface NewsDetailProps {
  title: string;
  createdAt: string;
  imageUrl: string;
  author: string;
  content: string;
}

export default function NewsDetail({
  title,
  createdAt,
  imageUrl,
  author,
  content,
}: NewsDetailProps) {
  return (
    <div className="flex w-8/12 flex-col">
      {/* 뉴스 제목 */}
      <p className="font-TitleMedium text-3xl">
        {title}
      </p>

      <div className="flex w-full justify-between">
        {/* 날짜 */}
        <p className="text-lightgray">{formatDateWithYear(createdAt)}</p>
        {/* 기자 이름 */}
        <p className="text-gray">{author}</p>
      </div>

      <hr className="text my-2" />

      <img src={imageUrl} className="my-6 w-full" />

      <p className=" whitespace-pre-line">
        {content}
      </p>
    </div>
  );
}
