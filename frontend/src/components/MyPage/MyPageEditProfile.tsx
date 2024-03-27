import { useNavigate } from "react-router-dom";
import { useForm, SubmitHandler } from "react-hook-form";
import { useMutation } from "@tanstack/react-query";

import { EditFormInput } from "@/types";
import { signUp } from "@/services/authService";

import MyPageEditProfileHeader from "./MyPageEditProfileHeader";
import MyPageEditProfileFooter from "./MyPageEditProfileFooter";
import MyPageEditProfileBody from "./MyPageEditProfileBody";

export default function SignUpForm() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<EditFormInput>();
  const navigate = useNavigate();

  const { mutate } = useMutation({
    mutationFn: signUp,
    onSuccess: (response: { data: { message: any }; status: any }) => {
      const { message } = response.data;
      const status = response.status;

      if (status === 200) {
        alert(message);
        navigate("/");
      }
    },
    onError: (error: any) => {
      console.error(error);
    },
  });

  const onSubmit: SubmitHandler<EditFormInput> = (data: EditFormInput) => {
    mutate(data);
  };
  // TODO email
  return (
    <form
      className="h-1/2 w-1/2 border bg-white border-lightgray p-20 pb-10 shadow-md"
      onSubmit={handleSubmit(onSubmit)}
    >
      <MyPageEditProfileHeader email={"devkwanwoo@gmail.com"} />
      <MyPageEditProfileBody register={register} errors={errors} />
      <MyPageEditProfileFooter />
    </form>
  );
}
