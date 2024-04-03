import Loading from "@/assets/lotties/LoadingAnimation.json";
import Lottie from "lottie-react";

export default function LoadingAnimation() {
  return (
    <div>
      <Lottie animationData={Loading} />
    </div>
  );
}
