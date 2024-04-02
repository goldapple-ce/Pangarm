import { useAuthStore } from "@/stores/authStore";

import MainGuest from "@/components/Main/MainGuest";
import MainMember from "@/components/Main/MainMember";

export default function MainPage() {
  const isSignedIn = useAuthStore((state) => state.isSignedIn);

  return (
    <>
      {isSignedIn && <MainMember />}
      {!isSignedIn && <MainGuest />}
    </>
  );
}
