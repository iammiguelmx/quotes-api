package com.mx.quotes.service;

import com.mx.quotes.model.Quote;
import java.util.List;

public interface IQuoteService {

    public Quote save(Quote quote);
    public List<Quote> findAll();
    public Quote findById(Integer id);
    public void delete(Integer id);

}
