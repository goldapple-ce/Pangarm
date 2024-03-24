import React from "react";

// type PrecedentDetailSummaryProps = {
//   1: {
//     1: string;
//     2: string;
//   };
//   2: string;
//   3: string;
//   4: string;
//   5: string;
//   6: string;
// }

// export default function PrecedentDetailSummary({ summary } : PrecedentDetailSummaryProps) {

const summary = {
  1: {
    1: "우주 제국력 9018년 12월 12일 황제 즉위 기념일에 그랬어요",
    2: "A가 B에게 총을 발사했어요",
  },
  2: "신원 미상의 남성 A",
  3: "우주 제국 경찰 ",
  4: "A 씨는 우주 제국 경찰의 연설이 정치적으로 알맞지 않다고 판단했어요",
  5: "B씨를 향해 총을 5발 발사했어요",
  6: "B씨는 중상을 입었다.",
};

export default function PrecedentDetailSummary() {
  return (
    <div className=" m-10 font-Content text-[15px] marker:flex">
      <div>
        <p className="mt-7">1. 어떤 상황에 대한 것인가요?</p>
        <div className="mx-4 mt-7">
          a. 언제 그랬나요?
          <div className="mx-4 mt-3 flex h-12 items-center rounded-md border border-lightgray bg-lightblue p-5">
            {summary[1][1]}
          </div>
        </div>
        <div className="mx-4 mt-7">
          b. 어떤 상황이었나요?
          <div className="mx-4 mt-3 flex h-12 items-center rounded-md border border-lightgray bg-lightblue p-5">
            {summary[1][2]}
          </div>
        </div>
      </div>
      <div className="mt-7">
        <p>2. 원고는 누구인가요?</p>
        <div className="mx-4 mt-3 flex h-12 items-center rounded-md border border-lightgray bg-lightblue p-5">
          {summary[2]}
        </div>
      </div>
      <div className="mt-7">
        <p>3. 피고는 누구인가요?</p>
        <div className="mx-4 mt-3 flex h-12 items-center rounded-md border border-lightgray bg-lightblue p-5">
          {summary[3]}
        </div>
      </div>
      <div className="mt-7">
        <p>4. 왜 그랬나요?</p>
        <div className="mx-4 mt-3 flex h-12 items-center rounded-md border border-lightgray bg-lightblue p-5">
          {summary[4]}
        </div>
      </div>
      <div className="mt-7">
        <p>5. 어떤 행동을 취했나요?</p>
        <div className="mx-4 mt-3 flex h-12 items-center rounded-md border border-lightgray bg-lightblue p-5">
          {summary[5]}
        </div>
      </div>
      <div className="mt-7">
        <p>6. 원고는 누구인가요?</p>
        <div className="mx-4 mt-3 flex h-12 items-center rounded-md border border-lightgray bg-lightblue p-5">
          {summary[6]}
        </div>
      </div>
    </div>
  );
}
