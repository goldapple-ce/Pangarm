import SearchNews from "@/components/News/SearchNews";
import LatestNews from "@/components/News/LatestNews";
import CategoryNews from "@/components/News/CategoryNews";

export default function NewsPage() {
  return (
    <div className="mx-[300px]">
      <SearchNews />
      <LatestNews />
      <CategoryNews />
    </div>
  );
}
