import { Link } from "react-router-dom";

import { formatDateWithoutYear } from "@/utils/formatUtils";

interface SearchNewsListItemProps {
  id: string;
  title: string;
  content: string;
  imageUrl: string;
  createdAt: string;
}

export default function SearchNewsListItem({
  id,
  title,
  content,
  imageUrl,
  createdAt,
}: SearchNewsListItemProps) {
  return (
    <div className="mx-3 mb-4 h-44 w-[31%] p-2 shadow-md">
      <Link to={`/news/${id}`}>
        <p className="truncate text-xl font-semibold">{title}</p>
        <p className="mb-3 text-xs text-lightgray">{formatDateWithoutYear(createdAt)}</p>
        <div className="flex h-24 flex-grow justify-between gap-2">
          <img src={imageUrl} className="h-full w-1/2 bg-lightgray" alt="" />
          <p className="line-clamp-4 h-full w-1/2">{content}</p>
        </div>
      </Link>
    </div>
  );
}
