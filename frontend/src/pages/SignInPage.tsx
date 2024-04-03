import SignInForm from "../components/SignIn/SignInForm";
import SignInAnimation from "../components/SignIn/SignInAnimation";

export default function SignInPage() {
  return (
    <div className="flex h-full w-full items-center justify-center gap-6 px-20 font-TitleLight">
      <SignInForm />
      <SignInAnimation />
    </div>
  );
}
