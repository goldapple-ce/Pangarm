import { Box, Flex } from "@radix-ui/themes";
import { Link } from "react-router-dom";

export default function HeaderRight() {
  return (
    <Flex gap="9">
      <Link to={"/signup"}>
        <Box>회원가입</Box>
      </Link>
      <Link to={"/signin"}>
        <Box>로그인</Box>
      </Link>
    </Flex>
  );
}
