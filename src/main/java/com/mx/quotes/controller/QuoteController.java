package com.mx.quotes.controller;


import com.mx.quotes.model.Quote;
import com.mx.quotes.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class QuoteController {

    @Autowired
    private QuoteRepository quoteRepository;

    @PostMapping("/addQuote")
    public ResponseEntity<?> saveQuote(@Valid @RequestBody Quote quote) {
        Map<String, Object> params = new HashMap<>();
        try {
            quoteRepository.save(quote);
        } catch (DataAccessException e) {
            params.put("message", "Se produjo un error al insertar en la base de datos.");
            params.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(params, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> showQuotes() {
        Map<String, Object> params = new HashMap<>();
        List<Quote> quotes;
        try {
            quotes = quoteRepository.findAll();
        } catch (DataAccessException e) {
            params.put("message", "Se produjo un error al consultar en la base de datos.");
            params.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(params, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }

}
