import ErrorEmpty from "@/assets/lotties/ErrorEmptyAnimation.json";
import Lottie from "lottie-react";

export default function ErrorEmptyAnimation() {
  return (
    <div className="flex flex-col items-center justify-center">
      <Lottie animationData={ErrorEmpty} className="w-1/2" />
      <p className="font-TitleLight text-xl">아무것도 없어요...</p>
    </div>
  );
}
