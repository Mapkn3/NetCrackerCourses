package myIterator;

import myInterface.Floor;
import myInterface.Space;

import java.util.Iterator;

public class FloorIterator implements Iterator<Space> {
    private final Floor floor;
    private int index;

    public FloorIterator(Floor floor) {
        this.floor = floor;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < floor.getCount();
    }

    @Override
    public Space next() {
        return floor.getSpace(index++);
    }

}
