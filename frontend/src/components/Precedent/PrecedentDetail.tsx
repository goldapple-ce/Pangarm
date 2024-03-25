import { useEffect, useState } from "react";
// import axios from "axios";
import { TfiClose } from "react-icons/tfi";
import PrecedentDetailSummary from "@/components/Precedent/PrecedentDetailSummary";
import PrecedentDetailRaw from "@/components/Precedent/PrecedentDetailRaw";
import { Data } from "@/types";
import instance from "@/utils/http-commons";
type PrecedentDetailProps = {
  detailCaseNo: string;
  closeDetail: () => void;
};

export default function PrecedentDetail({
  closeDetail,
  detailCaseNo,
}: PrecedentDetailProps) {
  const [animate, setAnimate] = useState<boolean>(false);
  const [tab, setTab] = useState<boolean>(false);
  const [data, setData] = useState<Data>();

  useEffect(() => {
    setAnimate(true);
    instance.get(`/precedent?caseNumber=${detailCaseNo}`).then((res) => {
      setData(res.data.data);
      console.log(res.data.data);
    });
  }, [detailCaseNo]);

  const handleClose = () => {
    setAnimate(false);
    setTimeout(() => {
      closeDetail();
    }, 200);
  };

  return (
    <div
      className="fixed left-0 top-0 z-10 h-screen w-screen bg-black bg-opacity-20"
      onClick={handleClose}
    >
      <div
        className={`z-11 druation-500 fixed right-0 top-0 h-[100vh] w-[1000px] overflow-y-hidden bg-white p-9 shadow-md shadow-gray transition-transform ease-in-out
        ${animate ? " translate-x-0" : " translate-x-full"}`}
        onClick={(e) => {
          e.stopPropagation();
        }}
      >
        <div className="flex-row">
          <p className="font-TitleLight">#키워드 #키워드 #키워드</p>
          <div className="flex justify-between">
            <p className="font-TitleBold text-4xl">
              이것은 제목일까 아닐까 그것이 문제로다
              {/* {PrecedentDetailExample.title} */}
            </p>
            <TfiClose className="h-9 w-9" onClick={handleClose} />
          </div>
          <p className="font-TitleMedium text-4xl">
            2077. 09. 21. 선고
            {/* {PrecedentDetailExample.detail.basicInformation.graph.judgementDate} */}
          </p>
          <p className="font-TitleLight">검색에 대한 유사도 77 %</p>
        </div>
        <div className="mt-4 font-TitleLight">
          <div className="flex justify-between px-6 py-2 ">
            <div className="flex">
              <p
                className={`mx-6 transition-all ease-in-out ${!tab && `font-TitleBold text-navy`}`}
                onClick={() => {
                  setTab(false);
                }}
              >
                요약 보기
              </p>
              <p
                className={`mx-7 transition-all ease-in-out ${tab && `font-TitleBold text-navy`}`}
                onClick={() => {
                  setTab(true);
                }}
              >
                상세 보기
              </p>
            </div>
            <div className="flex">
              <p className="mx-3">새 창으로 원문 보기</p>
              <p className="mx-3">PDF로 원문 보기</p>
            </div>
          </div>
          <div
            className={`h-0.5 w-20 border-2 border-navy transition-all  ease-in-out ${tab ? ` translate-x-40` : ` translate-x-10`}`}
          ></div>
          <hr />
        </div>
        <div>
          {tab && data && <PrecedentDetailRaw detail={data.detail} />}
          {!tab && <PrecedentDetailSummary />}
        </div>
      </div>
    </div>
  );
}
