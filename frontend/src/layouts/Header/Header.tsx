import { Flex } from "@radix-ui/themes";

import HeaderLogo from "./HeaderLogo";
import HeaderLeft from "./HeaderLeft";
import HeaderRight from "./HeaderRight";
import { useAuthStore } from "@/stores/authStore";

export default function Header() {
  const isSignedIn = useAuthStore((state) => state.isSignedIn);
  const setSignedOut = useAuthStore((state) => state.setSignedOut);

  const handleSignOut = () => {
    setSignedOut();
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
  }

  return (
    <Flex
      gap="3"
      className="mb-[60px] h-[60px] bg-navy px-10 font-TitleLight text-xl text-white drop-shadow-lg"
      align="center"
      justify="between"
    >
      <HeaderLogo />
      <HeaderLeft />
      <HeaderRight onSignOut={handleSignOut} isSignedIn={isSignedIn} />
    </Flex>
  );
}
