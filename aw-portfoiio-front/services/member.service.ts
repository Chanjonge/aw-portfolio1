import {Params} from "@/components/Pagination";
import api from "@/lib/axiosInstance";

export const MemberService = {
    get: (params: Params) => api.get("/api/members", { params }),

    // post: (body: BodyContent) => api.post("/api/category", body),
    //
    // put: (body: BodyContent) => api.put("/api/category", body),
    //
    // delete: (id: string) => api.delete(`/api/category/${id}`),
};
