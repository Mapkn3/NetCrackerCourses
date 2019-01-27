package myException;

public class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException {
    String message = "Выход за границы номеров помещений";

    public SpaceIndexOutOfBoundsException() {
        super();
    }

    public SpaceIndexOutOfBoundsException(String mes) {
        super(mes);
        this.message = mes;
    }
}
