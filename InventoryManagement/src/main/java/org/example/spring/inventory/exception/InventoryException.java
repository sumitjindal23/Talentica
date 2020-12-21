package org.example.spring.inventory.exception;

import java.util.List;

public class InventoryException extends Exception {

    private static final long serialVersionUID = 1;
    private String exceptionCode;
    private List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    /**
     * @param message
     */
    public InventoryException(String message){
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public InventoryException(String message, Throwable cause){
        super(message, cause);
    }

    /**
     * @param omExceptionCode
     * @param message
     */
    public InventoryException(String exceptionCode, String message){
        super(message);
        this.exceptionCode = exceptionCode;
    }

    /**
     * @param omExceptionCode
     * @param message
     * @param throwable
     */
    public InventoryException(String exceptionCode, String message,Throwable throwable){
        super(message,throwable);
        this.exceptionCode = exceptionCode;
    }    
}
    
