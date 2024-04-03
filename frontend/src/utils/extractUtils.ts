/**
 * @example
 * ```
 * // 입력
 * 소유권이전등기 [대법원 2023. 12. 7. 선고 2023다269139판결]
 * // 출력
 * 2023. 12. 7
 * ```
 * @param content
 * @returns string
 */
export const extractDate = (content: string) =>
  content.match(/\d{4}\.\s\d{1,2}\.\s\d{1,2}/g);
