package com.cinema.backend.handler;

public class ExceptionResponseWrapper {
  private ExceptionResponse data;

    public ExceptionResponseWrapper(ExceptionResponse data) {
        this.data = data;
    }

    public ExceptionResponse getData() {
        return data;
    }

    public void setData(ExceptionResponse data) {
        this.data = data;
    }
}
