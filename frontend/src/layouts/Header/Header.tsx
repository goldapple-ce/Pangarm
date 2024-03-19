import { Flex } from "@radix-ui/themes";
import HeaderLogo from "./HeaderLogo";
import HeaderLeft from "./HeaderLeft";
import HeaderRight from "./HeaderRight";

export default function Header() {
  return (
    <Flex
      gap="3"
      className="mb-[60px] h-[60px] bg-navy px-10 font-TitleLight text-xl text-white drop-shadow-lg"
      align="center"
      justify="between"
    >
      <HeaderLogo />
      <HeaderLeft />
      <HeaderRight />
    </Flex>
  );
}
