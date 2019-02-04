package buildings.dwelling;

import myException.FloorIndexOutOfBoundsException;
import myException.SpaceIndexOutOfBoundsException;
import myInterface.Building;
import myInterface.Floor;
import myInterface.Space;
import myIterator.BuildingIterator;

import java.io.Serializable;
import java.util.Iterator;

public class Dwelling implements Building, Serializable, Cloneable {
    private Floor[] floors;

    public Dwelling(int countFloors, int[] countFlatsOnFloor) {
        this.floors = new DwellingFloor[countFloors];
        for (int i = 0; i < floors.length; i++) {
            this.floors[i] = new DwellingFloor(countFlatsOnFloor[i]);
        }
    }

    public Dwelling(Floor[] newFloors) {
        this.floors = newFloors;
    }

    @Override
    public int getCountFloors() {
        return this.floors.length;
    }

    @Override
    public int getCountSpaces() {
        int countSpaces = 0;
        for (Floor floor : this.floors) {
            countSpaces += floor.getCount();
        }
        return countSpaces;
    }

    @Override
    public double getTotalSquare() {
        double totalSquare = 0;
        for (Floor floor : this.floors) {
            totalSquare += floor.getTotalSquare();
        }
        return totalSquare;
    }

    @Override
    public int getTotalRooms() {
        int totalRooms = 0;
        for (Floor floor : this.floors) {
            totalRooms += floor.getTotalRooms();
        }
        return totalRooms;
    }

    @Override
    public Floor[] getFloors() {
        return this.floors;
    }

    @Override
    public Floor getFloor(int index) throws FloorIndexOutOfBoundsException {
        if (isCorrectFloorIndex(index)) {
            return this.floors[index];
        } else {
            throw new FloorIndexOutOfBoundsException();
        }

    }

    @Override
    public void setFloor(int index, Floor newFloor) throws FloorIndexOutOfBoundsException {
        if (isCorrectFloorIndex(index)) {
            this.floors[index] = newFloor;
        } else {
            throw new FloorIndexOutOfBoundsException();
        }
    }

    @Override
    public Space getSpace(int index) throws SpaceIndexOutOfBoundsException {
        if (isCorrectFlatIndex(index)) {
            return getFloor(getFloorIndexForFlatIndex(index)).getSpace(getLocalFlatIndexOnFloor(index));
        } else {
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    @Override
    public void setSpace(int index, Space newFlat) throws SpaceIndexOutOfBoundsException {
        if (isCorrectFlatIndex(index)) {
            getFloor(getFloorIndexForFlatIndex(index)).setSpace(getLocalFlatIndexOnFloor(index), newFlat);
        } else {
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    @Override
    public void addSpace(int index, Space newFlat) throws SpaceIndexOutOfBoundsException {
        if (isCorrectFlatIndex(index)) {
            getFloor(getFloorIndexForFlatIndex(index)).addSpace(getLocalFlatIndexOnFloor(index), newFlat);
        } else {
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    @Override
    public void deleteSpace(int index) throws SpaceIndexOutOfBoundsException {
        if (isCorrectFlatIndex(index)) {
            getFloor(getFloorIndexForFlatIndex(index)).deleteSpace(getLocalFlatIndexOnFloor(index));
        } else {
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    private boolean isCorrectFlatIndex(int flatIndex) {
        return (flatIndex >= 0) && (flatIndex < getCountSpaces());
    }

    private boolean isCorrectFloorIndex(int floorIndex) {
        return (floorIndex >= 0) && (floorIndex < floors.length);
    }

    private int getLocalFlatIndexOnFloor(int index) throws SpaceIndexOutOfBoundsException, FloorIndexOutOfBoundsException {
        if (isCorrectFlatIndex(index)) {
            int i = 0;
            while (index > floors[i].getCount()) {
                index -= floors[i].getCount();
                i++;
                if (i == floors.length) {
                    throw new FloorIndexOutOfBoundsException();
                }
            }
            return index;
        } else {
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    private int getFloorIndexForFlatIndex(int index) throws FloorIndexOutOfBoundsException {
        int floorIndex = 0;
        int i = 0;
        while (index > floors[i].getCount()) {
            index -= floors[i].getCount();
            i++;
            if (i == floors.length) {
                throw new FloorIndexOutOfBoundsException();
            }
            floorIndex++;
        }
        return floorIndex;
    }

    @Override
    public Space getBestSpace() {
        int indexBestFloor = 0;
        Space bestSpace = floors[indexBestFloor].getBestSpace();
        for (int i = 1; i < floors.length; i++) {
            if (floors[i].getBestSpace().getSquare() > bestSpace.getSquare()) {
                indexBestFloor = i;
                bestSpace = floors[indexBestFloor].getBestSpace();
            }
        }
        return bestSpace;
    }

    @Override
    public Space[] getSortedSpace() {
        int n = this.getCountSpaces();
        Space[] flats = new Space[n];
        int k = 0;
        for (Floor floor : floors) {
            for (Space flat : floor.getSpaces()) {
                flats[k] = flat;
                k++;
            }
        }
        Space t;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (flats[j].getSquare() < flats[j + 1].getSquare()) {
                    t = flats[j];
                    flats[j] = flats[j + 1];
                    flats[j + 1] = t;
                }
            }
        }
        return flats;
    }

    @Override
    public Iterator<Floor> iterator() {
        return new BuildingIterator(this);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Dwelling");
        str.append(String.format(" (%d", this.getCountFloors()));
        for (Floor floor : this.getFloors()) {
            str.append(String.format(", %s", floor.toString()));
        }
        str.append(")");
        return str.toString();
    }

    @Override
    public boolean equals(Object object) {
        boolean isEquals = false;
        if (object.getClass() == Dwelling.class) {
            Dwelling dwelling = (Dwelling) object;
            if (dwelling.getCountFloors() == this.getCountFloors()) {
                for (int i = 0; i < dwelling.getCountFloors(); i++) {
                    if (!dwelling.getFloor(i).equals(this.getFloor(i))) {
                        return false;
                    }
                }
                isEquals = true;
            }
        }
        return isEquals;
    }

    @Override
    public int hashCode() {
        int hash = this.getCountFloors();
        for (Floor floor : this.getFloors()) {
            hash ^= floor.hashCode();
        }
        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Dwelling dwelling = (Dwelling) super.clone();
        dwelling.floors = this.floors.clone();
        for (int i = 0; i < this.getCountFloors(); i++) {
            dwelling.floors[i] = (Floor) this.floors[i].clone();
        }
        return dwelling;
    }
}
