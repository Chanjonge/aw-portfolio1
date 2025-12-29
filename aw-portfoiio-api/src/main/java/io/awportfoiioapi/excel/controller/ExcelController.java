package io.awportfoiioapi.excel.controller;

import io.awportfoiioapi.excel.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ExcelController {

    private final ExcelService excelService;
    
    
    @GetMapping("/excel")
    public String getExcel() {
        return null;
    }
}
