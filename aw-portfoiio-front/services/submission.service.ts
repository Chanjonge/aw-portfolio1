import { Params } from "@/components/Pagination";
import api from "@/lib/axiosInstance";

export const SubmissionService = {
    get: (id: string) => api.get(`/api/submission/${id}`),

    temporaryPost: (body: FormData) => api.post("/api/submission/temporaryStorage", body),

    post: (body: FormData) => api.put("/api/submission", body),

    delete: (id: string) => api.delete(`/api/question/${id}`),
};
