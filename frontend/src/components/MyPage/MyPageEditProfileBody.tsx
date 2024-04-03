import { genderList, jobList } from "@/constants";
import { EditFormInput } from "@/types";
import { useState } from "react";
import { FieldErrors, UseFormRegister } from "react-hook-form";
import { PiEye, PiEyeClosedDuotone } from "react-icons/pi";

interface SignUpFormMainProps {
  register: UseFormRegister<EditFormInput>;
  errors: FieldErrors<EditFormInput>;
}

export default function MyPageEditProfileBody({
  register,
  errors,
}: SignUpFormMainProps) {
  const [showCurrentPassword, setShowCurrentPassword] =
    useState<boolean>(false);
  const [showNextPassword, setShowNextPassword] = useState<boolean>(false);
  return (
    <div className="flex flex-col gap-2">
      <div className="relative">
        <input
          {...register("currentPassword")}
          type={showCurrentPassword ? "text" : "password"}
          placeholder="현재 비밀번호를 입력해주세요"
          className="w-full rounded-md bg-lightblue p-3"
          required
        />
        {showCurrentPassword ? (
          <PiEye
            size={20}
            className="absolute right-2 top-3 cursor-pointer"
            onClick={() => setShowCurrentPassword((prev) => !prev)}
          />
        ) : (
          <PiEyeClosedDuotone
            size={20}
            className="absolute right-2 top-3 cursor-pointer"
            onClick={() => setShowCurrentPassword((prev) => !prev)}
          />
        )}
      </div>
      <div className="relative">
        <input
          {...register("nextPassword", {
            required: "변경할 비밀번호를 입력해주세요",
            pattern: {
              value:
                /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/,
              message:
                "비밀번호: 8~16자의 영문, 숫자, 특수문자를 사용해 주세요",
            },
          })}
          type={showNextPassword ? "text" : "password"}
          placeholder="변경할 비밀번호를 입력해주세요"
          className="w-full rounded-md bg-lightblue p-3"
          autoComplete="off"
          required
        />
        {showNextPassword ? (
          <PiEye
            size={20}
            className="absolute right-2 top-3 cursor-pointer"
            onClick={() => setShowNextPassword((prev) => !prev)}
          />
        ) : (
          <PiEyeClosedDuotone
            size={20}
            className="absolute right-2 top-3 cursor-pointer"
            onClick={() => setShowNextPassword((prev) => !prev)}
          />
        )}
      </div>
      <div className="pl-3 text-xs">
        {errors.nextPassword && (
          <li className="text-red-500">{errors.nextPassword.message}</li>
        )}
      </div>

      <input
        {...register("name", {
          required: "이름을 입력해주세요",
          pattern: {
            value: /^[가-힣]+$/,
            message: "이름: 한글을 사용해 주세요. (특수기호, 공백 사용 불가)",
          },
        })}
        type="text"
        placeholder="이름을 입력해주세요"
        className="w-full rounded-md bg-lightblue p-3"
      />

      <div className="flex justify-between gap-2">
        <input
          {...register("age")}
          type="number"
          placeholder="나이를 입력해주세요"
          className="w-full rounded-md bg-lightblue p-3"
        />

        <select
          {...register("gender")}
          className="w-full rounded-md bg-lightblue p-3"
        >
          {genderList.map((gender, index) => (
            <option key={index} value={index}>
              {gender}
            </option>
          ))}
        </select>
      </div>

      <select
        {...register("job")}
        className="w-full rounded-md bg-lightblue p-3"
      >
        {jobList.map((job, index) => (
          <option key={index} value={job}>
            {job}
          </option>
        ))}
      </select>
      <div className="pl-3 text-xs">
        {errors.name && <li className="text-red-500">{errors.name.message}</li>}
      </div>
    </div>
  );
}
