package myInterface;

public interface Floor {
    public int getCount();
    public double getTotalSquare();
    public int getTotalRooms();
    public Space[] getSpaces();
    public Space getSpace(int index);
    public void setSpace(int index, Space newSpace);
    public void addSpace(int index, Space newSpace);
    public void deleteSpace(int index);
    public Space getBestSpace();
    public java.util.Iterator iterator();
    public Object clone()throws CloneNotSupportedException;
}
