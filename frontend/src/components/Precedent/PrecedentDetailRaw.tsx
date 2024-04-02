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
      <div>
        <PrecedentLoadingAnimation />
      </div>
    );
  if (isError)
    return (
      <div>
        <Error500Animation />
      </div>
    );
  return (
    <div className="h-[70vh] flex-row overflow-y-auto p-8 font-SubTitle">
      <div dangerouslySetInnerHTML={{ __html: data?.data.data.body }}></div>
    </div>
  );
}
