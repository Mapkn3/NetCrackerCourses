package myInterface;

import java.util.Iterator;

public interface Building {
    int getCountFloors();

    int getCountSpaces();

    double getTotalSquare();

    int getTotalRooms();

    Floor[] getFloors();

    Floor getFloor(int index);

    void setFloor(int index, Floor newFloor);

    Space getSpace(int index);

    void setSpace(int index, Space newSpace);

    void addSpace(int index, Space newSpace);

    void deleteSpace(int index);

    Space getBestSpace();

    Space[] getSortedSpace();

    Iterator iterator();

    Object clone() throws CloneNotSupportedException;
}
