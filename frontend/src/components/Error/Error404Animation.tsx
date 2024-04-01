import Error404 from "@/assets/lotties/Error404Animation.json";
import Lottie from "lottie-react";

export default function Error404Animation() {
  return (
    <div className="flex justify-center">
      <Lottie animationData={Error404} />
    </div>
  );
}
