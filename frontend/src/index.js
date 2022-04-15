import React, { Suspense } from "react";
import App from "./App";
import Loader from "./components/Loader/Loader";

import { BrowserRouter } from "react-router-dom";
import { QueryClientProvider } from "react-query";
import { createRoot } from "react-dom/client";
import { reactQueryClient } from "./api/ReactQueryConfig";

import "./index.css";
import { ErrorBoundary } from "react-error-boundary";

const container = document.getElementById("root");
const root = createRoot(container);

root.render(
  <QueryClientProvider client={reactQueryClient}>
    <BrowserRouter>
      <ErrorBoundary fallback={<h1>Error...</h1>}>
        <Suspense fallback={<Loader />}>
          <App />
        </Suspense>
      </ErrorBoundary>
    </BrowserRouter>
  </QueryClientProvider>
);
