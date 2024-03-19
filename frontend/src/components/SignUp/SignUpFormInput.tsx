interface SignUpFormInputProps {
  type: string;
  placeholder: string;
}

export default function SignUpFormInput({
  type,
  placeholder,
}: SignUpFormInputProps) {
  return (
    <input
      type={type}
      placeholder={placeholder}
      className="w-full rounded-md bg-lightblue p-3"
    />
  );
}
