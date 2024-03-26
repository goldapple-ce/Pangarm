import { Box, Flex } from "@radix-ui/themes";
import { Link } from "react-router-dom";

export default function HeaderLeft() {
  return (
    <Flex gap="9">
      <Box>판례 검색</Box>
      <Link to="news">
        <Box>법 뉴스</Box>
      </Link>
      <Box>법령 사전</Box>
    </Flex>
  );
}
