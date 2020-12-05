package com.mx.quotes.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Document(collection = "Quote")
public class Quote implements Serializable {

    @Id
    private int id;

    @NotEmpty(message = "quote no puede estar vacío.")
    @Size(min = 5, max = 10, message = "debe tener un tamaño entre 5 y 10 caracteres.")
    private String quote;

    @NotEmpty(message = "author no puede estar vacío.")
    @Size(min = 3, message = "debe tener un tamaño mayor de 3 caracteres.")
    private String author;

}
