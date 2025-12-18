package io.awportfoiioapi.portfolio.serivce.impl;

import io.awportfoiioapi.apiresponse.ApiResponse;
import io.awportfoiioapi.portfolio.dto.request.PortfolioPostRequest;
import io.awportfoiioapi.portfolio.dto.request.PortfolioPutRequest;
import io.awportfoiioapi.portfolio.dto.response.PortfolioGetDetailResponse;
import io.awportfoiioapi.portfolio.dto.response.PortfolioResponse;
import io.awportfoiioapi.portfolio.repository.PortfolioRepository;
import io.awportfoiioapi.portfolio.serivce.PortfolioService;
import io.awportfoiioapi.utils.S3FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
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
        return null;
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
