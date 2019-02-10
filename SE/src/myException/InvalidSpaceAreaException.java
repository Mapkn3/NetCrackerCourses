package myException;

public class InvalidSpaceAreaException extends IllegalArgumentException {
    public InvalidSpaceAreaException() {
        this("Некорректная площадь помещения");
    }

    public InvalidSpaceAreaException(String message) {
        super(message);
    }
}
