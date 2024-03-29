import { PrecedentItemType } from "@/types";
import { useEffect, useState } from "react";
import PrecedentItem from "../PrecedentItem";
import { useQuery } from "@tanstack/react-query";
import PrecedentDetail from "../Precedent/PrecedentDetail";
import { AxiosResponse } from "axios";

type MyPagePrecedentProps = {
  getPrecedent: () => Promise<AxiosResponse>;
  queryKey: string;
};

export default function MyPagePrecedent({
  getPrecedent,
  queryKey,
}: MyPagePrecedentProps) {
  const [precedentData, setPrecedentData] = useState<
    PrecedentItemType[] | undefined
  >();
  const [isDetailOpen, setDetailOpen] = useState<boolean>(false);
  const [detailNo, setDetailNo] = useState<string>("");

  const { data, error, isLoading } = useQuery({
    queryKey: [`${queryKey}`],
    queryFn: getPrecedent,
  });

  const showDetail = (c: string) => {
    setDetailOpen(true);
    setDetailNo(c);
  };

  const closeDetail = () => {
    setDetailOpen(false);
  };

  useEffect(() => {
    setPrecedentData(data ? data.data.data : undefined);
    console.log(data?.data.data);
  }, [data]);

  if (isLoading) return <div>Loading...</div>;
  if (error) return <div>Error...!</div>;
  return (
    <div className="flex max-h-[60vh] flex-wrap overflow-y-scroll">
      {isDetailOpen && (
        <PrecedentDetail closeDetail={closeDetail} detailNo={detailNo} />
      )}
      {precedentData &&
        precedentData.map((value) => (
          <PrecedentItem
            title={value.title}
            content={value.content}
            isBookmarked={value.isBookmarked}
            isViewed={value.isViewed}
            showDetail={showDetail}
          />
        ))}
    </div>
  );
}
