export interface QuestionForm {
  portfolioId: string;
  step: number;
  title: string;
  description: string;
  thumbnail: string;
  minLength: number;
  maxLength: number;
  requireMinLength: boolean;
  order: number;
  isRequired: boolean;
  questionType: string;
  options: string;
  thumbnailFile: File | null;
}
