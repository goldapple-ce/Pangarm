import {
  createContext,
  useContext,
  useState,
  Dispatch,
  SetStateAction,
} from "react";
import { FiltersType } from "@/types";

type SearchProviderProps = {
  children: JSX.Element;
};
// `filters` 객체의 구조에 대한 타입을 정의합니다.

const SearchContext = createContext<SearchContextType | null>(null);

// `SearchContext`에 전달될 `value` 객체의 타입을 정의합니다.
type SearchContextType = {
  filters: FiltersType;
  setFilters: Dispatch<SetStateAction<FiltersType>>;
};

// eslint-disable-next-line react-refresh/only-export-components
export const useSearch = () => {
  const context = useContext(SearchContext);
  if (context === null) {
    throw new Error("useSearch must be used within a SerachProvider");
  }
  return context;
};
export const SearchProvider = ({ children }: SearchProviderProps) => {
  const [filters, setFilters] = useState<FiltersType>({
    keywordList: [],
    duration: {
      startDate: "",
      endDate: "",
    },
    preclude: {
      isViewed: true,
      isBookmarked: true,
    },
    minimumSimilarity: 50,
  });
  // 필터 상태와 상태를 변경하는 함수를 value로 넘깁니다.
  return (
    <SearchContext.Provider value={{ filters, setFilters }}>
      {children}
    </SearchContext.Provider>
  );
};
