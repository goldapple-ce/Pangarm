// import { Detail } from "@/types";

// type PrecedentDetailRawProps = {
//   detail: Detail;
// };

// export default function PrecedentDetailRaw({
//   detail,
// }: PrecedentDetailRawProps) {
//   return (
//     <div className="h-[70vh] flex-row overflow-y-auto">
//       <div>
//         <p> 1. 기초 정보 </p>
//         <div className="flex w-full p-5 text-xl">
//           <table className="w-full font-Content text-xl">
//             <tbody>
//               <tr className="justify text-center">
//                 <td className="w-1/4 border bg-lightblue p-2 font-bold">
//                   사건유형
//                 </td>
//                 <td className="w-1/4 border p-2">
//                   {detail.basicInformation.graph.category.incident}
//                 </td>
//                 <td className="w-1/4 border bg-lightblue p-2 font-bold">
//                   세부 유형
//                 </td>
//                 <td className="w-1/4 border p-2">
//                   {detail.basicInformation.graph.category.detail}
//                 </td>
//               </tr>
//               <tr className="justify text-center">
//                 <td className="w-1/4 border bg-lightblue p-2 font-bold">
//                   사건번호
//                 </td>
//                 <td className="w-1/4 border p-2">
//                   {detail.basicInformation.graph.caseNumber}
//                 </td>
//                 <td className="w-1/4 border bg-lightblue p-2 font-bold">
//                   법원명
//                 </td>
//                 <td className="w-1/4 border p-2">
//                   {detail.basicInformation.graph.courthouse}
//                 </td>
//               </tr>
//               <tr className="justify p-2 text-center">
//                 <td className="w-1/4 border bg-lightblue p-2 font-bold">
//                   사건명
//                 </td>
//                 <td className="w-1/4 border p-2" colSpan={3}>
//                   {detail.basicInformation.graph.caseName}
//                 </td>
//               </tr>
//               <tr className="justify text-center">
//                 <td className="w-1/4 border bg-lightblue p-2 font-bold">
//                   판결선고일
//                 </td>
//                 <td className="w-1/4 border p-2">
//                   {detail.basicInformation.graph.judgementDate}
//                 </td>
//                 <td className="w-1/4 border bg-lightblue p-2 font-bold">
//                   심급유형
//                 </td>
//                 <td className="w-1/4 border p-2">
//                   {detail.basicInformation.graph.instanceType}
//                 </td>
//               </tr>
//             </tbody>
//           </table>
//         </div>

//         <div>
//           관련법령
//           <div className=" flex h-full rounded border border-lightgray bg-lightblue">
//             {/* {detail.basicInformation.relatedLawList.map((value) => (
//               <a key={value.law} href={value.link}>
//                 {" "}
//                 {value.law}{" "}
//               </a>
//             ))} */}
//           </div>
//         </div>
//         <div>
//           인용판례
//           <div className="flex h-full rounded border border-lightgray bg-lightblue">
//             {/* {detail.basicInformation.citedPrecedent.map((value) => (
//               <a key={value.precedent} href={value.link}>
//                 {" "}
//                 {value.precedent}{" "}
//               </a>
//             ))} */}
//           </div>
//         </div>
//       </div>
//       <div>
//         2. 사건 관계자
//         <table className="w-full font-Content text-xl">
//           <tbody>
//             <tr className="justify text-center">
//               <td className="w-1/4 border bg-lightblue p-2 font-bold">원고</td>
//               <td className="w-1/4 border p-2">{detail.part.plaintiff}</td>
//               <td className="w-1/4 border bg-lightblue p-2 font-bold">피고</td>
//               <td className="w-1/4 border p-2">{detail.part.defendant}</td>
//             </tr>
//           </tbody>
//         </table>
//       </div>
//       <div>
//         3. 원심 판결
//         <table className="w-full font-Content text-xl">
//           <tbody>
//             <tr className="justify text-center">
//               <td className="w-1/4 border bg-lightblue p-2 font-bold">
//                 원심사건번호
//               </td>
//               <td className="w-1/4 border p-2">
//                 {detail.originalJudgment.caseNumber}
//               </td>
//             </tr>
//             <tr className="justify text-center">
//               <td className="w-1/4 border bg-lightblue p-2 font-bold">
//                 원심법원명
//               </td>
//               <td className="w-1/4 border p-2">
//                 {detail.originalJudgment.courthouse}
//               </td>
//               <td className="w-1/4 border bg-lightblue p-2 font-bold">
//                 원심선고일
//               </td>
//               <td className="w-1/4 border p-2">
//                 {detail.originalJudgment.judgementDate}
//               </td>
//             </tr>
//           </tbody>
//         </table>
//       </div>
//       <div>
//         4. 처분
//         <table className="w-full font-Content text-xl">
//           <tbody>
//             <tr className="justify text-center">
//               <td className="w-1/4 border bg-lightblue p-2 font-bold">
//                 처분 종류
//               </td>
//               <td className="w-1/4 border p-2">{detail.disposal.type}</td>
//               <td className="w-1/4 "></td>
//               <td className="w-1/4"></td>
//             </tr>
//           </tbody>
//         </table>
//         처분내용
//         <div className="flex h-full rounded border border-lightgray bg-lightblue">
//           {detail.disposal.content}
//         </div>
//       </div>
//       <div>
//         5. 취지 청구 취지 및 항소 취지
//         <div className="flex h-full rounded border border-lightgray bg-lightblue">
//           {detail.disposal.content}
//         </div>
//       </div>
//       <div>
//         6. 주장 원고 주장
//         <div className="flex h-full rounded border border-lightgray bg-lightblue">
//           {detail.disposal.content}
//         </div>
//         피고 주장
//         <div className="flex h-full rounded border border-lightgray bg-lightblue">
//           {detail.disposal.content}
//         </div>
//       </div>
//       <div>
//         7. 사실 기초 사실
//         <div className="flex h-full rounded border border-lightgray bg-lightblue">
//           {detail.disposal.content}
//         </div>
//       </div>
//       <div>
//         8. 취지 재판부의 판단
//         <div className="flex h-full rounded border border-lightgray bg-lightblue">
//           {detail.disposal.content}
//         </div>
//       </div>
//       <div>
//         9. 결론 재판의 결론
//         <div className="flex h-full rounded border border-lightgray bg-lightblue">
//           {detail.disposal.content}
//         </div>
//       </div>
//     </div>
//   );
// }
