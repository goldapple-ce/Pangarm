import { Link } from "react-router-dom";
import { Box, Flex } from "@radix-ui/themes";
import Logo from "@/assets/imgs/Logo.svg?react"

export default function HeaderLogo() {
  return (
    <Link to={"/"}>
      <Flex align="center" gap="4">
        <Logo className="h-8" />
        <Box className="font-TitleLight text-4xl text-yellow">판가름</Box>
      </Flex>
    </Link>
  );
}
