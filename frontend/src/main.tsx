import React from "react";
import ReactDOM from "react-dom/client";
import { Theme } from "@radix-ui/themes";
import { RouterProvider } from "react-router-dom";

import { router } from "./routes/router.tsx";
import "./index.css";
import "@radix-ui/themes/styles.css";

const enableMocking = async () => {
  if (!import.meta.env.DEV) {
    return;
  }

  const { worker } = await import("./mocks/browser.ts");

  return worker.start();
};

enableMocking().then(() => {
  ReactDOM.createRoot(document.getElementById("root")!).render(
    <React.StrictMode>
      <Theme>
        <RouterProvider router={router} />
      </Theme>
    </React.StrictMode>,
  );
});
