package io.awportfoiioapi.submission.controller;


import io.awportfoiioapi.submission.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class SubmissionController {

    private final SubmissionService submissionService;
}
