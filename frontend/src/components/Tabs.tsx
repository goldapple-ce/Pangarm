import React, { useState } from "react";

type TabsProps = {
  children: JSX.Element[];
};
type TabProps = {
  id: string; // 고유 ID
  children: React.ReactNode;
};
const Tabs = ({ children }: TabsProps) => {
  const [activeTab, setActiveTab] = useState<string>(children[0].props.id);

  const handleClick = (
    e: React.MouseEvent<HTMLButtonElement, MouseEvent>,
    newActiveTab: string,
  ) => {
    e.preventDefault();
    setActiveTab(newActiveTab);
  };

  return (
    <div className="mx-auto ">
      <div className="border-gray-300 flex w-1/2 border-b">
        {children.map((child) => (
          <button
            key={child.props.id}
            className={`${
              activeTab === child.props.id
                ? "border-b-2 border-navy font-bold text-navy"
                : ""
            } text-gray-700 flex-1 py-2 font-TitleMedium`}
            onClick={(e) => handleClick(e, child.props.id)}
          >
            {child.props["aria-label"]}
          </button>
        ))}
      </div>
      <div className="py-4">
        {children.map((child) => {
          if (child.props.id === activeTab) {
            return <div key={child.props.id}>{child.props.children}</div>;
          }
          return null;
        })}
      </div>
    </div>
  );
};

const Tab = ({ id, children }: TabProps) => {
  return (
    <div id={id} className="hidden">
      {children}
    </div>
  );
};
export { Tabs, Tab };
