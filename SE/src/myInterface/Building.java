package myInterface;

public interface Building {
    public int getCountFloors();
    public int getCountSpaces();
    public double getTotalSquare();
    public int getTotalRooms();
    public Floor[] getFloors();
    public Floor getFloor(int index);
    public void setFloor(int index, Floor newFloor);
    public Space getSpace(int index);
    public void setSpace(int index, Space newSpace);
    public void addSpace(int index, Space newSpace);
    public void deleteSpace(int index);
    public Space getBestSpace();
    public Space[] getSortedSpace();
    public java.util.Iterator iterator();
    public Object clone()throws CloneNotSupportedException;
}
