interface SignUpFormSelectProps {
  optionList: string[];
}

export default function SignUpFormSelect({
  optionList,
}: SignUpFormSelectProps) {
  return (
    <select name="" id="" className="w-full rounded-md bg-lightblue p-3">
      {optionList.map((option, index) => (
        <option key={index} value={index}>
          {option}
        </option>
      ))}
    </select>
  );
}
