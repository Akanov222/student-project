package edu.javacourse.studentorder.exeption;

public class CityRegisterException extends Exception {

    private String code;


    public CityRegisterException() {
        super();
    }

    public CityRegisterException(String code, String message) {
        super(message);
        this.code = code;
    }

    public CityRegisterException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
