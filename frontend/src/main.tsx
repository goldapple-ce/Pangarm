import React from "react";
import ReactDOM from "react-dom/client";
import { RouterProvider } from "react-router-dom";

import "@/index.css";
import "@radix-ui/themes/styles.css";
import { router } from "@/routes/router.tsx";
import { Theme } from "@radix-ui/themes";

import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

import { QueryClient, QueryClientProvider } from "@tanstack/react-query";

const queryClient = new QueryClient();

const enableMocking = async () => {
  if (!import.meta.env.DEV) {
    return;
  }

  return;

  const { worker } = await import("./mocks/browser.ts");

  return worker.start();
};

enableMocking().then(() => {
  ReactDOM.createRoot(document.getElementById("root")!).render(
    <React.StrictMode>
      <QueryClientProvider client={queryClient}>
        <Theme>
          <RouterProvider router={router} />
        </Theme>
      </QueryClientProvider>
    </React.StrictMode>,
  );
});
