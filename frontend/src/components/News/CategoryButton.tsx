interface CategoryButtonProps {
  category: string;
  isSelected: boolean;
  onSelect: (keyword: string) => void;
}

export default function CategoryButton({
  category,
  isSelected,
  onSelect,
}: CategoryButtonProps) {
  return (
    <button
      className={`translate-color flex-shrink-0 rounded-xl border-2 border-navy px-3 py-0.5 font-bold duration-200 ease-in-out ${isSelected ? "bg-navy text-yellow hover:opacity-90" : "bg-white text-navy hover:bg-lightblue"}`}
      onClick={() => onSelect(category)}
    >
      #{category}
    </button>
  );
}
