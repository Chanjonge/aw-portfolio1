export interface Submission {
    id: string;
    portfolioId: string;
    companyName: string;
    password: string;
    isDraft: boolean;
    completedAt: string;
    updatedAt: string;
    responses: any;
    portfolio: {
        title: string;
        slug: string;
    };
}


