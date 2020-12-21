package org.example.spring.inventory.dao.exception;

import org.hibernate.HibernateException;

import java.util.List;

public class DAOException extends HibernateException {

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
    public DAOException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}