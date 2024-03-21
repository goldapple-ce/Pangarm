import React, { createContext, useContext, useState } from "react";

const SearchContext = createContext(null);

export const useSearch = () => useContext(SearchContext);

export const SearchProvider = ({ children }) => {
  const [filters, setFilters] = useState({
    keywords: [],
    startDate: "",
    endDate: "",
    isViewed: false,
    isBookmarked: false,
    minSimilarity: 50,
  });
  // 필터 상태와 상태를 변경하는 함수를 value로 넘깁니다.
  return (
    <SearchContext.Provider value={{ filters, setFilters }}>
      {children}
    </SearchContext.Provider>
  );
};
