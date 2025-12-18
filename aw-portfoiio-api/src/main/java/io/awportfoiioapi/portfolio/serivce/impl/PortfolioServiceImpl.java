package io.awportfoiioapi.portfolio.serivce.impl;

import io.awportfoiioapi.portfolio.repository.PortfolioRepository;
import io.awportfoiioapi.portfolio.serivce.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
}
