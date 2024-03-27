type MyPageEditProfileHeaderProps = {
  email: string;
};

export default function MyPageEditProfileHeader({
  email,
}: MyPageEditProfileHeaderProps) {
  return (
    <div className="mb-4 font-TitleLight text-4xl">
      회원가입 <span className="mx-3 text-2xl">{email}</span>
    </div>
  );
}
