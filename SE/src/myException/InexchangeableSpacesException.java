package myException;

public class InexchangeableSpacesException extends Exception {
    public InexchangeableSpacesException() {
        this("Несоответствие обменивающихся помещений");
    }

    public InexchangeableSpacesException(String message) {
        super(message);
    }
}
