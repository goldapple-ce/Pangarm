import Lottie, { LottieRefCurrentProps } from "lottie-react";
import { useEffect, useRef, useState } from "react";
import { PiEye, PiEyeClosedDuotone } from "react-icons/pi";

import animationData from "@/assets/lotties/BookmarkAnimation-2.json";
import { putSubscribeBookmark } from "@/services/authService";
import { useMutation } from "@tanstack/react-query";
import PrecedentDetail from "@/components/Precedent/PrecedentDetail";

type PrecedentListItemProps = {
  id: number;
  caseNumber: string;
  caseName: string;
  summary: string;
  keywordList: string[];
  createAt: string;
  viewed: false;
  bookmarked: false;
};

export default function PrecedentItem({
  id,
  caseNumber,
  caseName,
  summary,
  keywordList,
  createAt,
  viewed,
  bookmarked,
}: PrecedentListItemProps) {
  const [isDetailOpen, setDetailOpen] = useState<boolean>(false);
  const lottieRef = useRef<LottieRefCurrentProps>(null);
  const rootRef = useRef<HTMLDivElement>(null);
  const isBookmarkedRef = useRef<boolean>(bookmarked);

  // TODO axios 요청 send
  const { mutate } = useMutation({
    mutationFn: putSubscribeBookmark,
  });

  const showDetail = () => {
    setDetailOpen(true);
  };

  const closeDetail = () => {
    setDetailOpen(false);
  };

  const handleBookmarkClick = () => {
    mutate(id);
    // console.log(isSuccess);
    if (lottieRef.current) {
      lottieRef.current.setSpeed(1.5);
      if (isBookmarkedRef.current) {
        lottieRef.current.setDirection(-1);
      } else {
        lottieRef.current.setDirection(1);
      } // 애니메이션 방향을 거꾸로 설정
      lottieRef.current.play();
    }
    isBookmarkedRef.current = !isBookmarkedRef.current;
  };
  useEffect(() => {
    if (isBookmarkedRef.current && lottieRef.current) {
      lottieRef.current.goToAndStop(119, true);
    }
  }, []);
  return (
    <>
      {isDetailOpen && (
        <PrecedentDetail
          closeDetail={closeDetail}
          caseNo={caseNumber}
          keywordList={keywordList}
        />
      )}
      <div
        className=" font-lighthover:text-clip m-6 w-5/12 font-Content text-xl"
        ref={rootRef}
      >
        <div className="w-10/12 truncate">
          {keywordList &&
            keywordList.map((value) => {
              return (
                <span
                  key={value}
                  className="me-2 font-Content text-sm text-lightgray"
                >
                  #{value}
                </span>
              );
            })}
        </div>
        <div className="flex justify-between ">
          <div
            className="w-3/4 min-w-0 flex-shrink truncate"
            onClick={() => {
              showDetail();
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
              className="w-8 hover:cursor-pointer"
              animationData={animationData}
              loop={false}
              autoplay={false}
              lottieRef={lottieRef}
              onClick={handleBookmarkClick}
            />
          </div>
        </div>
        <div className=" flex justify-between text-sm">
          {createAt ? (
            <span>{createAt}. 선고</span>
          ) : (
            <span>선고 날짜 미정</span>
          )}
        </div>
        <p
          onClick={() => {
            showDetail();
          }}
          className="me-3 mt-1 text-clip text-sm text-gray  hover:text-clip "
        >
          {summary}
        </p>
        <div></div>
      </div>
    </>
  );
}
