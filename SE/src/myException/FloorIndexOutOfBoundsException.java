package myException;

public class FloorIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public FloorIndexOutOfBoundsException() {
        this("Выход за границы номеров этажей");
    }

    public FloorIndexOutOfBoundsException(String message) {
        super(message);
    }
}
