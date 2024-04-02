type PrecedentDetailBoxProps = {
  content: string;
};

export default function PrecedentDetailBox({
  content,
}: PrecedentDetailBoxProps) {
  return (
    <div className="m-2 mx-5 flex rounded border border-lightgray bg-lightblue p-5">
      {content}
    </div>
  );
}
