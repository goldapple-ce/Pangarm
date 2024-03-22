import { useState } from "react";

type ToggleButtonProps = {
  content: string;
};

export default function ToggleButtonBig({ content }: ToggleButtonProps) {
  const [isToggled, setIsToggled] = useState(false);

  const toggleButton = () => {
    setIsToggled(!isToggled);
  };

  return (
    <button
      onClick={toggleButton}
      className={`${
        isToggled
          ? "border-lightblue bg-navy hover:bg-opacity-90"
          : "border-navy bg-white shadow-md hover:bg-navy hover:bg-opacity-15 "
      }relative me-1.5 inline-flex h-8 w-36 items-center rounded-full border transition-colors focus:outline-none focus:ring-2 focus:ring-navy focus:ring-offset-1`}
    >
      <span
        className={`${
          isToggled ? " translate-x-28  bg-white" : "translate-x-1 bg-navy"
        } inline-block h-6 w-6 transform rounded-full transition-all`}
      />
      <p
        className={`${isToggled ? "-translate-x-1 text-white" : "translate-x-4 text-navy"} font-Content text-navy`}
      >
        {content}
      </p>
    </button>
  );
}
