import { createBrowserRouter } from "react-router-dom";

import App from "@/App";
import SignUpPage from "@/pages/SignUpPage";
import MainPage from "@/pages/MainPage";
import SignInPage from "@/pages/SignInPage";
import PrecedentSearchPage from "@/pages/PrecedentSearchPage";
import MyInfoPage from "@/pages/MyInfoPage";
import NewsPage from "@/pages/NewsPage";
import NewsDetailPage from "@/pages/NewsDetailPage";

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
        path: "mypage",
        element: <MyInfoPage />,
      },
      {
        path: "news",
        element: <NewsPage />,
      },
      {
        path: "news/:newsId",
        element: <NewsDetailPage />,
      },
    ],
  },
]);
