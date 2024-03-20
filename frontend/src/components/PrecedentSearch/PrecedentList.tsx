import React from "react";
import PrecedentListItem from "./PrecedentListItem";
import { PrecedentItem } from "../../types";
import * as Select from "@radix-ui/react-select";

import {
  CheckIcon,
  ChevronDownIcon,
  ChevronUpIcon,
} from "@radix-ui/react-icons";

interface PrecedentListProps {
  precedentList: PrecedentItem[];
}

// export default function PrecedentList({ precedentList }: PrecedentListProps) {
export default function PrecedentList({ precedentList }: PrecedentListProps) {
  return (
    <>
      <div className="mb-3 flex justify-between">
        <div className="text-xl">
          검색 결과 총 <span className="text-yellow">12,300</span> 건
        </div>

        <div>
          <Select.Root>
            <Select.Trigger
              className="text-violet11 hover:bg-mauve3 mx-2 inline-flex h-[35px] items-center justify-center gap-[5px] rounded bg-white px-[15px] text-[13px] leading-none shadow-[0_2px_10px] shadow-black/10 focus:shadow-[0_0_0_2px]"
              aria-label="orderBy"
            >
              <Select.Value placeholder="정렬기준" />
              <Select.Icon className="text-violet11">
                <ChevronDownIcon />
              </Select.Icon>
            </Select.Trigger>
            <Select.Portal>
              <Select.Content className="overflow-hidden rounded-md bg-white shadow-[0px_10px_38px_-10px_rgba(22,_23,_24,_0.35),0px_10px_20px_-15px_rgba(22,_23,_24,_0.2)]">
                <Select.ScrollUpButton className="text-violet11 flex h-[25px] cursor-default items-center justify-center bg-white">
                  <ChevronUpIcon />
                </Select.ScrollUpButton>
                <Select.Viewport className="p-[5px]">
                  <Select.Group>
                    <Select.Label className="text-mauve11 text-s px-[25px] leading-[25px]">
                      정렬기준
                    </Select.Label>
                    <Select.Item
                      value="similarity"
                      className="text-violet11 data-[disabled]:text-mauve8 data-[highlighted]:bg-violet9 data-[highlighted]:text-violet1 relative flex h-[25px] select-none items-center rounded-[3px] pl-[25px] pr-[35px] text-[13px] leading-none data-[disabled]:pointer-events-none data-[highlighted]:outline-none"
                    >
                      <Select.ItemText>유사도</Select.ItemText>
                      <Select.ItemIndicator className="absolute left-0 inline-flex w-[25px] items-center justify-center">
                        {" "}
                        <CheckIcon />
                      </Select.ItemIndicator>
                    </Select.Item>
                    <Select.Item
                      value="date"
                      className="text-violet11 data-[disabled]:text-mauve8 data-[highlighted]:bg-violet9 data-[highlighted]:text-violet1 relative flex h-[25px] select-none items-center rounded-[3px] pl-[25px] pr-[35px] text-[13px] leading-none data-[disabled]:pointer-events-none data-[highlighted]:outline-none"
                    >
                      <Select.ItemText>선고날짜</Select.ItemText>
                      <Select.ItemIndicator className="absolute left-0 inline-flex w-[25px] items-center justify-center">
                        {" "}
                        <CheckIcon />
                      </Select.ItemIndicator>
                    </Select.Item>
                  </Select.Group>
                </Select.Viewport>
                <Select.ScrollDownButton className="text-violet11 flex h-[25px] cursor-default items-center justify-center bg-white">
                  <ChevronDownIcon />
                </Select.ScrollDownButton>
              </Select.Content>
            </Select.Portal>
          </Select.Root>
          <Select.Root>
            <Select.Trigger
              className="text-violet11 hover:bg-mauve3 inline-flex h-[35px] items-center justify-center gap-[5px] rounded bg-white px-[15px] text-[13px] leading-none shadow-[0_2px_10px] shadow-black/10 focus:shadow-[0_0_0_2px]"
              aria-label="orderBy"
            >
              <Select.Value placeholder="순서" />
              <Select.Icon className="text-violet11">
                <ChevronDownIcon />
              </Select.Icon>
            </Select.Trigger>
            <Select.Portal>
              <Select.Content className="overflow-hidden rounded-md bg-white shadow-[0px_10px_38px_-10px_rgba(22,_23,_24,_0.35),0px_10px_20px_-15px_rgba(22,_23,_24,_0.2)]">
                <Select.ScrollUpButton className="text-violet11 flex h-[25px] cursor-default items-center justify-center bg-white">
                  <ChevronUpIcon />
                </Select.ScrollUpButton>
                <Select.Viewport className="p-[5px]">
                  <Select.Group>
                    <Select.Label className="text-mauve11 text-s px-[25px] leading-[25px]">
                      순서
                    </Select.Label>
                    <Select.Item
                      value="similarity"
                      className="text-violet11 data-[disabled]:text-mauve8 data-[highlighted]:bg-violet9 data-[highlighted]:text-violet1 relative flex h-[25px] select-none items-center rounded-[3px] pl-[25px] pr-[35px] text-[13px] leading-none data-[disabled]:pointer-events-none data-[highlighted]:outline-none"
                    >
                      <Select.ItemText>오름차순</Select.ItemText>
                      <Select.ItemIndicator className="absolute left-0 inline-flex w-[25px] items-center justify-center">
                        {" "}
                        <CheckIcon />
                      </Select.ItemIndicator>
                    </Select.Item>
                    <Select.Item
                      value="date"
                      className="text-violet11 data-[disabled]:text-mauve8 data-[highlighted]:bg-violet9 data-[highlighted]:text-violet1 relative flex h-[25px] select-none items-center rounded-[3px] pl-[25px] pr-[35px] text-[13px] leading-none data-[disabled]:pointer-events-none data-[highlighted]:outline-none"
                    >
                      <Select.ItemText>내림차순</Select.ItemText>
                      <Select.ItemIndicator className="absolute left-0 inline-flex w-[25px] items-center justify-center">
                        {" "}
                        <CheckIcon />
                      </Select.ItemIndicator>
                    </Select.Item>
                  </Select.Group>
                </Select.Viewport>
                <Select.ScrollDownButton className="text-violet11 flex h-[25px] cursor-default items-center justify-center bg-white">
                  <ChevronDownIcon />
                </Select.ScrollDownButton>
              </Select.Content>
            </Select.Portal>
          </Select.Root>
        </div>
      </div>
      <div>
        {precedentList &&
          precedentList.map(({ title, content, isBookmarked, isViewed }) => (
            <React.Fragment key={title}>
              <PrecedentListItem
                key={title}
                title={title}
                content={content}
                isBookmarked={isBookmarked}
                isViewed={isViewed}
              ></PrecedentListItem>
              <hr className="opacity-30" />
            </React.Fragment>
          ))}
      </div>
    </>
  );
}
