import { useNavigate } from "react-router-dom";

import Swal from "sweetalert2";
import axios, { HttpStatusCode } from "axios";
import { useForm, SubmitHandler } from "react-hook-form";
import { useMutation } from "@tanstack/react-query";

import { SignUpFormInput } from "@/types";
import { signUp } from "@/services/authService";

import SignUpFormFooter from "@/components/SignUp/SignUpFormFooter";
import SignUpFormHeader from "@/components/SignUp/SignUpFormHeader";
import SignUpFormMain from "@/components/SignUp/SignUpFormMain";

export default function SignUpForm() {
  const {
    register,
    handleSubmit,
    setError,
    formState: { errors },
  } = useForm<SignUpFormInput>();
  const navigate = useNavigate();

  const { mutate } = useMutation({
    mutationFn: signUp,
    onSuccess: (response) => {
      const status = response.status;

      if (status === HttpStatusCode.Created) {
        Swal.fire({
          text: "회원가입에 성공했습니다.",
          icon: "success",
        }).then(() => {
          navigate("/");
        });
        return;
      }
    },
    onError: (error) => {
      if (axios.isAxiosError(error)) {
        const response = error.response;
        if (response) {
          const { businessCode, errorMessage } = response.data;
          console.log(businessCode, errorMessage);

          if (businessCode === "M002") {
            setError("root.signUpError", {
              message: "이미 가입된 회원입니다.",
            });
          }
        }
      }
    },
  });

  const onSubmit: SubmitHandler<SignUpFormInput> = (data: SignUpFormInput) => {
    mutate(data);
  };

  return (
    <form
      className="h-full w-1/2 border border-lightgray p-20 pb-10 shadow-md"
      onSubmit={handleSubmit(onSubmit)}
    >
      <SignUpFormHeader />
      <SignUpFormMain register={register} errors={errors} />
      <SignUpFormFooter />
    </form>
  );
}
