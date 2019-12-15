package pl.wojcikiewicz.service.exception;

public class UserNotAdultException extends Exception {

    private String message;

    public UserNotAdultException(String message) {
        super(message);
        this.message = message;
    }
}
