import SignUpAnimation from "@/components/SignUp/SignUpAnimation";
import SignUpForm from "@/components/SignUp/SignUpForm";

export default function SignUpPage() {
  return (
    <div className="flex h-full w-full items-center justify-center gap-6 px-20 font-TitleLight">
      <SignUpForm />
      <SignUpAnimation />
    </div>
  );
}
