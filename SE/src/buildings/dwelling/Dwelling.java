package buildings.dwelling;

import java.io.Serializable;
import java.util.Iterator;
import myInterface.*;
import myException.*;
import myIterator.BuildingIterator;

public class Dwelling implements Building, Serializable, Cloneable {
    protected Floor[] floors;
    
    public Dwelling(int countFloors, int[] countFlats) {
	this.floors = new DwellingFloor[countFloors];
	for (int i = 0; i < countFloors; i++) {
            this.floors[i] = new DwellingFloor(countFlats[i]);
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
        int f = 0;
        for (Floor floor : this.floors) {
            f += floor.getCount();
        }
	return f;
    }
    @Override
    public double getTotalSquare() {
	double s = 0;
        for (Floor floor : this.floors) {
            s += floor.getTotalSquare();
        }
	return s;
    }
    @Override
    public int getTotalRooms() {
	int r = 0;
        for (Floor floor : this.floors) {
            r += floor.getTotalRooms();
        }
	return r;
    }
    @Override
    public Floor[] getFloors() {
	return this.floors;
    }
    @Override
    public Floor getFloor(int index) {
        if ((index < 0) || (index >= floors.length)) {
            throw new FloorIndexOutOfBoundsException();
	}
	return this.floors[index];
    }
    @Override
    public void setFloor(int index, Floor newFloor) {
        if ((index < 0) || (index >= floors.length)) {
            throw new FloorIndexOutOfBoundsException();
	}
	this.floors[index] = newFloor;
    }
    @Override
    public Space getSpace(int index) {
	if ((index < 0) || (index >= this.getCountSpaces())) {
            throw new SpaceIndexOutOfBoundsException();
	}
	int i = 0;
	while ((index > floors[i].getCount()) && (i < floors.length)) {
            index -= floors[i].getCount();
            i++;
	}
	return floors[i].getSpace(index);
    }
    @Override
    public void setSpace(int index, Space newFlat) {
	if ((index < 0) || (index >= this.getCountSpaces())) {
            throw new SpaceIndexOutOfBoundsException();
	}
        for (Floor floor : floors) {
            if (index >= floor.getCount()) {
                index -= floor.getCount();
            } else {
                floor.setSpace(index, newFlat);
            }
        }
    }
    @Override
    public void addSpace(int index, Space newFlat) {
	if ((index < 0) || (index >= this.getCountSpaces())) {
            throw new SpaceIndexOutOfBoundsException();
	}
        for (Floor floor : floors) {
            if ((index - floor.getCount()) > 0) {
                index -= floor.getCount();
            } else {
                floor.addSpace(index, newFlat);
            }
        }
    }
    @Override
    public void deleteSpace(int index) {
	if ((index < 0) || (index >= this.getCountSpaces())) {
            throw new SpaceIndexOutOfBoundsException();
	}
        for (Floor floor : floors) {
            if ((index - floor.getCount()) > 0) {
                index -= floor.getCount();
            } else {
                floor.deleteSpace(index);
            }
        }
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
        Space[] flats = new Flat[n];
	int k = 0;
        for (Floor floor : floors) {
            for (int j = 0; j < floor.getCount(); j++) {
                flats[k] = floor.getSpace(j);
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
            Dwelling dwelling = (Dwelling)object;
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
        Dwelling dwelling = (Dwelling)super.clone();
        dwelling.floors = this.floors.clone();
        for (int i = 0; i < this.getCountFloors(); i++) {
            dwelling.floors[i] = (Floor)this.floors[i].clone();
        }
        return dwelling;
    }
}
