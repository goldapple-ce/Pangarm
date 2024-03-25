import { http, HttpResponse } from "msw";
import ERR_MSG from "./ErrorMessage";

type PrecedentSearchRequestBody = {
  content: string;
};

type PrecedentDetailRequestBody = {
  content: string;
};

export const precedentHandlers = [
  // 판례 검색
  http.post<never, PrecedentSearchRequestBody>(
    "/precedent",
    async ({ request }) => {
      // Header에서 AuthToken 가져오기
      const authToken = request.headers.get("Authorization");

      // URL 에서 PARAMS 가져오기
      const url = new URL(request.url);
      const pageNumber = url.searchParams.get("page");
      const size = url.searchParams.get("size");

      // 콘솔로 다 뽑아 먹어보기
      console.log(authToken, url, pageNumber, size);

      //Body에서 정보 가져오기
      const data = await request.json();
      const searchContent = data.content;

      if (searchContent.length === 0) {
        return HttpResponse.json({
          Header: {
            status: 409,
          },
          Body: {
            code: "G002",
            message: ERR_MSG.G002,
          },
        });
      }

      return HttpResponse.json({
        data: [
          {
            title:
              "법인지방소득세경정거부처분취소  [대법원 2023. 12. 7. 선고 2020두42668 판결]",
            content:
              "甲 주식회사가 2014. 1. 1. 지방세법 개정 전에 지출한 구 조세특례제한법에 따른 법인세 세액공제 또는 이월공제 대상인 연구·인력개발비 등을 2014년 이후 사업연도에 이월공제를 적용하여 법인세를 신고·납부하였으나, 같은 기간의 법인지방소득세를 신고·납부할 때에는 이월공제를 적용하지 않았는데, 甲 회사가 경과규정인 2014. 1. 1. 개정 지방세법 부칙 제15조를 근거로 법인세 이월공제의 효과가 같은 기간 법인지방소득세에도 미친다고 주장하며, 당초 신고·납부한 법인지방소득세액의 일부 환급을 구하는 취지의 경정청구를 하였으나, 관할 시장 등이 이를 거부한 사안에서, ‘장래의 한정된 기간 동안 조세감면 등을 명시적으로 규정한 종전의 규정’이 존재한다고 볼 수 없어 법인지방소득세에 경과규정을 근거로 구 지방세법의 과세표준 및 세율을 적용할 수 없다고 한 사례",
            isViewed: true,
            isBookmarked: false,
          },
          {
            title:
              "소유권이전등기  [대법원 2023. 12. 7. 선고 2023다269139 판결]",
            content:
              "甲이 乙로부터 아파트를 매수하기로 하는 계약을 체결하였고, 위 계약 체결 무렵 위 아파트에 거주 중인 임차인 丙이 임대차계약기간 만료 후 계약갱신요구권을 행사하지 않고 아파트를 인도할 것이라고 하였는데, 잔금 지급일 직전 丙이 계약갱신요구권을 행사해 위 아파트에 2년 더 거주하겠다고 통보하자, 실거주할 목적으로 계약을 체결한 甲이 乙에게 잔금 지급을 하지 않았고, 乙이 이를 이유로 계약 해제를 주장한 사안에서, 매매계약 체결 당시 갱신요구권을 행사하지 않겠다고 한 丙이 잔금 지급일 직전 갱신요구권을 행사하였고, 이에 따라 乙의 현실인도의무 이행이 곤란할 현저한 사정변경이 생겼다고 볼 수 있어, 당초 계약 내용에 따라 甲이 선이행의무를 이행하게 하는 것이 공평과 신의칙에 반하게 되었다고 볼 여지가 있다고 한 사례",
            isViewed: false,
            isBookmarked: true,
          },
          {
            title:
              "저작권법위반·부정경쟁방지및영업비밀보호에관한법률위반[외국에서 수입·판매한 만화 캐릭터 미니블록 제품에 대한 배포권 침해 여부가 문제된 사건]  [대법원 2023. 12. 7. 선고 2020도17863 판결]",
            content:
              "저작재산권자의 허락을 받아 저작물의 원본이나 그 복제물이 판매 등의 방법으로 거래에 제공된 경우, 저작자의 배포권이 소진되는지 여부(적극) / 저작물의 원본이나 그 복제물이 외국에서 판매 등의 방법으로 거래에 제공되지 않고 곧바로 국내로 수입되어 그 소유권이나 처분권이 이전된 경우, 저작권법 제20조 단서에 따라 저작자의 배포권 소진 여부를 판단하여야 하는지 여부(적극) / 외국에서 저작재산권자의 허락을 받아 판매 등의 방법으로 거래에 제공되었던 저작물의 원본이나 그 복제물을 국내로 다시 수입하여 배포하는 경우에도 마찬가지인지 여부(원칙적 적극)",
            isViewed: true,
            isBookmarked: true,
          },
          {
            title:
              "보조금환수및재정지원제외처분취소  [대법원 2023. 11. 30. 선고 2019두38465 판결]",
            content:
              "시외버스(공항버스) 운송사업을 하는 甲 주식회사가 청소년요금 할인에 따른 결손 보조금의 지원 대상이 아님에도 청소년 할인 보조금을 지급받음으로써 ‘부정한 방법으로 보조금을 지급받은 경우’에 해당한다는 이유로, 관할 시장이 보조금을 환수하고 구 경기도 여객자동차 운수사업 관리 조례 제18조 제4항을 근거로 보조금 지원 대상 제외처분을 하였다가 처분에 대한 취소소송에서 구 지방재정법 제32조의8 제7항을 처분사유로 추가한 사안에서, 시장이 위 처분의 근거 법령을 추가한 것은 기본적 사실관계의 동일성이 인정되지 않는 별개의 사실을 들어 주장하는 것으로서 처분사유 추가·변경이 허용되지 않는데도, 이와 달리 본 원심판단에 법리오해의 잘못이 있다고 한 사례",
            isViewed: false,
            isBookmarked: false,
          },
          {
            title:
              "손해배상(기)[일제 강제징용 노동자상을 제작·설치한 조각가 부부가 해당 노동자상의 모델이 일본인이라는 발언 등을 한 피고를 상대로 손해배상을 청구한 사건]  [대법원 2023. 11. 30. 선고 2022다280283 판결]",
            content:
              "일제 강제징용 피해자를 상징하는 강제징용 노동자상을 제작한 조각가 부부 甲 등이 위 노동자상은 조선인이 아니라 일본인들을 모델로 만들었다는 발언들을 한 시의회 의원 乙을 상대로 허위사실 적시에 의한 명예훼손 등을 주장하며 손해배상을 구한 사안에서, 위 발언들은 통상적인 어휘의 의미나 전후 문맥 등 전체적인 흐름, 사회평균인의 지식이나 경험 등을 고려하여 그 표현의 의미를 확정할 경우 사실의 적시가 아니라 의견의 표명이나 구체적인 정황 제시가 있는 의혹의 제기에 불과하여 명예훼손의 불법행위에 해당하지 않는다고 볼 여지가 많고, 위 발언들이 진실한 사실에 기초한 것이 아니라고 하더라도 乙로서는 위 발언들을 행할 당시 그 내용이 진실이라고 믿을 만한 상당한 이유가 있었다고 볼 여지가 많은데도, 이와 달리 본 원심판결에 법리오해 등의 잘못이 있다고 한 사례",
            isViewed: false,
            isBookmarked: false,
          },
          {
            title:
              "공직선거법위반  [대법원 2023. 11. 16. 선고 2023도5915 판결]",
            content:
              "피고인 甲이 구청장 예비후보자로서 선거구 내 길거리에서 총 3회에 걸쳐 자신의 이름과 홍보 내용이 기재된 표지물을 착용하지 않고 양손에 잡고 머리 위로 든 채 선거구민들을 상대로 지지를 호소하는 방법으로 선거운동을 함으로써 선거운동기간 전에 공직선거법에 규정된 방법 이외의 방법으로 선거운동을 하였다는 공소사실로 기소된 사안에서, 공소사실을 유죄로 판단한 원심판결을 정당하다고 한 사례",
            isViewed: false,
            isBookmarked: false,
          },
        ],
        message: "판례를 성공적으로 가져왔습니다.",
      });
    },
  ),

  http.get("/precedent", async ({ request }) => {
    // Header에서 AuthToken 가져오기
    // const authToken = request.headers.get("Authorization");

    // URL 에서 PARAMS 가져오기
    const url = new URL(request.url);
    const caseNumber = url.searchParams.get("caseNumber");

    if (!caseNumber) {
      HttpResponse.json({
        Header: {
          status: 409,
        },
        Body: {
          code: "G002",
          message: ERR_MSG.G002,
        },
      });
    }

    return HttpResponse.json(
      {
        data: {
          title: "string",
          summary: {
            1: {
              1: "string",
              2: "string",
            },
            2: "string",
            3: "string",
            4: "string",
            5: "string",
            6: "string",
          },
          detail: {
            basicInformation: {
              graph: {
                category: {
                  incident: "string",
                  detail: "string",
                },
                caseNumber: "string",
                caseName: "string",
                courthouse: "string",
                judgementDate: "date",
                instanceType: "string",
              },
              relatedLawList: [
                {
                  law: "string",
                  link: "string",
                },
                //..
              ],
              citedPrecedent: [
                {
                  precedent: "string",
                  link: "string",
                },
                //..
              ],
            },
            part: {
              plaintiff: "string",
              defendant: "string",
            },
            originalJudgment: {
              caseNumber: "string",
              courthouse: "string",
              judgementDate: "string",
            },
            disposal: {
              type: "string",
              content: "string",
            },
            purport: "string",
            opinion: {
              plaintiff: "string",
              defendant: "string",
            },
            fact: "string",
            judgement: "string",
            result: "string",
          },
        },
        message: "판례를 정상적으로 불러왔습니다.",
      },
      {
        status: 200,
      },
    );
  }),
];
