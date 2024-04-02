import axios, { HttpStatusCode } from "axios";
import { useMutation } from "@tanstack/react-query";

import { postSubscribeNewsCategory } from "@/services/authService";

export default function NewsDetailCategoryListItem({
  category,
}: {
  category: string;
}) {
  const { mutate } = useMutation({
    mutationFn: (category: string) => postSubscribeNewsCategory(category),
  });

  const handleSubscribe = () => {
    mutate(category, {
      onSuccess: (response) => {
        const status = response.status;

        if (status === HttpStatusCode.Created) {
          alert(`'${category}' 카테고리 구독에 성공했습니다!`);
          return;
        }
      },
      onError: (error) => {
        if (axios.isAxiosError(error)) {
          const response = error.response;
          if (response?.status === HttpStatusCode.Conflict) {
            alert("이미 구독한 카테고리입니다");
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
