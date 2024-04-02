type ToggleButtonProps = {
  content: string;
  label: string;
  setToggleFalse: (lable: string) => void;
  setToggleTrue: (lable: string) => void;
  isToggled: boolean;
};

export default function PrecedentSearchToggleSlider({
  content,
  label,
  setToggleFalse,
  setToggleTrue,
  isToggled,
}: ToggleButtonProps) {
  const toggleButton = () => {
    if (isToggled) setToggleFalse(label);
    else setToggleTrue(label);
  };

  return (
    <button
      onClick={toggleButton}
      className={`${
        isToggled
          ? "border-navy bg-white shadow-md hover:bg-navy hover:bg-opacity-15 "
          : "border-lightblue bg-navy hover:bg-opacity-90"
      }relative me-1.5 inline-flex h-8 w-36 items-center rounded-full border transition-colors focus:outline-none focus:ring-2 focus:ring-navy focus:ring-offset-1`}
    >
      <span
        className={`${
          isToggled ? "translate-x-1 bg-navy" : " translate-x-28  bg-white"
        } inline-block h-6 w-6 transform rounded-full transition-all`}
      />
      <p
        className={`${isToggled ? "translate-x-4 text-navy" : "-translate-x-1 text-white"} font-Content text-navy`}
      >
        {content}
      </p>
    </button>
  );
}
