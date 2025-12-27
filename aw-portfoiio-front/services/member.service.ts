import {Params} from "@/components/Pagination";
import api from "@/lib/axiosInstance";

export const MemberService = {
    get: (params: Params) => api.get("/api/members", { params }),

    post: (data: { memberId: string; password: string }) => api.post(`/api/members` , data),
    //
    // put: (body: BodyContent) => api.put("/api/category", body),
    //
    delete: (id: string) => api.delete(`/api/members/${id}`),
};
