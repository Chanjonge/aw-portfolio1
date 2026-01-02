import api from "@/lib/axiosInstance";

interface ExcelParams {
  portfolioId: string;
  submissionId: string;
}

export const SubmissionService = {
  get: (id: string) => api.get(`/api/submission/${id}`),

  adminGet: () => api.get(`/api/admin-submissions`),

  adminExcelGet(body: ExcelParams) {
    return api.post(`/api/excel`, body, { responseType: "blob" });
  },

  fileGet(fileName: string) {
    return api.get(`/api/download/${fileName}`, { responseType: "blob" });
  },

  temporaryPost: (body: FormData) =>
    api.post("/api/submission/temporaryStorage", body),

  post: (body: FormData) => api.post("/api/submission", body),

  getMyList: () => api.post("/api/submission/my-list"),

  submissionOffPost: (body: ExcelParams) => api.post("/api/submitOff", body),

  delete: (id: string) => api.delete(`/api/admin-submissions/${id}`),
};
