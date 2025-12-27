

export interface MemberContent {
    id: string;
    companyName: string;
    password: string; // 평문 비밀번호 (식별번호)
    lastLoginAt: string | null; // null일 수 있음
    createdAt: string;
    updatedAt: string;
    ipAddress?: string;
    loginCount: number;
}

export interface Member {
    content: MemberContent[];
    totalPages: number;
    totalElements: number;
    number: number;
    size: number;
    first: boolean;
    last: boolean;
    empty: boolean;
}
