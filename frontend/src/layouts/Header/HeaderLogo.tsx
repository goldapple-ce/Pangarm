import { Box, Flex } from "@radix-ui/themes";
import Logo from "../../assets/Logo.svg?react";
import { Link } from "react-router-dom";

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
