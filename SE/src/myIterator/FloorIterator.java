package myIterator;

import java.util.Iterator;
import myInterface.Floor;
import myInterface.Space;

public class FloorIterator implements Iterator {
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
        Space temp = floor.getSpace(index++);
        return temp;
    }
    
}
