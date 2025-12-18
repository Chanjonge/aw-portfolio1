package io.awportfoiioapi.portfolio.serivce;

import io.awportfoiioapi.apiresponse.ApiResponse;
import io.awportfoiioapi.portfolio.dto.request.PortfolioPostRequest;
import io.awportfoiioapi.portfolio.dto.request.PortfolioPutRequest;
import io.awportfoiioapi.portfolio.dto.response.PortfolioGetDetailResponse;
import io.awportfoiioapi.portfolio.dto.response.PortfolioResponse;

import java.util.List;

public interface PortfolioService {
    List<PortfolioResponse> getPortfolioList();
    PortfolioGetDetailResponse getPortfolioDetail(Long id);
    ApiResponse createPortfolio(PortfolioPostRequest request);
    ApiResponse modifyPortfolio(PortfolioPutRequest request);
    ApiResponse deletePortfolio(Long id);
}
