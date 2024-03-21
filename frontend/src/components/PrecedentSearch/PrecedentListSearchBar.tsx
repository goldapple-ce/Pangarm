import React from "react";

// interface PrecedentListSearchBarProps {
//   content: string;
// }

// export default function PrecedentListSearchBar({ content }: PrecedentListSearchBarProps) {
export default function PrecedentListSearchBar() {
  return (
    <input className="mt-4 px-4 text-2xl flex h-16 w-11/12 items-center rounded-md border border-lightgray bg-lightblue text-navy"
    placeholder="검색어를 입력해주세요..."/>
  );
}
