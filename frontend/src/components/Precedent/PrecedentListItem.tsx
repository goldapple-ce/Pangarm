import { useRef, useEffect } from "react";
import Lottie, { LottieRefCurrentProps } from "lottie-react";
import { PiEyeClosedDuotone } from "react-icons/pi";
import { PiEye } from "react-icons/pi";

import { PrecedentItem } from "@/types";
import animationData from "@/assets/BookmarkAnimation-2.json";

export default function PrecedentListItem({
  title,
  content,
  isBookmarked,
  isViewed,
}: PrecedentItem) {
  const regex = /\d{4}\.\s\d{1,2}\.\s\d{1,2}/g;
  const date = title.match(regex);
  const lottieRef = useRef<LottieRefCurrentProps>(null);
  const isBookmarkedRef = useRef<boolean>(isBookmarked);
  useEffect(() => {
    if (isBookmarkedRef.current && lottieRef.current) {
      lottieRef.current.goToAndStop(119, true);
    }
  }, []);

  // TODO axios 요청 send
  const handleClick = () => {
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
  /**
   * 1. 아무것도 없음
   * 2. 북마크가 눌려있음 ->
   * 3. 눌렀을때 애니메이션 정 재생
   * 4. 눌렀을 때 애니메이션 초기상태로 돌아가기 (1. 역재생 2. 그냥 깜빡 )
   */
  return (
    <div className=" font-lighthover:text-clip my-3 w-[900px] font-Content text-xl">
      <div className="flex justify-between ">
        <div className="w-3/4 min-w-0 flex-shrink truncate ">
          <p className="text-ellipsis text-xl">{title}</p>
        </div>

        <div className="flex items-center">
          <Lottie
            className="w-8"
            animationData={animationData}
            loop={false}
            autoplay={false}
            lottieRef={lottieRef}
            onClick={handleClick}
          />
          <PiEye className={isViewed ? "" : "hidden"} />
          <PiEyeClosedDuotone className={isViewed ? "hidden" : ""} />
        </div>
      </div>
      <div>
        <p className="text-lg">{date}. 선고</p>
      </div>
      <p className="me-3 mt-1 text-clip text-sm text-gray  hover:text-clip ">
        {content}
      </p>
      <div></div>
    </div>
  );
}
