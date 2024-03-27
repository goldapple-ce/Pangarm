import React from "react";

type PrecedentDetailRawTextboxProps = {
  children: React.ReactNode;
};

export default function PrecedentDetailRawTextbox({
  children,
}: PrecedentDetailRawTextboxProps) {
  return (
    <div className=" my-3 flex mx-2 h-full min-h-24 items-center rounded border border-lightgray bg-lightblue p-3">
      {children}
    </div>
  );
}
