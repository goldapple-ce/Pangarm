import Lottie from "lottie-react";
import SigninAnimation from "@/assets/lotties/SigninAnimation.json";

export default function SignInAnimation() {
  return (
    <div className="w-full h-full text-center">
      <div>내 상황과 비슷한 판례를 찾고 싶으신가요?</div>
      <div className="text-3xl font-bold mt-2">판가름을 이용해보세요</div>
      <Lottie
        animationData={SigninAnimation}
        loop={false}
        style={{ height: "650px" }}
      />
    </div>
  );
}
