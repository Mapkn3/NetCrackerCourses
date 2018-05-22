package myException;

public class InexchangeableSpacesException extends Exception{
    String message = "Несоответствие обменивающихся помещений";
    
    public InexchangeableSpacesException() {
        super();
    }
    public InexchangeableSpacesException(String mes) {
        super(mes);
        this.message = mes;
    }
}
