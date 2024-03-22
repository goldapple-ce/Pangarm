export const genderList = ["미정", "남자", "여자"];
export const jobList = ["직업1", "직업2", "직업3", "기타"];

export const DATE_REGEX = /\d{4}\.\s\d{1,2}\.\s\d{1,2}/g;

export const SelectPivot = {
  placeholder: "정렬기준",
  value: ["sim_asc", "sim_dec", "date_asc", "date_dec"],
  label: [
    "유사도 높은 순",
    "유사도 낮은 순",
    "선고날짜 최신 순",
    "선고날짜 오래된 순",
  ],
};
export const SearchKeywordExampleList = [
  "로동",
  "교통",
  "교육",
  "부동산",
  "폭력",
  "마약",
  "성범죄",
  "사이버",
  "지적재산",
  "공무",
];

export const PrecedentDetailExample = {
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
        chourthouse: "string",
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
      chourthouse: "string",
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
};
