import { Detail } from "@/types";
import PrecedentDetailRawTextbox from "./PrecedentDetailRawTextbox";

type PrecedentDetailRawProps = {
  detail: Detail;
};

export default function PrecedentDetailRaw({
  detail,
}: PrecedentDetailRawProps) {
  return (
    <div className="m-5 h-[70vh] flex-row overflow-y-auto font-Content">
      <div>
        <p className=" text-3xl"> 1. 기초 정보 </p>
        <div className="flex w-full p-5 text-xl">
          <table className="w-full font-Content text-xl">
            <tbody>
              <tr className="justify text-center">
                <td className="w-1/4 border bg-lightblue p-2 font-bold">
                  사건유형
                </td>
                <td className="w-1/4 border p-2">
                  {detail.basicInformation.graph.category.incident}
                </td>
                <td className="w-1/4 border bg-lightblue p-2 font-bold">
                  세부 유형
                </td>
                <td className="w-1/4 border p-2">
                  {detail.basicInformation.graph.category.detail}
                </td>
              </tr>
              <tr className="justify text-center">
                <td className="w-1/4 border bg-lightblue p-2 font-bold">
                  사건번호
                </td>
                <td className="w-1/4 border p-2">
                  {detail.basicInformation.graph.caseNumber}
                </td>
                <td className="w-1/4 border bg-lightblue p-2 font-bold">
                  법원명
                </td>
                <td className="w-1/4 border p-2">
                  {detail.basicInformation.graph.courthouse}
                </td>
              </tr>
              <tr className="justify p-2 text-center">
                <td className="w-1/4 border bg-lightblue p-2 font-bold">
                  사건명
                </td>
                <td className="w-1/4 border p-2" colSpan={3}>
                  {detail.basicInformation.graph.caseName}
                </td>
              </tr>
              <tr className="justify text-center">
                <td className="w-1/4 border bg-lightblue p-2 font-bold">
                  판결선고일
                </td>
                <td className="w-1/4 border p-2">
                  {detail.basicInformation.graph.judgementDate}
                </td>
                <td className="w-1/4 border bg-lightblue p-2 font-bold">
                  심급유형
                </td>
                <td className="w-1/4 border p-2">
                  {detail.basicInformation.graph.instanceType}
                </td>
              </tr>
            </tbody>
          </table>
        </div>

        <div className="m-5">
          <p className=" text-xl font-bold">관련법령</p>
          <PrecedentDetailRawTextbox>
            {detail.basicInformation.relatedLawList.map((value) => (
              <a key={value.law} href={value.link}>
                {" "}
                {value.law}{" "}
              </a>
            ))}
          </PrecedentDetailRawTextbox>
        </div>
        <div className="m-5">
          <p className=" text-xl font-bold"> 인용판례 </p>
          <PrecedentDetailRawTextbox>
            {detail.basicInformation.citedPrecedent.map((value) => (
              <a key={value.precedent} href={value.link}>
                {" "}
                {value.precedent}{" "}
              </a>
            ))}
          </PrecedentDetailRawTextbox>
        </div>
      </div>
      <div>
        <p className=" mt-5 text-3xl"> 2. 사건 관계자</p>
        <table className="m-5 w-full text-xl">
          <tbody>
            <tr className="justify text-center">
              <td className="w-1/4 border bg-lightblue p-2 font-bold">원고</td>
              <td className="w-1/4 border p-2">{detail.part.plaintiff}</td>
              <td className="w-1/4 border bg-lightblue p-2 font-bold">피고</td>
              <td className="w-1/4 border p-2">{detail.part.defendant}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div>
        <p className=" mt-5 text-3xl"> 3. 원심 판결</p>
        <table className="m-5 w-full text-xl">
          <tbody>
            <tr className="justify text-center">
              <td className="w-1/4 border bg-lightblue p-2 font-bold">
                원심사건번호
              </td>
              <td className="w-1/4 border p-2">
                {detail.originalJudgment.caseNumber}
              </td>
            </tr>
            <tr className="justify text-center">
              <td className="w-1/4 border bg-lightblue p-2 font-bold">
                원심법원명
              </td>
              <td className="w-1/4 border p-2">
                {detail.originalJudgment.courthouse}
              </td>
              <td className="w-1/4 border bg-lightblue p-2 font-bold">
                원심선고일
              </td>
              <td className="w-1/4 border p-2">
                {detail.originalJudgment.judgementDate}
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div>
        <p className=" mt-5 text-3xl"> 4. 처분</p>
        <table className="m-5 w-full font-Content text-xl">
          <tbody>
            <tr className="justify text-center">
              <td className="w-1/4 border bg-lightblue p-2 font-bold">
                처분 종류
              </td>
              <td className="w-1/4 border p-2">{detail.disposal.type}</td>
              <td className="w-1/4 "></td>
              <td className="w-1/4"></td>
            </tr>
          </tbody>
        </table>
        <div className="m-5">
          <p className=" text-xl font-bold">처분내용 </p>
          <PrecedentDetailRawTextbox>
            {detail.disposal.content}
          </PrecedentDetailRawTextbox>
        </div>
      </div>
      <div>
        <p className=" mt-5 text-3xl"> 5. 취지</p>
        <p className=" mx-2 mt-2 text-xl font-bold"> 청구 취지 및 항소 취지</p>
        <PrecedentDetailRawTextbox>{detail.purport}</PrecedentDetailRawTextbox>
      </div>
      <div>
        <p className=" mt-5 text-3xl"> 6. 주장</p>
        <div className="mx-5">
          <p className="mx-2 mt-2  text-xl font-bold">원고 주장</p>
          <PrecedentDetailRawTextbox>
            {detail.opinion.plaintiff}
          </PrecedentDetailRawTextbox>
        </div>
      </div>
      <div className="m-5">
        <p className="mx-2 mt-2  text-xl font-bold"> 피고 주장</p>
        <PrecedentDetailRawTextbox>
          {detail.opinion.defendant}
        </PrecedentDetailRawTextbox>
      </div>
      <div>
        <p className=" mt-5 text-3xl"> 7. 사실</p>
        <p className=" mx-2 mt-2 text-xl font-bold"> 기초 사실</p>
        <PrecedentDetailRawTextbox>{detail.fact}</PrecedentDetailRawTextbox>
      </div>
      <div>
        <p className=" mt-5 text-3xl"> 8. 취지 </p>
        <p className=" mx-2 mt-2 text-xl font-bold">재판부의 판단</p>
        <PrecedentDetailRawTextbox>
          {detail.judgement}
        </PrecedentDetailRawTextbox>
      </div>
      <div>
        <p className=" mt-5 text-3xl"> 9. 결론 </p>
        <p className=" mx-2 mt-2 text-xl font-bold">재판의 결론</p>
        <PrecedentDetailRawTextbox>{detail.result}</PrecedentDetailRawTextbox>
      </div>
    </div>
  );
}
