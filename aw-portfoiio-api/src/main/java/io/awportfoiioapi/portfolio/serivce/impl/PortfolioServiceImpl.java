package io.awportfoiioapi.portfolio.serivce.impl;

import io.awportfoiioapi.advice.exception.CategoryAndPortfolioException;
import io.awportfoiioapi.apiresponse.ApiResponse;
import io.awportfoiioapi.category.entity.Category;
import io.awportfoiioapi.category.repository.CategoryRepository;
import io.awportfoiioapi.file.entity.CommonFile;
import io.awportfoiioapi.file.enums.CommonFileType;
import io.awportfoiioapi.file.repository.CommonFileRepository;
import io.awportfoiioapi.portfolio.dto.request.PortfolioPostRequest;
import io.awportfoiioapi.portfolio.dto.request.PortfolioPutRequest;
import io.awportfoiioapi.portfolio.dto.response.PortfolioGetDetailResponse;
import io.awportfoiioapi.portfolio.dto.response.PortfolioResponse;
import io.awportfoiioapi.portfolio.entity.Portfolio;
import io.awportfoiioapi.portfolio.repository.PortfolioRepository;
import io.awportfoiioapi.portfolio.serivce.PortfolioService;
import io.awportfoiioapi.utils.S3FileUtils;
import io.awportfoiioapi.utils.UploadResult;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.And;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final CategoryRepository categoryRepository;
    private final CommonFileRepository commonFileRepository;
    private final S3FileUtils s3FileUtils;
    
    @Override
    public List<PortfolioResponse> getPortfolioList() {
        return List.of();
    }
    
    @Override
    public PortfolioGetDetailResponse getPortfolioDetail(Long id) {
        return null;
    }
    
    @Override
    public ApiResponse createPortfolio(PortfolioPostRequest request) {
        Integer order = request.getOrder();
        boolean result = portfolioRepository.existsByPortfolioOrder(order);
        if (result) {
            throw new CategoryAndPortfolioException("이미 존재 하는 포트폴리오 순서입니다.", "order");
        }
        // 파일
        MultipartFile thumbnail = request.getThumbnail();
        Long requestCategoryId = request.getCategoryId();
        
        // category는 null 허용
        Category category = null;
        if (requestCategoryId != null) {
            category = categoryRepository.findById(requestCategoryId).orElse(null);
        }

        // 썸네일 업로드 (있으면)
        UploadResult uploadResult = null;
        String thumbnailUrl = null;
        
        if (thumbnail != null && !thumbnail.isEmpty()) {
            uploadResult = s3FileUtils.storeFile(thumbnail, "portfolio");
            thumbnailUrl = uploadResult.url();
        }

        // Portfolio는 항상 생성
        Portfolio portfolio = Portfolio.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .category(category)
                .slug(request.getSlug())
                .domain(request.getDomain())
                .thumbnail(thumbnailUrl) // null 가능
                .orders(request.getOrder())
                .isActive(request.getIsActive())
                .build();
        
        Portfolio savedPortfolio = portfolioRepository.save(portfolio);

        // 파일이 있을 때만 CommonFile 생성
        if (uploadResult != null) {
            CommonFile commonFile = CommonFile.builder()
                    .fileTargetId(savedPortfolio.getId())
                    .fileName(uploadResult.originalFilename())
                    .fileUuidName(uploadResult.uuid())
                    .fileUrl(uploadResult.url())
                    .fileType(CommonFileType.PORTFOLIO)
                    .build();
            
            commonFileRepository.save(commonFile);
        }
        
        return new ApiResponse(200,true,"포트폴리오가 생성되었습니다.");
    }
    
    @Override
    public ApiResponse modifyPortfolio(PortfolioPutRequest request) {
        return null;
    }
    
    @Override
    public ApiResponse deletePortfolio(Long id) {
        return null;
    }
}
