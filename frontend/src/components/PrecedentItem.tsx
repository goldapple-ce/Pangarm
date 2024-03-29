import Lottie, { LottieRefCurrentProps } from "lottie-react";
import { useEffect, useRef } from "react";
import { PiEye, PiEyeClosedDuotone } from "react-icons/pi";

import animationData from "@/assets/BookmarkAnimation-2.json";
import { extractDate } from "@/utils/extractUtils";

type PrecedentListItemProps = {
  title: string;
  content: string;
  isBookmarked: boolean;
  isViewed: boolean;
  showDetail: (caseNo: string) => void;
};

export default function PrecedentItem({
  title,
  content,
  isBookmarked,
  isViewed,
  showDetail,
}: PrecedentListItemProps) {
  const date = extractDate(title);

  const lottieRef = useRef<LottieRefCurrentProps>(null);
  const rootRef = useRef<HTMLDivElement>(null);
  const isBookmarkedRef = useRef<boolean>(isBookmarked);

  useEffect(() => {
    if (isBookmarkedRef.current && lottieRef.current) {
      lottieRef.current.goToAndStop(119, true);
    }
  }, []);

  // TODO axios 요청 send
  const handleBookmarkClick = () => {
    if (lottieRef.current) {
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
      className=" font-lighthover:text-clip m-2 h-40 w-[39vh] font-Content text-xl "
      ref={rootRef}
    >
      <div className="flex justify-between ">
        <div
          className="w-3/4 min-w-0 flex-shrink truncate"
          onClick={() => {
            showDetail(title);
          }}
        >
          <p className="text-ellipsis text-xl">{title}</p>
        </div>

        <div className="flex items-center">
          <Lottie
            className="w-8"
            animationData={animationData}
            loop={false}
            autoplay={false}
            lottieRef={lottieRef}
            onClick={handleBookmarkClick}
          />
          <PiEye className={isViewed ? "" : "hidden"} />
          <PiEyeClosedDuotone className={isViewed ? "hidden" : ""} />
        </div>
      </div>
      <div>
        <p className="text-lg">{date}. 선고</p>
      </div>
      <p
        onClick={() => {
          showDetail(title);
        }}
        className="me-3 mt-1 text-clip text-sm text-gray  hover:text-clip "
      >
        {content}
      </p>
      <div></div>
    </div>
  );
}
