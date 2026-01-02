## 개발환경

### Frontend - [package.json](package.json)

| 항목              | 버전 / 설명                         |
|-----------------|---------------------------------|
| Next.js         | 14.2.5                          |
| React/React DOM | 18.3.1                          |
| Tailwind        | 3.4.7                           |
| Recoil          | 0.7.7                           |
| Axios           | 1.13.2                          |

---

## 개발환경 실행방법

### Frontend
```bash
cd aw-portfoiio-front
npm install
npm run dev
```

## 디렉토리 구조

### API 요청 – [services](services)

- [auth.service.ts](services/auth.service.ts)
- [categories.service.ts](services/categories.service.ts)
- [member.service.ts](services/member.service.ts)
- [portfolios.service.ts](services/portfolios.service.ts)
- [question.service.ts](services/question.service.ts)
- [submission.service.ts](services/submission.service.ts)
- [tokenStore.ts](services/tokenStore.ts)
- [user.service.ts](services/user.service.ts)  

### 타입 정의 – [types](types)

- [category.ts](types/category.ts)
- [member.ts](types/member.ts)
- [portfolio.ts](types/portfolio.ts)
- [question.ts](types/question.ts)
- [submission.ts](types/submission.ts)
- [userList.ts](types/userList.ts)  

### 참고사항

## specials 글자수 제한 - [page](app/portfolio/[id]/page.tsx)
위 페이지에서 sp.desc.length, sp.desc.trim().length 숫자를 조정

```ts
<p className={`text-xs mt-1 text-right ${sp.desc.length < 20 ? "text-red-500" : "text-gray-500" }`}>
!sp.name?.trim() || !sp.desc?.trim() || sp.desc.trim().length < 20,
if (sp.desc.trim().length < 20) return true;
````
## 임시저장 자동저장 시간 조절 - [page](app/portfolio/[id]/page.tsx)
위 페이지에서 startAutoSave 내의 시간 조정(ex.180000)
```ts
  const startAutoSave = () => {
    if (autoSaveTimeoutRef.current) {
      clearTimeout(autoSaveTimeoutRef.current);
    }

    autoSaveTimeoutRef.current = setTimeout(() => {
      setShouldAutoSave(true); // 트리거
      startAutoSave(); // 다음 타이머 예약
    }, 180000);
  };
````
