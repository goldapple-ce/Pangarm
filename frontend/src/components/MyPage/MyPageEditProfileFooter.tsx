import { Link } from "react-router-dom";

export default function MyPageEditProfile() {
  return (
    <div className="mt-20">
      <button className="flex w-full items-center justify-center rounded-xl bg-navy p-2 text-2xl text-white">
        회원가입
      </button>
      <Link to={"/signin"}>
        <div className="mt-2 w-full text-end text-lightgray transition duration-300 ease-in-out hover:text-gray">
          이미 회원이신가요?
        </div>
      </Link>
    </div>
  );
}
