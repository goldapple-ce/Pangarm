import { useRef, useEffect } from "react";
import Lottie, { LottieRefCurrentProps } from "lottie-react";
import { PiEyeClosedDuotone, PiEye } from "react-icons/pi";

import animationData from "@/assets/lotties/BookmarkAnimation-2.json";

type PrecedentListItemProps = {
  caseNumber: string;
  caseName: string;
  summary: string;
  similarity: number;
  keywordList: string[];
  createAt: string;
  viewed: boolean;
  bookmarked: boolean;
  showDetail: (caseNo: string) => void;
};

export default function PrecedentListItem({
  caseNumber,
  caseName,
  summary,
  // similarity,
  // keywordList,
  createAt,
  viewed,
  bookmarked,
  showDetail,
}: PrecedentListItemProps) {
  const lottieRef = useRef<LottieRefCurrentProps>(null);
  const rootRef = useRef<HTMLDivElement>(null);
  const isBookmarkedRef = useRef<boolean>(bookmarked);

  useEffect(() => {
    if (isBookmarkedRef.current && lottieRef.current) {
      lottieRef.current.goToAndStop(119, true);
    }
  }, []);

  // TODO axios 요청 send
  const handleBookmarkClick = () => {
    if (lottieRef.current) {
      lottieRef.current.setSpeed(1.5);
      if (isBookmarkedRef.current) {
        lottieRef.current.setDirection(-1);
      } else {
        lottieRef.current.setDirection(1);
      } // 애니메이션 방향을 거꾸로 설정
      lottieRef.current.play();

      isBookmarkedRef.current = !isBookmarkedRef.current;
    }
  };

  return (
    <div
      className=" font-lighthover:text-clip my-3 w-full font-Content text-xl"
      ref={rootRef}
    >
      <div className="flex justify-between ">
        <div
          className="w-3/4 min-w-0 flex-shrink truncate"
          onClick={() => {
            showDetail(caseNumber);
          }}
        >
          <p className="truncate text-xl">
            {caseNumber} - {caseName}
          </p>
        </div>

        <div className="flex items-center">
          <PiEye className={viewed ? "" : "hidden"} />
          <PiEyeClosedDuotone className={viewed ? "hidden" : ""} />
          <Lottie
            className="w-8"
            animationData={animationData}
            loop={false}
            autoplay={false}
            lottieRef={lottieRef}
            onClick={handleBookmarkClick}
          />
        </div>
      </div>
      <div>
        {createAt ? (
          <p className="text-lg">{createAt}. 선고</p>
        ) : (
          <p>선고 날짜 미정</p>
        )}
      </div>
      <p
        onClick={() => {
          showDetail(caseNumber);
        }}
        className="me-3 mt-1 text-clip text-sm text-gray  hover:text-clip "
      >
        {summary}
      </p>
      <div></div>
    </div>
  );
}
