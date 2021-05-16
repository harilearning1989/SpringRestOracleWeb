package com.web.demo.reposDev;

import com.web.demo.entities.CreditCardRisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRiskRespository extends JpaRepository<CreditCardRisk, Long> {
}
