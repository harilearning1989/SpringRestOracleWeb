package com.web.demo.batch.write;

import com.web.demo.entities.CreditCard;
import com.web.demo.repos.CreditCardRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CreditCardWriter implements ItemWriter<CreditCard> {

    @Autowired
    private CreditCardRepository repo;

    @Override
    @Transactional
    public void write(List<? extends CreditCard> users) throws Exception {
        repo.saveAll(users);
    }
}
