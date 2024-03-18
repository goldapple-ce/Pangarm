import { Box, Flex } from "@radix-ui/themes";

export default function HeaderLeft() {
  return (
    <Flex gap="9">
      <Box>판례 검색</Box>
      <Box>법 뉴스</Box>
      <Box>법령 사전</Box>
    </Flex>
  );
}
