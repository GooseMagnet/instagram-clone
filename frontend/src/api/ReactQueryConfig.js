import { QueryClient } from "react-query";

export const reactQueryClient = new QueryClient({
  defaultOptions: {
    queries: {
      suspense: true,
    },
  },
});
