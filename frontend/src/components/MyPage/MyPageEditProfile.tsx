import { useNavigate } from "react-router-dom";
import { useForm, SubmitHandler } from "react-hook-form";
import { useMutation } from "@tanstack/react-query";

import { EditFormInput } from "@/types";
import { editInfo } from "@/services/authService";

import MyPageEditProfileHeader from "./MyPageEditProfileHeader";
import MyPageEditProfileFooter from "./MyPageEditProfileFooter";
import MyPageEditProfileBody from "./MyPageEditProfileBody";

type SignUpFormProps = {
  closeEditModal: () => void;
};

export default function SignUpForm({ closeEditModal }: SignUpFormProps) {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<EditFormInput>();
  const navigate = useNavigate();

  const { mutate } = useMutation({
    mutationFn: editInfo,
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

  const onSubmit: SubmitHandler<EditFormInput> = (data: EditFormInput) => {
    mutate(data);
  };

  return (
    <form
      className={`w-1/2 flex-row items-center justify-center border border-lightgray bg-white p-20 pb-10 shadow-md `}
      onSubmit={handleSubmit(onSubmit)}
    >
      <MyPageEditProfileHeader email={"devkwanwoo@gmail.com"} />
      <MyPageEditProfileBody register={register} errors={errors} />
      <MyPageEditProfileFooter closeEditModal={closeEditModal} />
    </form>
  );
}
