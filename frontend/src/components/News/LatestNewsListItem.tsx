import { Link } from "react-router-dom";

import { formatDate } from "@/utils/formatUtils";

interface LatestNewsListItemProps {
  id: string;
  title: string;
  content: string;
  imageUrl: string;
  createdAt: string;
}

export default function LatestNewsListItem({
  id,
  title,
  content,
  imageUrl,
  createdAt,
}: LatestNewsListItemProps) {
  return (
    <div className="mx-3 mb-4 h-44 w-[95%] p-2 shadow-md">
      <Link to={`/news/${id}`}>
        <p className="mb-1 truncate text-xl font-semibold">{title}</p>
        <p className="mb-3 text-xs text-lightgray">{formatDate(createdAt)}</p>
        <div className="flex h-24 flex-grow justify-between gap-2">
          <img src={imageUrl} className="w-1/2 object-cover" />
          <p className="line-clamp-4 h-full w-1/2">{content}</p>
        </div>
      </Link>
    </div>
  );
}
