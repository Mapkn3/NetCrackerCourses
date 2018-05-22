package buildings;

import java.util.Iterator;
import myInterface.Floor;
import myInterface.Space;

public class SynchronizedFloor implements Floor, Cloneable{
    
    private final Floor floor;
    
    public SynchronizedFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    synchronized public int getCount() {
        return this.floor.getCount();
    }

    @Override
    synchronized public double getTotalSquare() {
        return this.floor.getTotalSquare();
    }

    @Override
    synchronized public int getTotalRooms() {
        return this.floor.getTotalRooms();
    }

    @Override
    synchronized public Space[] getSpaces() {
        return this.floor.getSpaces();
    }

    @Override
    synchronized public Space getSpace(int index) {
        return this.floor.getSpace(index);
    }

    @Override
    synchronized public void setSpace(int index, Space newSpace) {
        this.floor.setSpace(index, newSpace);
    }

    @Override
    synchronized public void addSpace(int index, Space newSpace) {
        this.floor.addSpace(index, newSpace);
    }

    @Override
    synchronized public void deleteSpace(int index) {
        this.floor.deleteSpace(index);
    }

    @Override
    synchronized public Space getBestSpace() {
        return this.floor.getBestSpace();
    }

    @Override
    synchronized public Iterator iterator() {
        return this.floor.iterator();
    }
    
    @Override
    synchronized public String toString() {
        return this.floor.toString();
    }

    @Override
    synchronized public boolean equals(Object obj) {
        return floor.equals(obj);
    }
    
    @Override
    synchronized public int hashCode() {
        return this.floor.hashCode();
    }

    @Override
    public Object clone()throws CloneNotSupportedException {
        return this.floor.clone();
    }
}
