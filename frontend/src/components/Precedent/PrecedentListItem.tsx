import { useRef, useEffect } from "react";
import Lottie, { LottieRefCurrentProps } from "lottie-react";
import { PiEyeClosedDuotone, PiEye } from "react-icons/pi";

import animationData from "@/assets/lotties/BookmarkAnimation-2.json";
import { useMutation } from "@tanstack/react-query";
import { putSubscribeBookmark } from "@/services/authService";
import { PrecedentItemType } from "@/types";

type PrecedentListItemProps = {
  precedentData: PrecedentItemType;
  showDetail: (caseNo: PrecedentItemType) => void;
};

export default function PrecedentListItem({
  precedentData,
  showDetail,
}: PrecedentListItemProps) {
  const lottieRef = useRef<LottieRefCurrentProps>(null);
  const rootRef = useRef<HTMLDivElement>(null);
  const isBookmarkedRef = useRef<boolean>(precedentData.bookmarked);

  const { mutate, isSuccess } = useMutation({
    mutationFn: putSubscribeBookmark,
  });

  useEffect(() => {
    if (isBookmarkedRef.current && lottieRef.current) {
      lottieRef.current.goToAndStop(119, true);
    }
  }, []);

  const handleBookmarkClick = () => {
    mutate(precedentData.id);
    console.log(isSuccess);
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

  return (
    <div
      className=" font-lighthover:text-clip my-3 w-full font-Content text-xl"
      ref={rootRef}
    >
      <div className="w-10/12 truncate">
        {precedentData.keywordList &&
          precedentData.keywordList.map((value) => {
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
            showDetail(precedentData);
          }}
        >
          <p className="truncate text-xl">
            {precedentData.caseNumber} - {precedentData.caseName}
          </p>
        </div>

        <div className="flex items-center">
          <PiEye className={precedentData.viewed ? "" : "hidden"} />
          <PiEyeClosedDuotone
            className={precedentData.viewed ? "hidden" : ""}
          />
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
        {precedentData.createAt ? (
          <span>{precedentData.createAt}. 선고</span>
        ) : (
          <span>선고 날짜 미정</span>
        )}
        <span>유사도 {precedentData.similarity}%</span>
      </div>
      <p
        onClick={() => {
          showDetail(precedentData);
        }}
        className="me-3 mt-1 text-clip text-sm text-gray  hover:text-clip "
      >
        {precedentData.summary}
      </p>
      <div></div>
    </div>
  );
}
