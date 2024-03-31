interface CategoryButtonProps {
  keyword: string;
  isSelected: boolean;
  onSelect: (keyword: string) => void;
}

export default function CategoryButton({
  keyword,
  isSelected,
  onSelect,
}: CategoryButtonProps) {
  return (
    <button
      className={`flex-shrink-0 rounded-xl border-2 border-navy px-3 py-0.5 font-bold ${isSelected ? "bg-navy text-yellow" : "bg-white text-navy"}`}
      onClick={() => onSelect(keyword)}
    >
      #{keyword}
    </button>
  );
}
