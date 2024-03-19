import SignUpFormFooter from "./SignUpFormFooter";
import SignUpFormHeader from "./SignUpFormHeader";
import SignUpFormMain from "./SignUpFormMain";

export default function SignUpForm() {
  return (
    <form
      action=""
      className="w-1/2 p-20 pb-10 h-full border border-lightgray shadow-md"
    >
      <SignUpFormHeader />
      <SignUpFormMain />
      <SignUpFormFooter />
    </form>
  );
}
