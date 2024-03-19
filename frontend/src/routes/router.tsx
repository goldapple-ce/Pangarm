import { createBrowserRouter } from "react-router-dom";

import App from "../App";
import SignUpPage from "../pages/SignUpPage";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      {
        path: "/signup",
        element: <SignUpPage />,
      },
    ],
  },
]);
