package io.awportfoiioapi.excel.service.impl;


import io.awportfoiioapi.excel.dto.request.ExcelRequest;
import io.awportfoiioapi.excel.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {
    

    @Override
    public byte[] createSubmissionExcel(ExcelRequest requests) {
        return new byte[0];
    }
}
