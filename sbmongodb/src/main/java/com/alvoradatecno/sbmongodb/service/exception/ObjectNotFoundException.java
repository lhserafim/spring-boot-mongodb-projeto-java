package com.alvoradatecno.sbmongodb.service.exception;

// RuntimeException não exige que eu trate a exceção (como a Exception)
// Uso este, pq vou usar uma classe aux. para tratar exceção
public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // Sobrepor o construtor
    public ObjectNotFoundException(String msg) {
        super(msg);
    }
}
