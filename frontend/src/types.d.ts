export type PrecedentItem = {
  title: string;
  content: string;
  isBookmarked: boolean;
  isViewed: boolean;
  similarity?: number;
  keywords?: string[];
};

export type FiltersType = {
  keywords: string[];
  startDate: string;
  endDate: string;
  isViewed: boolean;
  isBookmarked: boolean;
  minSimilarity: number;
};

export type SignInFormInput = {
  email: string;
  password: string;
};

export type SignUpFormInput = {
  email: string;
  password: string;
  name: string;
  age: number;
  gender: number;
  job: string;
};

export type EditFormInput = {
  currentPassword: string;
  nextPassword: string;
  name: string;
  age: number;
  gender: number;
  job: string;
};

export type GetPrecedentBody = {
  data: Data;
  message: string;
};
export type Data = {
  title: string;
  summary: Summary;
  detail: Detail;
};

export type Detail = {
  basicInformation: BasicInformation;
  part: Part;
  originalJudgment: OriginalJudgment;
  disposal: Disposal;
  purport: string;
  opinion: Part;
  fact: string;
  judgement: string;
  result: string;
};
type Disposal = {
  type: string;
  content: string;
};
type OriginalJudgment = {
  caseNumber: string;
  courthouse: string;
  judgementDate: string;
};
type Part = {
  plaintiff: string;
  defendant: string;
};
type BasicInformation = {
  graph: Graph;
  relatedLawList: RelatedLawList[];
  citedPrecedent: CitedPrecedent[];
};
type CitedPrecedent = {
  precedent: string;
  link: string;
};
type RelatedLawList = {
  law: string;
  link: string;
};
type Graph = {
  category: Category;
  caseNumber: string;
  caseName: string;
  courthouse: string;
  judgementDate: string;
  instanceType: string;
};
type Category = {
  incident: string;
  detail: string;
};
