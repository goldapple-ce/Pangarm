import { Outlet } from "react-router-dom";
import Header from "@/layouts/Header/Header";
import Footer from "@/layouts/Footer/Footer";

export default function App() {
  return (
    <>
      <Header />
      <Outlet />
      <Footer />
    </>
  );
}
