import { genderList, jobList } from "../../constants";
import SignUpFormInput from "./SignUpFormInput";
import SignUpFormSelect from "./SignUpFormSelect";

export default function SignUpFormMain() {
  return (
    <div>
      <div className="flex flex-col gap-2">
        <SignUpFormInput type="email" placeholder="이메일을 입력해주세요" />
        <SignUpFormInput
          type="password"
          placeholder="비밀번호을 입력해주세요"
        />
        <SignUpFormInput
          type="password"
          placeholder="비밀번호 확인을 위해 한 번 더 입력해주세요"
        />

        <div></div>

        <SignUpFormInput type="text" placeholder="이름을 입력해주세요" />
        <div className="flex w-full justify-between gap-2">
          <SignUpFormInput type="text" placeholder="나이를 입력해주세요" />
          <SignUpFormSelect optionList={genderList}/>
        </div>
        <SignUpFormSelect optionList={jobList}/>
      </div>
    </div>
  );
}
