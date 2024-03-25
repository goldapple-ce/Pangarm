import { useState } from "react";

type ToggleButton = {
  content: string;
};

export default function PrecedentSearchKeywordToggleButton({
  content,
}: ToggleButton) {
  const [isActive, setIsActive] = useState(false);

  const handleClick = () => {
    setIsActive(!isActive);
  };

  return (
    <button
      onClick={handleClick}
      className={`${
        isActive
          ? "boder-white border border-navy bg-navy text-white shadow-inner"
          : "hover border border-navy bg-white text-navy shadow-md hover:bg-navy hover:bg-opacity-20"
      } mx-1 mt-2 rounded-full px-3 py-0.5 text-base font-bold  transition-colors duration-150 focus:outline-none focus:ring-2 focus:ring-navy focus:ring-offset-1`}
    >
      {content}
    </button>
  );
}
