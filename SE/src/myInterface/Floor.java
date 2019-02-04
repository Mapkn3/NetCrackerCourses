package myInterface;

import java.util.Iterator;

public interface Floor {
    int getCount();

    double getTotalSquare();

    int getTotalRooms();

    Space[] getSpaces();

    Space getSpace(int index);

    void setSpace(int index, Space newSpace);

    void addSpace(int index, Space newSpace);

    void deleteSpace(int index);

    Space getBestSpace();

    Iterator iterator();

    Object clone() throws CloneNotSupportedException;
}
