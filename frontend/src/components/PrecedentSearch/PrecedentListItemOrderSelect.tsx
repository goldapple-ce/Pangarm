import * as Select from "@radix-ui/react-select";

import { IoChevronDown, IoChevronUp, IoCheckmark } from "react-icons/io5";
interface PrecedentListItemOrderSelectProps {
  placeholder: string;
  value: string[];
  label: string[];
}

export default function PrecedentListItemOrderSelect({
  placeholder,
  value,
  label,
}: PrecedentListItemOrderSelectProps) {
  return (
    <Select.Root>
      <Select.Trigger className="text-violet11 hover:bg-mauve3 mx-2 inline-flex h-[35px] items-center justify-center gap-[5px] rounded bg-white px-[15px] text-[13px] leading-none shadow-[0_2px_10px] shadow-black/10 focus:shadow-[0_0_0_2px]">
        <Select.Value placeholder={placeholder} />
        <Select.Icon className="text-violet11">
          <IoChevronDown />
        </Select.Icon>
      </Select.Trigger>
      <Select.Portal>
        <Select.Content className="overflow-hidden rounded-md bg-white shadow-[0px_10px_38px_-10px_rgba(22,_23,_24,_0.35),0px_10px_20px_-15px_rgba(22,_23,_24,_0.2)]">
          <Select.ScrollUpButton className="text-violet11 flex h-[25px] cursor-default items-center justify-center bg-white">
            <IoChevronUp />
          </Select.ScrollUpButton>
          <Select.Viewport className="p-[5px]">
            <Select.Group>
              <Select.Label className="text-mauve11 text-s px-[25px] leading-[25px]">
                {placeholder}
              </Select.Label>

              {value.map((value, index) => (
                <Select.Item
                  value={value}
                  key={value}
                  className="text-violet11 data-[disabled]:text-mauve8 data-[highlighted]:bg-violet9 data-[highlighted]:text-violet1 relative flex h-[25px] select-none items-center rounded-[3px] pl-[25px] pr-[35px] text-[13px] leading-none data-[disabled]:pointer-events-none data-[highlighted]:outline-none"
                >
                  <Select.ItemText>{label[index]}</Select.ItemText>
                  <Select.ItemIndicator className="absolute left-0 inline-flex w-[25px] items-center justify-center">
                    <IoCheckmark />
                  </Select.ItemIndicator>
                </Select.Item>
              ))}
            </Select.Group>
          </Select.Viewport>
          <Select.ScrollDownButton className="text-violet11 flex h-[25px] cursor-default items-center justify-center bg-white">
            <IoChevronDown />
          </Select.ScrollDownButton>
        </Select.Content>
      </Select.Portal>
    </Select.Root>
  );
}
