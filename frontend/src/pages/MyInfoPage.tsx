import { useEffect, useState } from "react";
import MyPageEditProfile from "@/components/MyPage/MyPageEditProfile";
import Modal from "react-modal";
import { Tab, Tabs } from "@/components/Tabs";
import MyPageBookmarked from "@/components/MyPage/MyPageBookmarked";
import MyPageRecentlyViewed from "@/components/MyPage/MyPageRecentlyViewed";
import MyPageSubscribedKeywords from "@/components/MyPage/MyPageSubscribedKeywords";
import { postPrecedentSearch } from "@/services/precedentService";
import { PrecedentItemType } from "@/types";

Modal.setAppElement("#root");
export default function MyInfoPage() {
  const [openModal, setOpenModal] = useState<boolean>(false);
  const [isDetailOpen, setIsDetailOpen] = useState<boolean>(false);
  const [bookmarkedPrecedentList, setBookmarkedPrecedentList] =
    useState<PrecedentItemType[]>();
  const [viewedPrecedentList, setViewedPrecedentList] =
    useState<PrecedentItemType[]>();
  const openEditModal = () => {
    setOpenModal(true);
  };

  const closeEditModal = () => {
    setOpenModal(false);
  };

  useEffect(() => {
    const fetchPrecedents = async () => {
      try {
        const res = await postPrecedentSearch("something", 1, 10);
        if (res && res.data && res.data.data) {
          setBookmarkedPrecedentList(res.data.data);
          setViewedPrecedentList(res.data.data);
        }
      } catch (error) {
        console.error("Error fetching precedents:", error);
      }
    };
  }, []);

  return (
    <div className="mx-[300px]">
      무직백수대졸 27세 무수입
      <Modal
        isOpen={openModal}
        onRequestClose={() => {
          closeEditModal();
        }}
        // style={customStyles}
        className={`flex h-screen items-center justify-center`}
      >
        <MyPageEditProfile closeEditModal={closeEditModal} openModal={false} />
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
      <div className="mt-7">
        <Tabs>
          <Tab id="tab1" aria-label="북마크한 판례">
            <div className="py-4">
              { bookmarkedPrecedentList &&
              <MyPageBookmarked
              />
              }
            </div>
          </Tab>
          <Tab id="tab2" aria-label="최근 본 판례">
            <div className="py-4">
              { viewedPrecedentList &&
                <MyPageRecentlyViewed  />
              }
            </div>
          </Tab>
          <Tab id="tab3" aria-label="구독한 키워드">
            <div className="py-4">
              { 
                <MyPageSubscribedKeywords />
              }
            </div>
          </Tab>
        </Tabs>
      </div>
    </div>
  );
}
