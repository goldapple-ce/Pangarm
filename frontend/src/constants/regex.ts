export const VALIDATE_REGEX = {
  NAME: /^[가-힣]+$/,
  PASSWORD: /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/,
  DATE: /\d{4}\.\s\d{1,2}\.\s\d{1,2}/g,
};
