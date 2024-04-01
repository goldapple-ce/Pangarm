import { create } from "zustand";
import { createJSONStorage, persist } from "zustand/middleware";

interface contentState {
  content: string;
}
interface contentAction {
  setContent: (content: string) => void;
}

export const useContentStore = create<contentState& contentAction>()(
  persist(
    (set) => ({
      content: '',
      setContent: (content) => set({ content: content }),
    }),
    {
      name: "content",
      storage: createJSONStorage(() => sessionStorage),
    },
  ),
);
