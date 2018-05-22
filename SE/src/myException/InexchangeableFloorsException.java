package myException;

public class InexchangeableFloorsException extends Exception{
    String message = "Несоответствие обменивающихся этажей";
    
    public InexchangeableFloorsException() {
        super();
    }
    public InexchangeableFloorsException(String mes) {
        super(mes);
        this.message = mes;
    }
}
