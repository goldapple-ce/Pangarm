import CategoryNews from "@/components/News/CategoryNews";
import LatestNews from "@/components/News/LatestNews";

export default function NewsPage() {
  return (
    <div className="mx-[300px]">
      <LatestNews />
      <CategoryNews />
    </div>
  );
}
