package io.awportfoiioapi.excel.service;

import io.awportfoiioapi.apiresponse.ApiResponse;
import io.awportfoiioapi.excel.dto.request.ExcelRequest;

public interface ExcelService {
    
    byte[] createSubmissionExcel(ExcelRequest requests);
    
    ApiResponse modifySubmitOff(ExcelRequest request);
    
    ApiResponse copyPortfolio(Long portfolioId);
}
