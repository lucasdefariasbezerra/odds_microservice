package com.lucasbezerra.oddsproject.exceptionHandler;

import java.util.function.Supplier;

public class RestInsertionHandler extends Exception {
    public RestInsertionHandler(String errorMessage) {
        super(errorMessage);
    }
}
