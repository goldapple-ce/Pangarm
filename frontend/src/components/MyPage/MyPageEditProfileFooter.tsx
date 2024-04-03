type MyPageEditProfileFooterProps = {
  closeEditModal: () => void;
};

export default function MyPageEditProfileFooter({
  closeEditModal,
}: MyPageEditProfileFooterProps) {
  return (
    <div className="mt-10 font-TitleLight">
      <button className="flex w-full items-center justify-center rounded-xl bg-navy p-2 text-2xl text-white">
        정보수정
      </button>
      <button
        className="mb-10 mt-3 flex w-full items-center justify-center rounded-xl border border-navy bg-white p-2 text-2xl text-navy"
        onClick={() => { closeEditModal() }}
      >
        취소
      </button>
    </div>
  );
}
