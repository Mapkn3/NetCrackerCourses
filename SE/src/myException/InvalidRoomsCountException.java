package myException;

public class InvalidRoomsCountException extends IllegalArgumentException {
    public InvalidRoomsCountException() {
        this("Некорректное количество комнат в помещении");
    }

    public InvalidRoomsCountException(String message) {
        super(message);
    }
}
