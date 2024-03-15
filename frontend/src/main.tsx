import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.tsx";
import "./index.css";
import "@radix-ui/themes/styles.css";

import { Theme } from "@radix-ui/themes";

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
        <App />
      </Theme>
    </React.StrictMode>
  );
});
