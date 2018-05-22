package buildings.dwelling;

import java.io.Serializable;
import java.util.Iterator;
import myInterface.*;
import myException.*;
import myIterator.FloorIterator;

public class DwellingFloor implements Floor, Serializable, Cloneable {
    protected Space[] floor;
    
    public DwellingFloor(int countFlats) {
        this.floor = new Flat[countFlats];
	for (int i = 0; i < countFlats; i++) {
            this.floor[i] = new Flat();
	}
    }
    public DwellingFloor(Space[] flats) {
        this.floor = flats;
    }

    @Override
    public int getCount() {
	return this.floor.length;
    }
    @Override
    public double getTotalSquare() {
	double s = 0;
        for (Space flat : floor) {
            s += flat.getSquare();
        }
	return s;
    }
    @Override
    public int getTotalRooms() {
	int r = 0;
        for (Space flat : floor) {
            r += flat.getCountRooms();
        }
	return r;
    }
    @Override
    public Space[] getSpaces() {
	return this.floor;
    }
    @Override
    public Space getSpace(int index) {
        if ((index < 0) || (index >= floor.length)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        return this.floor[index];
    }
    @Override
    public void setSpace(int index, Space newSpace) {
	if ((index < 0) || (index >= floor.length)) {
            throw new SpaceIndexOutOfBoundsException();
	}
	this.floor[index] = newSpace;
    }
    @Override
    public void addSpace(int index, Space newSpace) {
	if ((index < 0) || (index >= floor.length)) {
            throw new SpaceIndexOutOfBoundsException();
	}
	Space[] newFloor = new Flat[floor.length + 1];
	for (int i = 0; i < index; i++) {
            newFloor[i] = this.floor[i];
	}
	newFloor[index] = newSpace;
	for (int i = index + 1; i < newFloor.length; i++)
	{
            newFloor[i] = this.floor[i - 1];
	}
	this.floor = newFloor;
    }
    @Override
    public void deleteSpace(int index) {
	if ((index < 0) || (index >= floor.length)) {
            throw new SpaceIndexOutOfBoundsException();
	}
	Space[] newFloor = new Flat[floor.length - 1];
	for (int i = 0; i < index; i++) {
            newFloor[i] = this.floor[i];
	}
	for (int i = index + 1; i < newFloor.length; i++) {
            newFloor[i - 1] = this.floor[i];
	}
	this.floor = newFloor;
    }
    @Override
    public Space getBestSpace() {
        int ibs = 0;
	double bs = this.floor[ibs].getSquare();
	for (int i = 1; i < floor.length; i++) {
            if (this.floor[i].getSquare() > bs) {
		ibs = i;
		bs = this.floor[i].getSquare();
            }
	}
	return floor[ibs];
    }

    @Override
    public Iterator<Space> iterator() {
        return new FloorIterator(this);
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("DwellingFloor");
        str.append(String.format(" (%d", this.getCount()));
        for (Space space : this.getSpaces()) {
            str.append(String.format(", %s", space.toString()));
        }
        str.append(")");
        return str.toString();
    }
    
    @Override
    public boolean equals(Object object) {
        boolean isEquals = false;
        if (object.getClass() == DwellingFloor.class) {
            DwellingFloor dwellingFloor = (DwellingFloor)object;
            if (dwellingFloor.getCount() == this.getCount()) {
                for (int i = 0; i < dwellingFloor.getCount(); i++) {
                    if (!dwellingFloor.getSpace(i).equals(this.getSpace(i))) {
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
        int hash = this.getCount();
        for (Space space : this.getSpaces()) {
            hash ^= space.hashCode();
        }
        return hash;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        DwellingFloor dwellingFloor = (DwellingFloor)super.clone();
        dwellingFloor.floor = this.floor.clone();
        for (int i = 0; i < dwellingFloor.getCount(); i++) {
            dwellingFloor.floor[i] = (Space)this.floor[i].clone();
        }
        return dwellingFloor;
    }
}
