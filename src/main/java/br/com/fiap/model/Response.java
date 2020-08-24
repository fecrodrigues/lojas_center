package br.com.fiap.model;

public class Response<T> {

    private String message;
    private T response;

    public Response(String message, T response) {
        this.message = message;
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
