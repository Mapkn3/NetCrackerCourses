package myException;

public class InvalidRoomsCountException extends IllegalArgumentException {
    String message = "Некорректное количество комнат в помещении";
    public InvalidRoomsCountException() {
	super();
    }
    public InvalidRoomsCountException(String mes) {
	super(mes);
	this.message = mes;
    }
}
