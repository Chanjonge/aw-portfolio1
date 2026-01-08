'use client';

import Link from 'next/link';
import { useState, useEffect, useCallback } from 'react';
import { useRouter } from 'next/navigation';
import { AuthService } from '@/services/auth.service';
import { tokenStore } from '@/services/tokenStore';
import { useRequest } from '@/hooks/useRequest';
import { userState } from '@/store/user';
import { useRecoilValue, useSetRecoilState } from 'recoil';
import axios from 'axios';
import api from '@/lib/axiosInstance';
import { PortfolioService } from '@/services/portfolios.service';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation, Pagination, Autoplay } from 'swiper/modules';
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';

interface Category {
    id: string;
    name: string;
    slug: string;
    order: number;
    _count?: { portfolios: number };
}

interface Portfolio {
    id: string;
    title: string;
    description: string;
    slug: string;
    thumbnail?: string;
    domain?: string; // 미리보기용 도메인 URL
    categoryId?: string;
    category?: Category;
    isActive: boolean;
    order: number;
    _count: {
        questions: number;
        submissions: number;
    };
}

export default function Home() {
    const router = useRouter();

    //hooks
    const { request } = useRequest();

    //유저정보
    const currentUser = useRecoilValue(userState);
    const setUser = useSetRecoilState(userState);

    // 일반 상태
    const [categories, setCategories] = useState<Category[]>([]);
    const [portfolios, setPortfolios] = useState<Portfolio[]>([]);
    const [selectedCategory, setSelectedCategory] = useState<string | null>(null);
    const [loading, setLoading] = useState(true);

    // 미리보기 팝업 상태
    const [showPreview, setShowPreview] = useState(false);
    // 포토폴리오 아이디
    const [previewPortfolioId, setPreviewPortfolioId] = useState<string>('');
    const [previewUrl, setPreviewUrl] = useState<string>('');
    const [previewTitle, setPreviewTitle] = useState<string>('');
    const [previewMode, setPreviewMode] = useState<'desktop' | 'mobile'>('desktop'); // ⬅ 추가
    const [proxyError, setProxyError] = useState<string>(''); // 프록시 오류 상태
    const [isPreviewLoading, setIsPreviewLoading] = useState<boolean>(false); // 미리보기 로딩 상태

    // ESC 키로 팝업 닫기 + 스크롤 잠금/복원
    useEffect(() => {
        const handleKeyDown = (event: KeyboardEvent) => {
            if (event.key === 'Escape' && showPreview) setShowPreview(false);
        };
        const prevOverflow = document.body.style.overflow;

        if (showPreview) {
            document.addEventListener('keydown', handleKeyDown);
            document.body.style.overflow = 'hidden';
        }
        return () => {
            document.removeEventListener('keydown', handleKeyDown);
            document.body.style.overflow = prevOverflow || '';
        };
    }, [showPreview]);

    // 인증 상태

    // 초기 로드
    useEffect(() => {
        fetchCategories();
    }, []);

    // 카테고리/포트폴리오
    const fetchCategories = async () => {
        await request(
            () => PortfolioService.getCategorySelect(),
            (res) => {
                setCategories(res.data);
            },
            { ignoreErrorRedirect: true }
        );
    };

    const fetchPortfolios = useCallback(async () => {
        try {
            setLoading(true);

            await request(
                () => PortfolioService.getUser(true, selectedCategory ?? null),
                (res) => {
                    setPortfolios(res.data);
                },
                { ignoreErrorRedirect: true }
            );
        } catch (error) {
            console.error('Failed to fetch portfolios:', error);
        } finally {
            setLoading(false);
        }
    }, [selectedCategory]);

    useEffect(() => {
        fetchPortfolios();
    }, [fetchPortfolios]);

    // 인증 초기화
    const handleClearAuth = async () => {
        await axios.delete('/api/refresh', {
            baseURL: api.defaults.baseURL,
            withCredentials: true,
        });

        tokenStore.clear();
        setUser(null);
        localStorage.removeItem('login');

        localStorage.removeItem('portfolio_auth');
    };

    // 프록시 URL 생성 함수
    const getProxyUrl = (originalUrl: string) => {
        const API_BASE = process.env.NEXT_PUBLIC_API_BASE_URL;

        try {
            const url = new URL(originalUrl);
            // HTTPS 사이트는 직접 사용
            if (url.protocol === 'https:') {
                return originalUrl;
            }
            // HTTP 사이트는 프록시를 통해 사용
            return `${API_BASE}/api/proxy?url=${encodeURIComponent(originalUrl)}`;
        } catch (error) {
            console.error('Invalid URL:', originalUrl);
            return originalUrl;
        }
    };

    // 미리보기 열기 함수
    const handlePreviewOpen = async (domain: string, title: string, id: string) => {
        setProxyError('');
        setIsPreviewLoading(true);
        setPreviewPortfolioId(id);
        const proxyUrl = getProxyUrl(domain);
        setPreviewUrl(proxyUrl);
        setPreviewTitle(title);
        setPreviewMode('desktop');
        setShowPreview(true);

        setIsPreviewLoading(false);
    };

    return (
        <div className="min-h-screen bg-white">
            {/* Header */}
            <header className="bg-white border-gray-200">
                <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-4">
                    <div className="flex justify-between items-center gap-2 md:gap-1 md:flex-row flex-col">
                        <a href="/" className="block">
                            <h1 className="text-2xl font-bold text-black">
                                <img src="/logo.png" alt="로고" className="h-8" />
                            </h1>
                        </a>
                        <div className="flex items-center gap-2 md:gap-4">
                            {/* 상호명 표시 (비관리자 인증시) */}
                            {currentUser && currentUser?.role !== 'SUPER_ADMIN' && (
                                <div className="flex items-center gap-2 mr-4">
                                    <span className="font-semibold text-black text-sm">상호명: {currentUser?.email}</span>
                                    <button onClick={handleClearAuth} className="text-xs text-gray-500 hover:text-gray-700 underline">
                                        로그아웃
                                    </button>
                                </div>
                            )}

                            {currentUser && currentUser?.role !== 'USER' ? (
                                <>
                                    <span className="text-gray-600">
                                        {currentUser.name}님 ({currentUser.role === 'SUPER_ADMIN' ? '최고 관리자' : '관리자'})
                                    </span>
                                    <button onClick={() => router.push(currentUser.role === 'SUPER_ADMIN' ? '/admin/super' : '/admin/dashboard')} className="text-xs md:text-base px-2 md:px-4 py-2 bg-black text-white rounded-lg font-semibold hover:bg-gray-800 transition-all">
                                        관리자 페이지
                                    </button>
                                    <button onClick={handleClearAuth} className="text-xs md:text-base px-2 md:px-4 py-2 bg-white text-black border-2 border-black rounded-lg font-semibold hover:bg-black hover:text-white transition-all">
                                        로그아웃
                                    </button>
                                </>
                            ) : currentUser ? (
                                <button
                                    onClick={() => {
                                        const targetPath = `/my-submissions`;

                                        // 비로그인시
                                        if (!currentUser) {
                                            router.push(`/login?redirect=${encodeURIComponent(targetPath)}`);
                                            return;
                                        }

                                        // 로그인 상태면 바로 이동
                                        router.push(targetPath);
                                    }}
                                    className="px-4 py-2 border border-gray-300 rounded-lg font-semibold hover:bg-black hover:text-white transition-all"
                                >
                                    작성 내역 불러오기
                                </button>
                            ) : (
                                <button
                                    onClick={() => {
                                        const targetPath = `/my-submissions`;

                                        router.push(`/login?redirect=${encodeURIComponent(targetPath)}`);
                                    }}
                                    className="px-4 py-2 border border-gray-300 rounded-lg font-semibold hover:bg-black hover:text-white transition-all"
                                >
                                    로그인
                                </button>
                            )}
                        </div>
                    </div>
                </div>
            </header>

            <div className="max-w-7xl mx-auto px-4 sm:px-6 py-12">
                {/* Swiper Slide */}
                <div className="mb-10 md:mb-18">
                    <Swiper modules={[Navigation, Pagination, Autoplay]} navigation pagination={{ clickable: true }} autoplay={{ delay: 3000, disableOnInteraction: false }} loop={true} className="w-full rounded-md h-[200px] md:h-[400px] lg:h-[500px]">
                        <SwiperSlide className="relative">
                            <img src="/slide1.jpg" alt="슬라이드 1" className="w-full h-full object-cover" />
                            <div className="absolute inset-0 flex flex-col items-center justify-center text-center px-4 bg-black/30">
                                <h3 className="text-white text-2xl md:text-4xl lg:text-4xl line-height-1.5 font-bold mb-3 md:mb-4">
                                    복잡한 제작은 덜고
                                    <br />
                                    타입으로 쉽게 시작해 보세요
                                </h3>
                                <p className="text-white text-sm md:text-lg">전문 지식 없이도 선택만으로 홈페이지 제작이 가능합니다.</p>
                            </div>
                        </SwiperSlide>
                        <SwiperSlide className="relative">
                            <img src="/slide2.jpg" alt="슬라이드 2" className="w-full h-full object-cover" />
                            <div className="absolute inset-0 flex flex-col items-center justify-center text-center px-4 bg-black/30">
                                <h3 className="text-white text-2xl md:text-4xl lg:text-4xl line-height-1.5 font-bold mb-3 md:mb-4">
                                    숙소에 맞게 미리 준비된
                                    <br />
                                    타입으로 만들어 보세요
                                </h3>
                                <p className="text-white text-sm md:text-lg">전문 지식 없이도 선택만으로 홈페이지 제작이 가능합니다.</p>
                            </div>
                        </SwiperSlide>
                        <SwiperSlide className="relative">
                            <img src="/slide3.jpg" alt="슬라이드 3" className="w-full h-full object-cover" />
                            <div className="absolute inset-0 flex flex-col items-center justify-center text-center px-4 bg-black/30">
                                <h3 className="text-white text-2xl md:text-4xl lg:text-4xl line-height-1.5 font-bold mb-3 md:mb-4">
                                    브랜드를 담기 쉬운 방법
                                    <br />
                                    타입 기반 홈페이지 제작
                                </h3>
                                <p className="text-white text-sm md:text-lg">전문 지식 없이도 선택만으로 홈페이지 제작이 가능합니다.</p>
                            </div>
                        </SwiperSlide>
                    </Swiper>
                </div>

                {/* Category Filter */}
                {categories.length > 0 && (
                    <div className="mb-8 mt-12">
                        <div className="flex justify-center gap-1 md:gap-3 flex-wrap">
                            <button onClick={() => setSelectedCategory(null)} className={`rounded-md px-3 md:px-6 py-1 text-base font-semibold transition-all ${selectedCategory === null ? 'bg-[#1C1C1E] text-white' : 'bg-white text-black border-black hover:bg-black hover:text-white'}`}>
                                전체
                            </button>

                            {categories.map((category) => {
                                // "고급형" 카테고리 특별 스타일 적용
                                const isPremium = category.name === '고급형';

                                if (isPremium) {
                                    return (
                                        <button
                                            key={category.id}
                                            onClick={() => setSelectedCategory(category.id)}
                                            className={`rounded-lg px-3 md:px-6 py-1 text-base font-bold transition-all ${
                                                selectedCategory === category.id
                                                    ? 'bg-gradient-to-r from-amber-500 to-yellow-600 text-white shadow-lg border-2 border-amber-400'
                                                    : 'bg-gradient-to-r from-amber-100 to-yellow-100 text-amber-900 border-2 border-amber-300 hover:from-amber-500 hover:to-yellow-600 hover:text-white hover:border-amber-400 hover:shadow-lg'
                                            }`}
                                        >
                                            {category.name}
                                        </button>
                                    );
                                }

                                // 일반 카테고리 스타일
                                return (
                                    <button
                                        key={category.id}
                                        onClick={() => setSelectedCategory(category.id)}
                                        className={`rounded-md px-3 md:px-6 py-1 text-base font-semibold transition-all ${selectedCategory === category.id ? 'bg-[#1C1C1E] text-white' : 'bg-white text-black border-black hover:bg-black hover:text-white'}`}
                                    >
                                        {category.name}
                                    </button>
                                );
                            })}
                        </div>
                    </div>
                )}

                {/* 고급형 카테고리 선택 시 콜아웃 */}
                {selectedCategory && categories.find((cat) => cat.id === selectedCategory && cat.name === '고급형') && (
                    <div className="mb-8 p-6 md:p-8 bg-gradient-to-r from-amber-50 via-yellow-50 to-amber-50 border-2 border-amber-300 rounded-xl shadow-lg">
                        <div className="flex items-start gap-4">
                            {/* 아이콘 */}
                            <div className="flex-shrink-0">
                                <div className="w-12 h-12 md:w-16 md:h-16 bg-gradient-to-br from-amber-400 to-yellow-500 rounded-full flex items-center justify-center shadow-md">
                                    <span className="text-2xl md:text-3xl">⭐</span>
                                </div>
                            </div>

                            {/* 콘텐츠 */}
                            <div className="flex-1">
                                <h3 className="text-xl md:text-2xl font-bold text-amber-900 mb-2 flex items-center gap-2">
                                    <span className="bg-gradient-to-r from-amber-600 to-yellow-600 bg-clip-text text-transparent">프리미엄 고급형 타입</span>
                                </h3>
                                <p className="text-gray-700 text-sm md:text-base leading-relaxed mb-3">최상위 디자인과 기능을 갖춘 프리미엄 홈페이지 타입입니다. 고급스러운 레이아웃과 차별화된 사용자 경험을 제공합니다.</p>
                                <div className="flex flex-wrap gap-2">
                                    <span className="inline-flex items-center px-3 py-1 bg-amber-100 text-amber-800 text-xs font-semibold rounded-full">🎨 고급 디자인</span>
                                    <span className="inline-flex items-center px-3 py-1 bg-amber-100 text-amber-800 text-xs font-semibold rounded-full">⚡ 최신 기술</span>
                                    <span className="inline-flex items-center px-3 py-1 bg-amber-100 text-amber-800 text-xs font-semibold rounded-full">💎 프리미엄 기능</span>
                                </div>
                            </div>
                        </div>
                    </div>
                )}

                {/* 포트폴리오 목록 */}
                {loading ? (
                    <div className="text-center py-12">
                        <div className="text-xl text-gray-600">불러오는 중입니다</div>
                    </div>
                ) : portfolios.length === 0 ? (
                    <div className="text-center py-12">
                        <div className="text-xl text-gray-600">등록된 타입이 존재하지 않습니다.</div>
                    </div>
                ) : (
                    <div className="grid min-h-[65vh] grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 cursor-pointer">
                        {portfolios.map((portfolio) => (
                            <div
                                key={portfolio.id}
                                onClick={() => {
                                    handlePreviewOpen(portfolio.domain!, portfolio.title, portfolio.id);
                                }}
                                className="border-black transition-all overflow-hidden group"
                            >
                                {portfolio.thumbnail && (
                                    <div className="portfolio-list w-full h-48 bg-gray-200 overflow-hidden">
                                        <img src={portfolio.thumbnail} alt={portfolio.title} className="w-full h-full object-cover group-hover:scale-105 transition-transform duration-300" loading="lazy" />
                                    </div>
                                )}

                                <div className="p-2 pt-6">
                                    <h3 className="text-2xl font-bold mb-3 group-hover:text-gray-700">{portfolio.title}</h3>
                                    {portfolio.description && <p className="text-gray-600 mb-4">{portfolio.description}</p>}
                                </div>

                                {/* 버튼 영역 */}
                                <div className="hidden flex gap-3 px-2 pb-6">
                                    {/* 미리보기 버튼 - 팝업 모달 */}
                                    {portfolio.domain ? (
                                        <button
                                            type="button"
                                            onClick={(e) => {
                                                e.preventDefault();
                                                e.stopPropagation();
                                                handlePreviewOpen(portfolio.domain!, portfolio.title, portfolio.id);
                                            }}
                                            className="px-4 py-2 bg-gray-200 text-gray-700 rounded-md hover:bg-gray-300 transition-all"
                                        >
                                            미리보기
                                        </button>
                                    ) : (
                                        <button
                                            type="button"
                                            onClick={(e) => {
                                                e.preventDefault();
                                                e.stopPropagation();
                                                alert('이 포트폴리오에는 도메인이 등록되어 있지 않습니다.');
                                            }}
                                            className="px-4 py-2 bg-gray-200 text-gray-700 rounded-md hover:bg-gray-300 transition-all opacity-50 cursor-not-allowed"
                                            disabled
                                        >
                                            미리보기
                                        </button>
                                    )}
                                </div>
                            </div>
                        ))}
                    </div>
                )}

                {/* Admin Login Link at Bottom */}
                {!currentUser && (
                    <div className="text-center mt-16 pt-8 border-t border-gray-200">
                        <Link href="/admin/login" className="text-sm text-gray-400 hover:text-gray-600 transition-colors">
                            관리자 로그인
                        </Link>
                    </div>
                )}
            </div>

            {/* 미리보기 팝업 모달 */}
            {showPreview && (
                <div className="fixed inset-0 bg-black/50 flex items-center justify-center p-4 z-50" onClick={() => setShowPreview(false)}>
                    <div className="bg-white rounded-lg shadow-2xl w-full max-w-10xl h-full max-h-[90vh] flex flex-col" onClick={(e) => e.stopPropagation()} role="dialog" aria-modal="true" aria-label={`${previewTitle} 미리보기`}>
                        {/* 상단 바 */}
                        <div className="bg-gray-100 px-4 py-3 rounded-t-lg border-b flex items-center gap-4">
                            {/* 주소창 */}
                            <div className="flex-1 px-3 py-2 text-sm text-gray-700 overflow-hidden text-ellipsis whitespace-nowrap">{previewTitle}</div>

                            {/* 모드 토글 */}
                            <div className="flex-1 flex justify-center">
                                <div className="flex items-center gap-2">
                                    <button
                                        type="button"
                                        onClick={() => setPreviewMode('desktop')}
                                        className={`hidden md:block px-3 py-2 rounded-md border text-sm transition-all ${previewMode === 'desktop' ? 'bg-black text-white border-black' : 'bg-white text-gray-700 border-gray-300 hover:bg-gray-50'}`}
                                        title="데스크톱 미리보기"
                                    >
                                        PC
                                    </button>
                                    <button
                                        type="button"
                                        onClick={() => setPreviewMode('mobile')}
                                        className={`px-3 py-2 rounded-md border text-sm transition-all ${previewMode === 'mobile' ? 'bg-black text-white border-black' : 'bg-white text-gray-700 border-gray-300 hover:bg-gray-50'}`}
                                        title="모바일 미리보기(500px)"
                                    >
                                        모바일
                                    </button>
                                </div>
                            </div>

                            <div className="flex-1 flex justify-end items-center gap-3">
                                <button
                                    type="button"
                                    onClick={() => {
                                        const targetPath = `/portfolio/${previewPortfolioId}`;

                                        // 비로그인시
                                        if (!currentUser) {
                                            router.push(`/login?redirect=${encodeURIComponent(targetPath)}`);
                                            return;
                                        }

                                        // 로그인 상태면 바로 이동
                                        router.push(targetPath);
                                    }}
                                    className="px-4 py-2 bg-black text-white rounded-md hover:bg-gray-800 transition-all"
                                >
                                    정보입력
                                </button>

                                {/* 닫기 */}
                                <button onClick={() => setShowPreview(false)} className="w-8 h-8 rounded-full bg-gray-200 hover:bg-gray-300 flex items-center justify-center text-gray-600 font-bold text-lg transition-colors" title="닫기 (ESC)">
                                    ×
                                </button>
                            </div>
                        </div>

                        {/* iframe 컨텐츠 */}
                        <div className="flex-1 bg-white rounded-b-lg flex items-start justify-center">
                            {isPreviewLoading ? (
                                <div className="mt-8 p-6 text-center">
                                    <div className="inline-flex items-center gap-3 px-6 py-4 bg-blue-50 border border-blue-200 rounded-lg">
                                        <div className="animate-spin rounded-full h-6 w-6 border-b-2 border-blue-600"></div>
                                        <div>
                                            <p className="text-blue-800 font-medium">사이트를 불러오는 중입니다...</p>
                                            <p className="text-blue-600 text-sm mt-1">스타일과 리소스를 프록시를 통해 로드하고 있습니다.</p>
                                        </div>
                                    </div>
                                </div>
                            ) : proxyError ? (
                                <div className="mt-8 p-6 bg-red-50 border border-red-200 rounded-lg max-w-lg mx-auto">
                                    <div className="flex items-start gap-3">
                                        <div className="flex-shrink-0 w-8 h-8 bg-red-100 rounded-full flex items-center justify-center">
                                            <svg className="w-5 h-5 text-red-600" fill="currentColor" viewBox="0 0 20 20">
                                                <path
                                                    fillRule="evenodd"
                                                    d="M10 18a8 8 0 100-16 8 8 0 000 16zM8.707 7.293a1 1 0 00-1.414 1.414L8.586 10l-1.293 1.293a1 1 0 101.414 1.414L10 11.414l1.293 1.293a1 1 0 001.414-1.414L11.414 10l1.293-1.293a1 1 0 00-1.414-1.414L10 8.586 8.707 7.293z"
                                                    clipRule="evenodd"
                                                />
                                            </svg>
                                        </div>
                                        <div className="flex-1">
                                            <h3 className="text-lg font-semibold text-red-800 mb-2">미리보기를 불러올 수 없습니다</h3>
                                            <p className="text-red-700 mb-4 leading-relaxed">{proxyError}</p>
                                            <div className="flex gap-3">
                                                <button
                                                    onClick={() => {
                                                        // 원본 URL로 새 창에서 열기
                                                        const originalUrl = new URLSearchParams(previewUrl.split('?')[1] || '').get('url') || previewUrl;
                                                        window.open(originalUrl, '_blank');
                                                    }}
                                                    className="px-4 py-2 bg-red-600 text-white rounded-md hover:bg-red-700 transition-all text-sm font-medium"
                                                >
                                                    새 창에서 열기
                                                </button>
                                                <button
                                                    onClick={() => {
                                                        setProxyError('');
                                                        // 다시 시도
                                                        const originalUrl = new URLSearchParams(previewUrl.split('?')[1] || '').get('url') || previewUrl;
                                                        handlePreviewOpen(originalUrl, previewTitle, previewPortfolioId);
                                                    }}
                                                    className="px-4 py-2 bg-gray-100 text-gray-700 rounded-md hover:bg-gray-200 transition-all text-sm font-medium"
                                                >
                                                    다시 시도
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ) : (
                                <div
                                    className={`mt-4 mb-6 rounded-[12px] border border-gray-200 shadow-md overflow-hidden bg-white`}
                                    style={{
                                        width: previewMode === 'mobile' ? '500px' : '100%',
                                        maxWidth: previewMode === 'mobile' ? '500px' : '100%',
                                        height: 'calc(100% - 2rem)',
                                        transition: 'all 0.6s ease-in-out',
                                        transform: previewMode === 'mobile' ? 'scale(1)' : 'scale(1)',
                                    }}
                                >
                                    <iframe
                                        key={`${previewMode}-${previewUrl}`} // 모드 전환 시 레이아웃 재계산
                                        src={previewUrl}
                                        className={previewMode === 'mobile' ? 'w-[500px] h-full border-0' : 'w-full h-full border-0'}
                                        title={`${previewTitle} 미리보기`}
                                        sandbox="allow-same-origin allow-scripts allow-forms allow-popups"
                                        onError={() => {
                                            setIsPreviewLoading(false);
                                            setProxyError('사이트를 불러올 수 없습니다.');
                                        }}
                                        onLoad={(e) => {
                                            const iframe = e.target as HTMLIFrameElement;
                                            try {
                                                // iframe 로드 성공 확인
                                                if (iframe.contentWindow) {
                                                    setProxyError('');
                                                    setIsPreviewLoading(false);
                                                }
                                            } catch (error) {
                                                // Cross-origin 오류는 정상적인 경우
                                                setProxyError('이 사이트는 미리보기를 허용하지 않습니다.');
                                                setIsPreviewLoading(false);
                                            }
                                        }}
                                    />
                                </div>
                            )}
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}
