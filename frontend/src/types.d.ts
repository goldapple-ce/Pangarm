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
