import Swal from "sweetalert2";
import axios, { HttpStatusCode } from "axios";
import { useMutation } from "@tanstack/react-query";

import { postSubscribeNewsCategory } from "@/services/authService";
import { useAuthStore } from "@/stores/authStore";

export default function NewsDetailCategoryListItem({
  category,
}: {
  category: string;
}) {
  const { mutate } = useMutation({
    mutationFn: (category: string) => postSubscribeNewsCategory(category),
  });
  const isSignedIn = useAuthStore((state) => state.isSignedIn);

  const handleSubscribe = () => {
    if (!isSignedIn) {
      Swal.fire({
        title: "로그인이 필요한 서비스입니다!",
        icon: "error",
      });
      return;
    }

    mutate(category, {
      onSuccess: (response) => {
        const status = response.status;

        if (status === HttpStatusCode.Created) {
          Swal.fire({
            text: `'${category}' 카테고리 구독에 성공했습니다!`,
            icon: "success",
          });
          return;
        }
      },
      onError: (error) => {
        if (axios.isAxiosError(error)) {
          const response = error.response;

          if (response?.status === HttpStatusCode.Conflict) {
            Swal.fire({
              text: "이미 구독한 카테고리입니다!",
              icon: "warning",
            });
            return;
          }
        } else {
          console.error("An unexpected error occurred:", error);
        }
      },
    });
  };

  return (
    <div
      className="flex-shrink-0 cursor-pointer rounded-xl border border-navy bg-white px-3 py-0.5 text-sm text-navy duration-150 hover:bg-navy hover:text-white"
      onClick={handleSubscribe}
    >
      #{category}
    </div>
  );
}
