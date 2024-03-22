import * as Slider from "@radix-ui/react-slider";

type SimilaritySliderProps = {
  setSimilarity: (value: number[]) => void;
};

export default function SimilaritySlider({
  setSimilarity,
}: SimilaritySliderProps) {
  return (
    <>
      <Slider.Root
        className="relative flex h-5 w-[300px] touch-none select-none items-center"
        defaultValue={[50]}
        max={100}
        step={1}
        onValueChange={(value) => setSimilarity(value)}
      >
        <Slider.Track className="bg-blackA7 relative h-[10px] grow rounded-full bg-navy">
          <Slider.Range className=" absolute h-full rounded-full border border-lightgray bg-slate-300" />
        </Slider.Track>
        <Slider.Thumb className="shadow-blackA4 hover:bg-violet3 focus:shadow-blackA5 block h-5 w-5 rounded-[10px] bg-white shadow-[0_1px_5px] focus:shadow-[0_0_0_2px] focus:outline-none" />
      </Slider.Root>
      <div className="flex justify-between font-Content">
        <p>0</p>
        <p>100</p>
      </div>
    </>
  );
}
