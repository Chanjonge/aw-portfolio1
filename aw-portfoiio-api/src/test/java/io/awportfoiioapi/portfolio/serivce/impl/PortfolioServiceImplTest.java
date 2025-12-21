package io.awportfoiioapi.portfolio.serivce.impl;

import io.awportfoiioapi.RepositoryAndServiceTestSupport;
import io.awportfoiioapi.apiresponse.ApiResponse;
import io.awportfoiioapi.portfolio.dto.request.PortfolioPostRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioServiceImplTest extends RepositoryAndServiceTestSupport {

    @DisplayName("포트폴리오 업로드(파일포함 , 카테고리 포함)")
    @Test
    void test1() throws IOException {
        File file1 = new File("src/test/java/io/awportfoiioapi/image/이건 모자가 아니잖아.jpg");
        FileInputStream fis1 = new FileInputStream(file1);
        
        MockMultipartFile multipartFile1 = new MockMultipartFile(
                "portfolio", file1.getName(), "image/jpeg",fis1
        );
        PortfolioPostRequest request = new PortfolioPostRequest(
                1L,
                "포트폴리오 타이틀",
                "포트폴리오 설명",
                "포트폴리오 도메인",
                2,
                "포트폴리오 슬러그",
                multipartFile1,
                Boolean.TRUE
        );
        ApiResponse portfolio = portfolioService.createPortfolio(request);
        System.out.println("portfolio = " + portfolio);
    }
    @DisplayName("포트폴리오 업로드(파일포함")
    @Test
    void test2() throws IOException {
        File file1 = new File("src/test/java/io/awportfoiioapi/image/이건 모자가 아니잖아.jpg");
        FileInputStream fis1 = new FileInputStream(file1);
        
        MockMultipartFile multipartFile1 = new MockMultipartFile(
                "portfolio", file1.getName(), "image/jpeg",fis1
        );
        PortfolioPostRequest request = new PortfolioPostRequest(
                null,
                "포트폴리오 타이틀",
                "포트폴리오 설명",
                "포트폴리오 도메인",
                3,
                "포트폴리오 슬러그",
                multipartFile1,
                Boolean.TRUE
        );
        ApiResponse portfolio = portfolioService.createPortfolio(request);
        System.out.println("portfolio = " + portfolio);
    }
    
    @DisplayName("포트폴리오 업로드(카테고리 포함)")
    @Test
    void test3(){
        PortfolioPostRequest request = new PortfolioPostRequest(
                1L,
                "포트폴리오 타이틀",
                "포트폴리오 설명",
                "포트폴리오 도메인",
                4,
                "포트폴리오 슬러그",
                null,
                Boolean.TRUE
        );
        ApiResponse portfolio = portfolioService.createPortfolio(request);
        System.out.println("portfolio = " + portfolio);
    }
    
    
    @DisplayName("포트폴리오 업로드")
    @Test
    void test4(){
        PortfolioPostRequest request = new PortfolioPostRequest(
                null,
                "포트폴리오 타이틀",
                "포트폴리오 설명",
                "포트폴리오 도메인",
                5,
                "포트폴리오 슬러그",
                null,
                Boolean.TRUE
        );
        ApiResponse portfolio = portfolioService.createPortfolio(request);
        System.out.println("portfolio = " + portfolio);
    }
}