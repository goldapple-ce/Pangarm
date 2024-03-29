import React from "react";
import PrecedentListItem from "@/components/Precedent/PrecedentListItem";
import { PrecedentItemType } from "@/types";
import { SelectPivot } from "@/constants";
import PrecedentListItemOrderSelect from "@/components/Precedent/PrecedentListItemOrderSelect";

interface PrecedentListProps {
  precedentList: PrecedentItemType[];
  showDetail: (caseNo: string) => void;
}

// export default function PrecedentList({ precedentList }: PrecedentListProps) {
export default function PrecedentList({
  precedentList,
  showDetail,
}: PrecedentListProps) {
  return (
    <>
      <div className="mb-3 flex justify-between">
        <div className="text-xl">
          검색 결과 총 <span className="text-yellow">12,300</span> 건
        </div>

        <div>
          <PrecedentListItemOrderSelect
            placeholder={SelectPivot.placeholder}
            value={SelectPivot.value}
            label={SelectPivot.label}
          />
        </div>
      </div>
      <div>
        {precedentList &&
          precedentList.map(({ title, content, isBookmarked, isViewed }) => (
            <React.Fragment key={title}>
              <PrecedentListItem
                key={title}
                title={title}
                content={content}
                isBookmarked={isBookmarked}
                isViewed={isViewed}
                showDetail={showDetail}
              />
              <hr className="opacity-30" />
            </React.Fragment>
          ))}
      </div>
    </>
  );
}
