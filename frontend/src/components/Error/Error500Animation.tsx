import Error500 from "@/assets/lotties/Error500Animation.json";
import Lottie from "lottie-react";

export default function Error500Animation() {
  return (
    <div className="flex justify-center">
      <div className="h-1/2 w-1/2">
        <Lottie animationData={Error500} className="" />
      </div>
    </div>
  );
}
