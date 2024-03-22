import { FaGithub } from "react-icons/fa";

export default function Footer() {
  return (
    <div className="w-full border border-t-gray px-[300px] py-5 font-Content text-gray">
      <p>대표자명 : 김관우 | 이메일 : devkwanwoo@gmail.com</p>
      <div className="mt-2 flex items-baseline justify-between">
        <div className="flex items-baseline gap-2">
          <p className="font-TitleMedium text-3xl text-yellow">판가름</p>
          <p>SSAFY 서울캠퍼스 A509팀</p>
        </div>
        <div>
          <a href="https://github.com/gwanu-dev" target="_blank">
            <FaGithub />
          </a>
        </div>
      </div>
    </div>
  );
}
