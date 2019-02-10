package myException;

public class InexchangeableFloorsException extends Exception {
    public InexchangeableFloorsException() {
        this("Несоответствие обменивающихся этажей");
    }

    public InexchangeableFloorsException(String message) {
        super(message);
    }
}
