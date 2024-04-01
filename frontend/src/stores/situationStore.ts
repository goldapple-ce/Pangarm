import { create } from "zustand";
import { createJSONStorage, persist } from "zustand/middleware";

interface situationState {
  situation: string;
}
interface situationAction {
  setSituation: (situation: string) => void;
}

export const useSituationStore = create<situationState & situationAction>()(
  persist(
    (set) => ({
      situation: "",
      setSituation: (content) => set({ situation: content }),
    }),
    {
      name: "content",
      storage: createJSONStorage(() => sessionStorage),
    },
  ),
);
