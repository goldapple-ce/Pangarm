import { useNavigate } from "react-router-dom";
import { useForm, SubmitHandler } from "react-hook-form";
import { useMutation } from "@tanstack/react-query";

import { SignUpFormInput } from "@/types";
import { signUp } from "@/services/authService";

import SignUpFormFooter from "@/components/SignUp/SignUpFormFooter";
import SignUpFormMain from "@/components/SignUp/SignUpFormMain";
import MyPageEditProfileHeader from "./MyPageEditProfileHeader";

export default function SignUpForm() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<SignUpFormInput>();
  const navigate = useNavigate();

  const { mutate } = useMutation({
    mutationFn: signUp,
    onSuccess: (response) => {
      const { message } = response.data;
      const status = response.status;

      if (status === 200) {
        alert(message);
        navigate("/");
      }
    },
    onError: (error) => {
      console.error(error);
    },
  });

  const onSubmit: SubmitHandler<SignUpFormInput> = (data: SignUpFormInput) => {
    mutate(data);
  };
  // TODO email
  return (
    <form
      className="h-full w-1/2 border border-lightgray p-20 pb-10 shadow-md"
      onSubmit={handleSubmit(onSubmit)}
    >
      <MyPageEditProfileHeader email={""} />
      <SignUpFormMain register={register} errors={errors} />
      <SignUpFormFooter />
    </form>
  );
}
