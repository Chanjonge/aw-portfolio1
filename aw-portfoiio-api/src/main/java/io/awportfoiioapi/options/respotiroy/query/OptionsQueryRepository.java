package io.awportfoiioapi.options.respotiroy.query;

import io.awportfoiioapi.options.entity.Options;

import java.util.List;

public interface OptionsQueryRepository {
    Long countByQuestionId(Long questionId);
    
    List<Options> findByQuestionId(Long id);
}
