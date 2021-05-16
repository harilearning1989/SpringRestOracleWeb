package com.web.demo.repos;

import com.web.demo.entities.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    List<CreditCard> findByCardTypeFullNameAndIssuingBankAndBillingDate(String cardTypeFullName, String issuingBank, int billDate);
}
