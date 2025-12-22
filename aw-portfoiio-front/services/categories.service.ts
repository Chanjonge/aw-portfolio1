import api from "@/lib/axiosInstance";
import { Params } from "@/components/Pagination";

type BodyContent = {
  name: string;
  slug: string;
  order: number;
  id?: string;
};

export const CategoriesService = {
  get: (params: Params) => api.get("/api/category", { params }),

  post: (body: BodyContent) => api.post("/api/category", body),

  put: (body: BodyContent) => api.put("/api/category", body),

  delete: (id: string) => api.delete(`/api/category/${id}`),
};
