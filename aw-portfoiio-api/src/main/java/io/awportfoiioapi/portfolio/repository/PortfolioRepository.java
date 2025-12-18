package io.awportfoiioapi.portfolio.repository;

import io.awportfoiioapi.portfolio.entity.Portfolio;
import io.awportfoiioapi.portfolio.repository.query.PortfolioQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio,Long>, PortfolioQueryRepository {
}
