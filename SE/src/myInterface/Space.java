package myInterface;

public interface Space {
    public int getCountRooms();
    public void setCountRooms(int newCountRooms);
    public double getSquare();
    public void setSquare(double newSquare);
    public Object clone() throws CloneNotSupportedException;
}
