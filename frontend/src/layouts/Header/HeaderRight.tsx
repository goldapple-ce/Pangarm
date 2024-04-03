import { Link } from "react-router-dom";
import { Box, Flex } from "@radix-ui/themes";

interface HeaderRightProps {
  onSignOut: () => void;
  isSignedIn: boolean;
}

export default function HeaderRight({
  onSignOut,
  isSignedIn,
}: HeaderRightProps) {
  return (
    <Flex gap="9" align={"center"}>
      {!isSignedIn && (
        <>
          <Link to={"/signup"}>
            <Box>회원가입</Box>
          </Link>
          <Link to={"/signin"}>
            <Box>로그인</Box>
          </Link>
        </>
      )}
      {isSignedIn && (
        <>
          <Link to={"/mypage"}>
            <Box>내정보</Box>
          </Link>
          <Link to={""} onClick={onSignOut}>
            <Box>로그아웃</Box>
          </Link>
        </>
      )}
    </Flex>
  );
}
