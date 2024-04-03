import { FaGithub } from "react-icons/fa";
import { RiNotionFill } from "react-icons/ri";
export default function Footer() {
  return (
    <div className=" relative bottom-10 h-fit w-full translate-y-full border-t border-t-gray bg-white px-[300px] py-5 font-Content text-gray">
      <p>대표자명 : 김관우 | 이메일 : devkwanwoo@gmail.com</p>
      <div className="mt-2 flex items-baseline justify-between">
        <div className="flex items-baseline gap-2">
          <p className="font-TitleMedium text-3xl text-yellow">판가름</p>
          <p>SSAFY 서울캠퍼스 A509팀</p>
        </div>
        <div className="flex gap-2">
          <a
            href="https://https://almondine-change-00b.notion.site/PANGARM-SSAFY-10-43dfc4b83fc14b1a9cb4ed7fc811f5bb?pvs=4.notion.so/PANGARM-SSAFY-10-43dfc4b83fc14b1a9cb4ed7fc811f5bb?pvs=4"
            target="_blank"
          >
            <RiNotionFill />
          </a>
          <a href="https://github.com/gwanu-dev" target="_blank">
            <FaGithub />
          </a>
        </div>
      </div>
    </div>
  );
}
