import { Params } from "@/components/Pagination";
import api from "@/lib/axiosInstance";

export const QuestionService = {
  get: (id: string) => api.get(`/api/question/${id}`),

  //포토폴리오 질문 목록
  getPortfolios: (id: string) => api.get(`/api/portfolios/${id}`),

  post: (body: FormData) => api.post("/api/question", body),

  put: (body: FormData) => api.put("/api/question", body),

  delete: (id: string) => api.delete(`/api/question/${id}`),
};
