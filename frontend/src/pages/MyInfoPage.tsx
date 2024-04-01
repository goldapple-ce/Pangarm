import { useState } from "react";
import MyPageEditProfile from "@/components/MyPage/MyPageEditProfile";
import Modal from "react-modal";
import { Tab, Tabs } from "@/components/Tabs";
import MyPageSubscribedCategory from "@/components/MyPage/MyPageSubscribedCategory";
import {
  getBookmarkedPrecedent,
  getViewedPrecedent,
} from "@/services/precedentService";
import MyPagePrecedent from "@/components/MyPage/MyPagePrecedent";
import { getMemberInfo } from "@/services/authService";
import { useQuery } from "@tanstack/react-query";
import Error500Animation from "@/components/Error/Error500Animation";

Modal.setAppElement("#root");
export default function MyInfoPage() {
  const [openModal, setOpenModal] = useState<boolean>(false);

  const { data, error, isLoading } = useQuery({
    queryKey: [`member`],
    queryFn: getMemberInfo,
  });

  const openEditModal = () => {
    setOpenModal(true);
  };

  const closeEditModal = () => {
    setOpenModal(false);
  };

  if (isLoading) {
    return <>회원 정보를 불러오는 중입니다...</>;
  }

  if (error) {
    return (
      <div>
        <Error500Animation />
      </div>
    );
  }

  return (
    <div className="mx-[300px]">
      {data?.data.data.age} 세 / {data?.data.data.job}
      <Modal
        isOpen={openModal}
        onRequestClose={() => {
          closeEditModal();
        }}
        // style={customStyles}
        className={`flex h-screen items-center justify-center`}
      >
        <MyPageEditProfile closeEditModal={closeEditModal} />
      </Modal>
      <div className="flex font-TitleLight text-6xl">
        <div className="pt-2">
          <span className="font-TitleMedium">{data?.data.data.name}</span> 님의
          정보
        </div>
        <button
          className="mx-5 my-2 rounded-lg bg-navy px-4 text-2xl text-white"
          onClick={openEditModal}
        >
          정보 수정
        </button>
      </div>
      <div className="mt-7">
        <Tabs>
          <Tab id="tab1" aria-label="북마크한 판례">
            <div className="py-4">
              <MyPagePrecedent
                getPrecedent={getBookmarkedPrecedent}
                queryKey="bookmarked"
              />
            </div>
          </Tab>
          <Tab id="tab2" aria-label="최근 본 판례">
            <div className="py-4">
              <MyPagePrecedent
                getPrecedent={getViewedPrecedent}
                queryKey="viewed"
              />
            </div>
          </Tab>
          <Tab id="tab3" aria-label="구독한 키워드">
            <div className="py-4">{<MyPageSubscribedCategory />}</div>
          </Tab>
        </Tabs>
      </div>
    </div>
  );
}
