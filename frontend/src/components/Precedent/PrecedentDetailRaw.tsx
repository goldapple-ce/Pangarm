import { getPrecedentDetail } from "@/services/precedentService";
import { useQuery } from "@tanstack/react-query";
import PrecedentLoadingAnimation from "../PrecedentLoadingAnimation";
import Error500Animation from "../Error/Error500Animation";

type PrecedentDetailRawProps = {
  caseNo: string;
};

export default function PrecedentDetailRaw({
  caseNo,
}: PrecedentDetailRawProps) {
  const { data, isLoading, isError } = useQuery({
    queryKey: ["precedentDetail", caseNo],
    queryFn: () => getPrecedentDetail(caseNo),
  });

  if (isLoading)
    return (
      <div className="flex h-full flex-col items-center justify-center">
        <PrecedentLoadingAnimation />
      </div>
    );
  if (isError)
    return (
      <div className="flex h-full items-center justify-center">
        <div className="flex-row justify-center">
          <Error500Animation />
          <p className="text-center font-TitleMedium text-2xl">
            무언가 잘못 됐어요... 나중에 다시 시도해주세요...!
          </p>
        </div>
      </div>
    );
  return (
    <div className="h-[70vh] flex-row overflow-y-auto p-8 font-SubTitle">
      <div dangerouslySetInnerHTML={{ __html: data?.data.data.body }}></div>
    </div>
  );
}
