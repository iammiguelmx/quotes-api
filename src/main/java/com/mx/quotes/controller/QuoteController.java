package com.mx.quotes.controller;


import com.mx.quotes.model.Quote;
import com.mx.quotes.service.IQuoteService;
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
    private IQuoteService iQuoteService;

    @PostMapping("/addQuote")
    public ResponseEntity<?> saveQuote(@Valid @RequestBody Quote quote) {
        Map<String, Object> params = new HashMap<>();
        try {
            iQuoteService.save(quote);
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
            quotes = iQuoteService.findAll();
        } catch (DataAccessException e) {
            params.put("message", "Se produjo un error al consultar en la base de datos.");
            params.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(params, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }


    @GetMapping("/quote/{id}")
    public ResponseEntity<?> show(@PathVariable int id) {
        Quote quote;
        Map<String, Object> params = new HashMap<>();
        try {
            quote = iQuoteService.findById(id);
        } catch (DataAccessException e) {
            params.put("message", "Se produjo un error al consultar en la base de datos.");
            params.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(params, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (quote == null) {
            params.put("message", "No se encontro el cliente.");
            return new ResponseEntity<>(params, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Quote>(quote, HttpStatus.OK);
    }

    @DeleteMapping("/quote/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Map<String, Object> params = new HashMap<>();
        try {
            iQuoteService.delete(id);
        } catch (DataAccessException e) {
            params.put("message", "Se produjo un error al eliminar en la base de datos.");
            params.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(params, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
