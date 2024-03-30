import { defineConfig, loadEnv } from "vite";
import react from "@vitejs/plugin-react";
import svgr from "vite-plugin-svgr";

// https://vitejs.dev/config/
export default ({ mode }: { mode: string }) => {
  const env = loadEnv(mode, process.cwd(), "");
  return defineConfig({
    plugins: [svgr(), react()],
    resolve: {
      alias: [{ find: "@", replacement: "/src" }],
    },
    server: {
      proxy: {
        "/api": {
          target: env.VITE_BASE_URL,
          changeOrigin: true,
        },
      },
    },
  });
};
// export default defineConfig({
//   plugins: [svgr(), react()],
//   resolve: {
//     alias: [{ find: "@", replacement: "/src" }],
//   },
//   server: {
//     proxy: {
//       "/api": {
//         target: "https://kmj.wookoo.shop",
//       }
//     }
//   }
// });
