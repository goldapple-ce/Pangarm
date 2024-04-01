/**
 * @example
 * ```ts
 * const dateTime = "2024-03-28T10:53:00.000+00:00";
 *
 * // 03월 28일 10:53
 * console.log(formatDateWithoutYear(dateTime))
 * ```
 */
export const formatDateWithoutYear = (dateTime: string) => {
  const formatter = new Intl.DateTimeFormat("ko-KR", {
    month: "2-digit",
    day: "2-digit",
    hour: "2-digit",
    minute: "2-digit",
    hourCycle: "h23",
  });

  const date = new Date(dateTime);
  const parts = formatter.formatToParts(date);

  const month = parts.find((part) => part.type === "month")?.value;
  const day = parts.find((part) => part.type === "day")?.value;
  const hour = parts.find((part) => part.type === "hour")?.value;
  const minute = parts.find((part) => part.type === "minute")?.value;

  const result = `${month}월 ${day}일 ${hour}:${minute}`;

  return result;
};

/**
 * @example
 * ```ts
 * const dateTime = "2024-03-28T10:53:00.000+00:00";
 *
 * // 2024. 03. 28. 오전 10:53
 * console.log(formatDateWithYear(dateTime))
 * ```
 */
export const formatDateWithYear = (dateTime: string) => {
  const date = new Date(dateTime);

  const formatter = new Intl.DateTimeFormat("ko-KR", {
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
    hour: "2-digit",
    minute: "2-digit",
    hour12: true,
  });

  const result = formatter.format(date);

  return result;
};
