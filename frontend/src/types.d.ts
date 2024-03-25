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
