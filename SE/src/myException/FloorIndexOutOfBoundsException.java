package myException;

public class FloorIndexOutOfBoundsException extends IndexOutOfBoundsException {
    String message = "Выход за границы номеров этажей";
    
    public FloorIndexOutOfBoundsException() {
	super();
    }
    public FloorIndexOutOfBoundsException(String mes) {
	super(mes);
	this.message = mes;
    }
}
