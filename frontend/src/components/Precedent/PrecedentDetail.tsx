import { useState } from "react";
// import axios from "axios";
import { TfiClose } from "react-icons/tfi";
import PrecedentDetailSummary from "@/components/Precedent/PrecedentDetailSummary";
// import PrecedentDetailRaw from "@/components/Precedent/PrecedentDetailRaw";
import { useQuery } from "@tanstack/react-query";
import { getPrecedentSummary } from "@/services/precedentService";
import PrecedentLoadingAnimation from "../PrecedentLoadingAnimation";
import Error500Animation from "../Error/Error500Animation";

type PrecedentDetailProps = {
  detailNo: string;
  closeDetail: () => void;
};

export default function PrecedentDetail({
  closeDetail,
  detailNo,
}: PrecedentDetailProps) {
  const [animate, setAnimate] = useState<boolean>(false);
  const [tab, setTab] = useState<boolean>(false);

  const { data, isLoading, isError } = useQuery({
    queryKey: ["precedentSummary", detailNo],
    queryFn: () => getPrecedentSummary(detailNo),
  });

  const handleClose = () => {
    setAnimate(false);
    setTimeout(() => {
      closeDetail();
    }, 200);
  };
  console.log(data);
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
        {isLoading ? (
          <div>
            <PrecedentLoadingAnimation />
          </div>
        ) : isError ? (
          <div>
            <Error500Animation />
            무언가 잘못됐어요... 나중에 다시 시도해주세요...!
          </div>
        ) : (
          <>
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
              {/* {tab && data && <PrecedentDetailRaw detail={""} />} */}
              {!tab && <PrecedentDetailSummary />}
            </div>
          </>
        )}
      </div>
    </div>
  );
}

// const exmapleData: PrecedentSummary = {
//   info: {
//     caseNumber: "2016도8419",
//     caseName:
//       "사기·보조금관리에관한법률위반(일부인정된죄명:보조금의예산및관리에관한법률위반)",
//     judgementDate: "2016.11.24",
//     type: {
//       incident: "형사",
//       courtName: "대법원",
//       verdict: "선고",
//     },
//   },
//   relate: {
//     lawList: [
//       {
//         lawName: "보조금 관리에 관한 법률 보조금 관리에 관한 법률 제40조",
//         searchNumber: "004000",
//         searchName: "보조금 관리에 관한 법률",
//         searchType: "JO",
//         searchKey: "prec",
//       },
//       {
//         lawName:
//           "보조금의 예산 및 관리에 관한 법률 구 보조금의 예산 및 관리에 관한 법률 제40조",
//         searchNumber: "004000",
//         searchName: "보조금의 예산 및 관리에 관한 법률",
//         searchType: "JO",
//         searchKey: "prec",
//       },
//       {
//         lawName:
//           "보조금의 예산 및 관리에 관한 법률 구 보조금의 예산 및 관리에 관한 법률(2011. 7. 25. 법률 제10898호 보조금 관리에 관한 법률로 개정되기 전의 것) 제40조",
//         searchNumber: "004000",
//         searchName: "보조금의 예산 및 관리에 관한 법률",
//         searchType: "JO",
//         searchKey: "prec20110725",
//       },
//       {
//         lawName: "보조금의 예산 및 관리에 관한 법률 보조금법 제40조",
//         searchNumber: "004000",
//         searchName: "보조금의 예산 및 관리에 관한 법률",
//         searchType: "JO",
//         searchKey: "prec",
//       },
//       {
//         lawName: "형법 형법 제37조",
//         searchNumber: "003700",
//         searchName: "형법",
//         searchType: "JO",
//         searchKey: "prec",
//       },
//     ],
//     precedentList: [
//       {
//         caseNumber: "99도4101",
//       },
//       {
//         caseNumber: "2013도6886",
//       },
//     ],
//   },
//   parties: {
//     plaintiff: null,
//     defendant: null,
//   },
//   originalJudgement: {
//     caseNumber: "부산지법 2016. 5. 26. 선고 2015노4075 판결",
//   },
//   purport:
//     "구 보조금의 예산 및 관리에 관한 법률(2011. 7. 25. 법률 제10898호 보조금 관리에 관한 법률로 개정되기 전의 것) 제40조는 “허위의 신청이나 기타 부정한 방법으로 보조금의 교부를 받은 자와 간접보조금의 교부를 받은 자 또는 그 사실을 알면서 보조금이나 간접보조금을 교부한 자는 5년 이하의 징역 또는 500만 원 이하의 벌금에 처한다.”라고 규정하고 있다. 여기서 ‘허위의 신청 기타 부정한 방법’이란 정상적인 절차에 의해서는 보조금을 지급받을 수 없음에도 위계 기타 사회통념상 부정이라고 인정되는 행위로서 보조금 교부에 관한 의사결정에 영향을 미칠 수 있는 적극적 및 소극적 행위를 의미하고, ‘부정한 방법으로 보조금의 교부를 받은’ 경우란 보조금의 교부대상이 되지 아니하는 사무 또는 사업에 대하여 보조금을 받거나 사업 등에 교부되어야 할 금액을 초과하여 보조금을 교부받는 것을 의미한다.",
//   fact: "피고인 2는 2010년 10월 말 시공업체들로부터 위 사무동 1층 철거·개축 및 비품 공사, 위 사무동 지붕, 외벽, 바닥의 방수공사에 대한 견적서를 제출받았다. 공소외 1 주식회사는 위 방수공사금액 36,510,000원(부가가치세 제외)을 포함한 공사금액 합계 126,000,000원의 견적서를 제출하였다. ○○○○산업을 운영하는 피고인 1은 고용노동부에서 시행하는 고용환경개선지원금을 활용하면 상대적으로 적은 비용으로 고용환경개선을 위한 시설 등의 공사를 할 수 있다고 제의하면서, 직접적으로 근로자들의 생활 등에 제공되는 시설에 대한 공사가 아니어서 고용환경개선사업에 해당하지 않는다고 생각한 방수공사 부분을 제외한 나머지 공사 및 비품에 대하여 합계금액 64,080,000원의 견적서를 제출하였다.",
//   opinion: {
//     plaintiff: null,
//     defendant: null,
//   },
//   judgement:
//     "피고인들의 위와 같은 행위는, 사전 계획하에 공사금액을 부풀린 허위의 공사계약서를 작성·제출하고 그에 따른 공사대금이 실제로 지급된 것과 같은 외관까지 만들어낸 것으로서, 사회통념상 부정한 행위라고 보이고, 보조금 교부에 관한 위 지청의 의사결정에도 영향을 미칠 수 있었다고 보인다. 또한 설령 피고인들이 당시 방수공사까지 포함시켜 고용환경개선지원금을 신청하였더라도 동일한 금액의 보조금을 수령할 가능성이 있었고 실제로 위 지청에서 사후에 위 5,600만 원을 정당한 지급으로 처리하여 지원금 환수조치를 취하지 않기로 하였다고 하더라도, 위 방수공사는 애초에 피고인들의 고용환경개선지원금 교부신청 및 행정청의 교부결정 대상에 포함되지 않았던 것이므로, 피고인들의 위와 같은 행위는 보조금법 제40조에서 정한 ‘허위의 신청이나 기타 부정한 방법으로 보조금의 교부를 받은 경우’에 해당한다고 보아야 한다.",
//   conclusion:
//     "원심판결을 파기하고, 사건을 다시 심리·판단하도록 원심법원에 환송하기로 하여, 관여 대법관의 일치된 의견으로 주문과 같이 판결한다.",
//   summary:
//     "구 보조금의 예산 및 관리에 관한 법률(2011. 7. 25. 법률 제10898호 보조금 관리에 관한 법률로 개정되기 전의 것) 제40조는 “허위의 신청이나 기타 부정한 방법으로 보조금의 교부를 받은 자와 간접보조금의 교부를 받은 자 또는 그 사실을 알면서 보조금이나 간접보조금을 교부한 자는 5년 이하의 징역 또는 500만 원 이하의 벌금에 처한다.”라고 규정하고 있다. 여기서 ‘허위의 신청 기타 부정한 방법’이란 정상적인 절차에 의해서는 보조금을 지급받을 수 없음에도 위계 기타 사회통념상 부정이라고 인정되는 행위로서 보조금 교부에 관한 의사결정에 영향을 미칠 수 있는 적극적 및 소극적 행위를 의미하고, ‘부정한 방법으로 보조금의 교부를 받은’ 경우란 보조금의 교부대상이 되지 아니하는 사무 또는 사업에 대하여 보조금을 받거나 사업 등에 교부되어야 할 금액을 초과하여 보조금을 교부받는 것을 의미한다.",
// };
