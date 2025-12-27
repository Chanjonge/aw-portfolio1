import api from "@/lib/axiosInstance";

interface Params {
    companyName: string;
    password: string;
}

export const SubmissionService = {
    get: (id: string) => api.get(`/api/submission/${id}`),

    temporaryPost: (body: FormData) => api.post("/api/submission/temporaryStorage", body),

    post: (body: FormData) => api.post("/api/submission", body),

    getMyList: (params: Params) =>
        api.post(
            "/api/submission/my-list",
            null,
            { params }
        ),

    delete: (id: string) => api.delete(`/api/question/${id}`),
};
