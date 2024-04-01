import { Link } from "react-router-dom";

import { News } from "@/types";
import { formatDateWithoutYear } from "@/utils/formatUtils";

interface SimilarNewsListItemProps {
  newsInfo: News;
}

export default function SimilarNewsListItem({
  newsInfo,
}: SimilarNewsListItemProps) {
  const { id, title, imageUrl, createdAt, categoryList } = newsInfo;

  return (
    <Link to={`/news/${id}`}>
      <div className="flex h-28 w-full gap-2 border-b border-slate-200 py-2">
        <div className="flex w-2/3 flex-col justify-between">
          <div>
            <p className="line-clamp-2">{title}</p>
            <p className="text-sm text-lightgray">{formatDateWithoutYear(createdAt)}</p>
          </div>

          <div className="flex gap-1 text-sm text-gray">
            {categoryList.map((category, index) => (
              <p key={index}>#{category}</p>
            ))}
          </div>
        </div>
        <img src={imageUrl} className="h-full w-1/3 object-cover" />
      </div>
    </Link>
  );
}
