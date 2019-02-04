package myInterface;

public interface Space {
    int getCountRooms();

    void setCountRooms(int newCountRooms);

    double getSquare();

    void setSquare(double newSquare);

    Object clone() throws CloneNotSupportedException;
}
