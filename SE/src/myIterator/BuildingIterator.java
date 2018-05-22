package myIterator;

import java.util.Iterator;
import myInterface.Building;
import myInterface.Floor;

public class BuildingIterator implements Iterator {
    private final Building building;
    private int index;
    
    public BuildingIterator(Building building) {
        this.building = building;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < building.getCountFloors();
    }

    @Override
    public Floor next() {
        Floor temp = building.getFloor(index++);
        return temp;
    }
}
