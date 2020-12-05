package com.mx.quotes.service;


import com.mx.quotes.model.Quote;
import com.mx.quotes.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuoteServiceImpl implements IQuoteService{

    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    @Transactional
    public Quote save(Quote quote) {
        return quoteRepository.save(quote);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Quote> findAll() {
        return (List<Quote>) quoteRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Quote findById(Integer id) {
        return quoteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        quoteRepository.deleteById(id);
    }
}
