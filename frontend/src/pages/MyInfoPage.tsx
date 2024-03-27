import { useState } from "react";
import MyPageEditProfile from "@/components/MyPage/MyPageEditProfile";
import Modal from "react-modal";

Modal.setAppElement("#root");
export default function MyInfoPage() {
  const [openModal, setOpenModal] = useState<boolean>(false);

  const openEditModal = () => {
    setOpenModal(true);
  };

  const closeEditModal = () => {
    setOpenModal(false);
  };

  return (
    <div className="mx-[300px]">
      무직백수대졸 27세 무수입
      <Modal
        isOpen={openModal}
        onRequestClose={closeEditModal}
        // style={customStyles}
        className="flex h-screen items-center justify-center"
      >
        <MyPageEditProfile />
      </Modal>
      <div className="flex font-TitleLight text-6xl">
        <div className="pt-2">
          <span className="font-TitleMedium">김관우</span> 님의 정보
        </div>
        <button
          className="mx-5 my-2 rounded-lg bg-navy px-4 pt-2 text-2xl text-white"
          onClick={openEditModal}
        >
          정보 수정
        </button>
      </div>
    </div>
  );
}
