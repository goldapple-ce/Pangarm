type PrecedentDetailSummaryElementProps = {
  question: string;
  content: string;
};

export default function PrecedentDetailSummaryElement({
  question,
  content,
}: PrecedentDetailSummaryElementProps) {
  return (
    <div className="mt-5">
      <p>{question}</p>
      <div className="mx-6 mt-2 flex h-12 items-center rounded-md border border-lightgray bg-lightblue p-5 text-[15px]">
        {content}
      </div>
    </div>
  );
}
