import { createBrowserRouter } from "react-router-dom";

import App from "@/App";
import SignUpPage from "@/pages/SignUpPage";
import MainPage from "@/pages/MainPage";
import SignInPage from "@/pages/SignInPage";
import PrecedentSearchPage from "@/pages/PrecedentSearchPage";
import MyInfoPage from "@/pages/MyInfoPage";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      {
        index: true,
        element: <MainPage />,
      },
      {
        path: "signup",
        element: <SignUpPage />,
      },
      {
        path: "signin",
        element: <SignInPage />,
      },
      {
        path: "main",
        element: <MainPage />,
      },
      {
        path: "search-precedent",
        element: <PrecedentSearchPage />,
      },
      {
        path: "my-page",
        element: <MyInfoPage />,
      },
    ],
  },
]);
