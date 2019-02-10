package myIterator;

import myInterface.Building;
import myInterface.Floor;

import java.util.Iterator;

public class BuildingIterator implements Iterator<Floor> {
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
        return building.getFloor(index++);
    }
}
