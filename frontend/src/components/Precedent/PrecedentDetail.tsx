import { useEffect, useState } from "react";
// import axios from "axios";
import { TfiClose } from "react-icons/tfi";
import PrecedentDetailSummary from "@/components/Precedent/PrecedentDetailSummary";
// import PrecedentDetailRaw from "@/components/Precedent/PrecedentDetailRaw";
import { useQuery } from "@tanstack/react-query";
import { getPrecedentSummary } from "@/services/precedentService";
import PrecedentLoadingAnimation from "../PrecedentLoadingAnimation";
import Error500Animation from "../Error/Error500Animation";
import PrecedentDetailRaw from "./PrecedentDetailRaw";

type PrecedentDetailProps = {
  caseNo: string;
  similarity?: number;
  keywordList?: string[];
  closeDetail: () => void;
};

export default function PrecedentDetail({
  closeDetail,
  caseNo,
  similarity,
  keywordList,
}: PrecedentDetailProps) {
  const [animate, setAnimate] = useState<boolean>(false);
  const [tab, setTab] = useState<boolean>(false);
  const { data, isLoading, isError } = useQuery({
    queryKey: ["precedentSummary", caseNo],
    queryFn: () => getPrecedentSummary(caseNo),
  });

  const handleClose = () => {
    setAnimate(false);
    setTimeout(() => {
      closeDetail();
    }, 200);
  };

  useEffect(() => {
    setAnimate(true);
  }, []);
  return (
    <div
      className="fixed left-0 top-0 z-10 h-screen w-screen bg-black bg-opacity-20"
      onClick={handleClose}
    >
      <div
        className={`druation-500 fixed right-[1000px] top-14 h-5 w-8 bg-navy transition-transform ease-in-out 
        ${animate ? " translate-x-0" : " translate-x-[1000px]"}`}
      ></div>
      <div
        className={`z-11 druation-500 fixed right-0 top-0 h-[100vh] w-[1000px] overflow-y-hidden bg-white p-9 shadow-md shadow-gray transition-transform ease-in-out
        ${animate ? " translate-x-0" : " translate-x-full"}`}
        onClick={(e) => {
          e.stopPropagation();
        }}
      >
        {isLoading ? (
          <div className="flex h-full flex-row  items-center justify-center">
            <PrecedentLoadingAnimation />
          </div>
        ) : isError ? (
          <div className="flex h-full items-center justify-center">
            <div className="flex-row justify-center">
              <Error500Animation />
              <p className="text-center font-TitleMedium text-2xl">
                무언가 잘못 됐어요... 나중에 다시 시도해주세요...!
              </p>
            </div>
          </div>
        ) : (
          <>
            <div className="flex-row">
              <p className="font-TitleLight">
                {keywordList ? (
                  keywordList.map((keyword: string) => {
                    return (
                      <span className="me-2" key={keyword}>
                        #{keyword}
                      </span>
                    );
                  })
                ) : (
                  <span>키워드는 검색에서 제공됩니다.</span>
                )}
              </p>
              <div className="flex justify-between">
                <p className="truncate font-TitleBold text-4xl">
                  {data?.data.data.info.caseNumber} -{" "}
                  {data?.data.data.info.caseName}
                </p>
                <TfiClose
                  className="h-9 w-9 hover:cursor-pointer"
                  onClick={handleClose}
                />
              </div>
              <p className="font-TitleMedium text-4xl">
                {data?.data.data.info.judgementDate} 선고
              </p>
              {similarity ? (
                <p className="font-TitleLight">
                  검색에 대한 유사도 {similarity}%
                </p>
              ) : (
                <p className="font-TitleLight">유사도는 검색에서 제공됩니다.</p>
              )}
            </div>
            <div className="mt-4 font-TitleLight">
              <div className="flex justify-between px-6 py-2 ">
                <div className="flex">
                  <p
                    className={`mx-6 cursor-pointer transition-all ease-in-out ${!tab && `font-TitleBold text-navy`}`}
                    onClick={() => {
                      setTab(false);
                    }}
                  >
                    요약 보기
                  </p>
                  <p
                    className={`mx-7 cursor-pointer transition-all ease-in-out ${tab && `font-TitleBold text-navy`}`}
                    onClick={() => {
                      setTab(true);
                    }}
                  >
                    상세 보기
                  </p>
                </div>
                <div className="flex">
                  <p className="mx-3">새 창으로 원문 보기</p>
                  <p className="mx-3 opacity-45">PDF로 원문 보기</p>
                </div>
              </div>
              <div
                className={`h-0.5 w-20 border-2 border-navy transition-all  ease-in-out ${tab ? ` translate-x-40` : ` translate-x-10`}`}
              ></div>
              <hr />
            </div>
            <div>
              {tab && data && <PrecedentDetailRaw caseNo={caseNo} />}
              {!tab && (
                <PrecedentDetailSummary precedentSummary={data?.data.data} />
              )}
            </div>
          </>
        )}
      </div>
    </div>
  );
}
