export type PrecedentItemType = {
  id: number;
  caseNumber: string;
  caseName: string;
  summary: string;
  similarity: number;
  keywordList: string[];
  createAt: string;
  viewed: false;
  bookmarked: false;
};

export type FiltersType = {
  keywordList: string[];
  duration: Duration;
  preclude: Preclude;
  minimumSimilarity: number;
};

export type Duration = {
  startDate: string;
  endDate: string;
};

export type Preclude = {
  isViewed: boolean;
  isBookmarked: boolean;
};

export type SignInFormInput = {
  email: string;
  password: string;
};

export type EditFormInput = {
  currentPassword: string;
  nextPassword: string;
  name: string;
  age: number;
  gender: number;
  job: string;
};

export type SignUpFormInput = {
  id: number;
  email: string;
  password: string;
  name: string;
  age: number;
  gender: number;
  job: string;
};

export type News = {
  id: string;
  newsLink: string;
  title: string;
  content: string;
  imageUrl: string;
  author: string;
  createdAt: string;
  categoryList: string[];
};

/** 판례 리스트 요약 타입  */
export interface Welcome4 {
  data: Data;
  message: string;
}

export interface PrecedentSummary {
  info: Info;
  relate: Relate;
  parties: Opinion;
  originalJudgement: OriginalJudgement;
  purport: string;
  fact: string;
  opinion: Opinion;
  judgement: string;
  conclusion: string;
  summary: string;
}

export interface Info {
  caseNumber: string;
  caseName: string;
  judgementDate: string;
  type: Type;
}

export interface Type {
  incident: string;
  courtName: string;
  verdict: string;
}

export interface Opinion {
  plaintiff: null;
  defendant: null;
}

export interface OriginalJudgement {
  caseNumber: string;
}

export interface Relate {
  lawList: LawList[];
  precedentList: OriginalJudgement[];
}

export interface LawList {
  lawName: string;
  searchNumber: string;
  searchName: string;
  searchType: string;
  searchKey: string;
}
/******************** */

export type SearchHistory = {
  id: number;
  situation: string;
};

/** 판례 요약 타입 */
export interface PrecedentDetailSummaryType {
  info: Info;
  relate: Relate;
  parties: Parties;
  originalJudgement: PrecedentList;
  purport: string;
  fact: string;
  opinion: Opinion;
  judgement: string;
  conclusion: string;
  summary: string;
}
interface Opinion {
  plaintiff?: string;
  defendant?: string;
}
interface Parties {
  plaintiff?: string;
  defendant?: string;
}
interface Relate {
  lawList: LawList[];
  precedentList: PrecedentList[];
}
interface PrecedentList {
  caseNumber: string;
}
interface LawList {
  lawName: string;
  searchNumber: string;
  searchName: string;
  searchType: string;
  searchKey: string;
}
interface Info {
  caseNumber: string;
  caseName: string;
  judgementDate: string;
  type: Type;
}
interface Type {
  incident: string;
  courtName: string;
  verdict: string;
}

/********* */

/** 판례 상세 타입  */
export interface PrecedentDetailType {
  info: Info;
  relate: Relate;
  body: string;
  isPdf: boolean;
  pdf: boolean;
}

export interface Info {
  caseNumber: string;
  caseName: string;
  judgementDate: string;
  type: Type;
}

export interface Type {
  incident: string;
  courtName: string;
  verdict: string;
}

export interface Relate {
  lawList: LawList[];
  precedentList: PrecedentList[];
}

export interface LawList {
  lawName: string;
  searchNumber: string;
  searchName: string;
  searchType: string;
  searchKey: string;
}

export interface PrecedentList {
  caseNumber: string;
}
