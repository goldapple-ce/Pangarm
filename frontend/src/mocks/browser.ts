import { setupWorker } from "msw/browser";
import { memberHandlers } from "./memberHandlers";

export const worker = setupWorker(...memberHandlers);
