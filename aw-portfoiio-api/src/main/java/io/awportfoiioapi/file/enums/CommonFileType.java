package io.awportfoiioapi.file.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonFileType {
    PORTFOLIO("포트폴리오"),
    QUESTION("질문"),
    OPTIONS("옵션");
    
    private final String value;
}
