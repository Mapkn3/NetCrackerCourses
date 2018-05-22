package myException;

public class InvalidSpaceAreaException extends IllegalArgumentException {
    String message = "Некорректная площадь помещения";
    public InvalidSpaceAreaException() {
	super();
    }
    public InvalidSpaceAreaException(String mes) {
	super(mes);
	this.message = mes;
    }
}
