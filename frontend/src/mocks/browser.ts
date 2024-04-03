import { setupWorker } from "msw/browser";
import { memberHandlers } from "./memberHandlers";
import { precedentHandlers } from "./precedentHandler";

export const worker = setupWorker(...memberHandlers, ...precedentHandlers);
