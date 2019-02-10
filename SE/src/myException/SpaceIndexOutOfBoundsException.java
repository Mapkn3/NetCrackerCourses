package myException;

public class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public SpaceIndexOutOfBoundsException() {
        this("Выход за границы номеров помещений");
    }

    public SpaceIndexOutOfBoundsException(String message) {
        super(message);
    }
}
