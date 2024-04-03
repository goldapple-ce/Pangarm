import { PrecedentDetailSummaryType } from "@/types";
import PrecedentDetailBox from "./PrecedentDetailBox";
import React from "react";

type PrecedentDetailSummaryProps = {
  precedentSummary: PrecedentDetailSummaryType;
};

export default function PrecedentDetailSummary({
  precedentSummary,
}: PrecedentDetailSummaryProps) {
  return (
    <div className=" m-10 flex h-[70vh] flex-col overflow-y-auto pb-20 font-TitleLight text-lg ">
      <div>
        <p className="mt-2 font-TitleMedium text-xl"> 1. 기초 정보 </p>
        <div className="flex w-full p-5 text-xl">
          <table className="w-full font-Content text-xl">
            <tbody>
              <tr className="justify text-center">
                <td className="w-1/4 border bg-lightblue p-2 font-bold">
                  사건번호
                </td>
                <td className="w-1/4 border p-2">
                  {precedentSummary.info.caseNumber}
                </td>
                <td className="w-1/4 border bg-lightblue p-2 font-bold">
                  사건유형
                </td>
                <td className="w-1/4 border p-2">
                  {precedentSummary.info.type.incident}
                </td>
              </tr>
              <tr className="justify text-center">
                <td className="w-1/4 border bg-lightblue p-2 font-bold">
                  평결
                </td>
                <td className="w-1/4 border p-2">
                  {precedentSummary.info.type.verdict}
                </td>
                <td className="w-1/4 border bg-lightblue p-2 font-bold">
                  법원명
                </td>
                <td className="w-1/4 border p-2">
                  {precedentSummary.info.type.courtName}
                </td>
              </tr>
              <tr className="justify p-2 text-center">
                <td className="w-1/4 flex-wrap border bg-lightblue p-2 font-bold">
                  사건명
                </td>
                <td className="w-1/4 border p-2" colSpan={3}>
                  <p className="flex w-full justify-center text-center ">
                    {precedentSummary.info.caseName}
                  </p>
                </td>
              </tr>
              <tr className="justify text-center">
                <td className="w-1/4 border bg-lightblue p-2 font-bold">
                  판결선고일
                </td>
                <td className="w-1/4 border p-2">
                  {precedentSummary.info.judgementDate}
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div>
          <p className="ms-4 mt-1 font-bold">요약</p>
          <div className="m-2 mx-5 flex h-full rounded border border-lightgray bg-lightblue p-5">
            {precedentSummary.summary}
          </div>
        </div>
        <div>
          <p className="ms-4 mt-1 font-bold">관련법령</p>
          <div className="m-2 mx-5 flex h-full rounded border border-lightgray bg-lightblue p-5">
            {precedentSummary.relate.lawList &&
              precedentSummary.relate.lawList.map(({ lawName }, index) => (
                <React.Fragment key={lawName}>
                  {`${index > 0 ? " / " : ""}${lawName}`}{" "}
                </React.Fragment>
              ))}
          </div>
        </div>
        <div>
          <p className="ms-4 font-bold">인용판례</p>
          <div className="m-2 mx-5 flex h-full rounded border border-lightgray bg-lightblue p-5">
            {precedentSummary.relate.precedentList ? (
              precedentSummary.relate.precedentList.map(
                ({ caseNumber }, index) => (
                  <p
                    aria-label={caseNumber}
                    key={caseNumber}
                  >{`${index > 0 ? " / " : ""}${caseNumber} `}</p>
                ),
              )
            ) : (
              <p>인용 판례가 존재하지 않습니다.</p>
            )}
          </div>
        </div>
      </div>
      <div>
        <p className="mt-2 font-TitleMedium text-xl">2. 사건 관계자</p>
        <div className="p-5">
          <table className="w-full font-Content text-xl">
            <tbody>
              <tr className="justify text-center">
                <td className="w-1/4 border bg-lightblue p-2 font-bold">
                  원고
                </td>
                <td className="w-1/4 border p-2">
                  {precedentSummary.parties.plaintiff
                    ? `${precedentSummary.parties.plaintiff}`
                    : "원고인"}
                </td>
                <td className="w-1/4 border bg-lightblue p-2 font-bold">
                  피고
                </td>
                <td className="w-1/4 border p-2">
                  {precedentSummary.parties.defendant
                    ? `${precedentSummary.parties.defendant}`
                    : "피고인"}
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div>
        <p className="mt-2 font-TitleMedium text-xl">3. 원심 판결</p>
        <div className="p-5">
          <table className="w-full font-Content text-xl">
            <tbody>
              <tr className="justify text-center">
                <td
                  className="w-1/4 border bg-lightblue p-2 font-bold"
                  colSpan={1}
                >
                  원심사건
                </td>
                <td className="w-1/4 border p-2" colSpan={3}>
                  {precedentSummary.originalJudgement.caseNumber
                    ? `${precedentSummary.originalJudgement.caseNumber}`
                    : "존재하지 않음"}
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div>
        <p className="mt-2 font-TitleMedium text-xl">4. 처분</p>
        <PrecedentDetailBox content={precedentSummary.purport} />
      </div>
      <div>
        <p className="mt-2 font-TitleMedium text-xl">5. 사실</p>
        <PrecedentDetailBox content={precedentSummary.fact} />
      </div>
      <div>
        <p className="mt-2 font-TitleMedium text-xl">6. 주장</p>
        <p className="ms-4 mt-1 font-bold">원고 주장</p>
        <PrecedentDetailBox
          content={
            precedentSummary.opinion.defendant
              ? precedentSummary.opinion.defendant
              : ""
          }
        />
        <p className="ms-4 mt-1 font-bold">피고 주장</p>
        <PrecedentDetailBox
          content={
            precedentSummary.opinion.plaintiff
              ? precedentSummary.opinion.plaintiff
              : ""
          }
        />
      </div>
      <div>
        <p className="mt-2 font-TitleMedium text-xl"> 7. 판결</p>
        <PrecedentDetailBox content={precedentSummary.judgement} />
      </div>
      <div>
        <p className="mt-2 font-TitleMedium text-xl">8. 결론</p>
        <PrecedentDetailBox content={precedentSummary.conclusion} />
      </div>
    </div>
  );
}
