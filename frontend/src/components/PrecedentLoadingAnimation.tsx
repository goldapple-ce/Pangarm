import PrecedentLoading from "@/assets/lotties/PrecedentLoadingAnimation.json";
import Lottie from "lottie-react";

export default function PrecedentLoadingAnimation() {
  return (
    <div>
      <Lottie animationData={PrecedentLoading} />
    </div>
  );
}
