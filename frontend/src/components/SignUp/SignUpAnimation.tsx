import Lottie from "lottie-react";
import SignupAnimation from "../../assets/SignupAnimation.json";

export default function SignUpAnimation() {
  return (
    <div className="w-full h-full text-center">
      <div>내 상황과 비슷한 판례를 찾고싶으신가요?</div>
      <div className="text-3xl font-bold mt-2">판가름을 이용해보세요</div>
      <Lottie
        animationData={SignupAnimation}
        loop={false}
        style={{ height: "650px" }}
      />
    </div>
  );
}
