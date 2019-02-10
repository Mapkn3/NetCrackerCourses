package buildings.dwelling;

import myException.SpaceIndexOutOfBoundsException;
import myInterface.Floor;
import myInterface.Space;
import myIterator.FloorIterator;

import java.io.Serializable;
import java.util.Iterator;

public class DwellingFloor implements Floor, Serializable, Cloneable {
    private Space[] flats;

    public DwellingFloor(int countFlats) {
        this.flats = new Flat[countFlats];
        for (int i = 0; i < flats.length; i++) {
            this.flats[i] = new Flat();
        }
    }

    public DwellingFloor(Space[] flats) {
        this.flats = flats;
    }

    @Override
    public int getCount() {
        return this.flats.length;
    }

    @Override
    public double getTotalSquare() {
        double s = 0;
        for (Space flat : flats) {
            s += flat.getSquare();
        }
        return s;
    }

    @Override
    public int getTotalRooms() {
        int r = 0;
        for (Space flat : flats) {
            r += flat.getCountRooms();
        }
        return r;
    }

    @Override
    public Space[] getSpaces() {
        return this.flats;
    }

    @Override
    public Space getSpace(int index) throws SpaceIndexOutOfBoundsException {
        if (!isCorrectFlatIndex(index)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        return this.flats[index];
    }

    @Override
    public void setSpace(int index, Space newSpace) throws SpaceIndexOutOfBoundsException {
        if (!isCorrectFlatIndex(index)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        this.flats[index] = newSpace;
    }

    @Override
    public void addSpace(int index, Space newSpace) throws SpaceIndexOutOfBoundsException {
        if (!isCorrectFlatIndex(index)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Space[] newFlats = new Flat[flats.length + 1];
        for (int i = 0; i < index; i++) {
            newFlats[i] = this.flats[i];
        }
        newFlats[index] = newSpace;
        for (int i = index + 1; i < newFlats.length; i++) {
            newFlats[i] = this.flats[i - 1];
        }
        this.flats = newFlats;
    }

    @Override
    public void deleteSpace(int index) throws SpaceIndexOutOfBoundsException {
        if (!isCorrectFlatIndex(index)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Space[] newFloor = new Flat[flats.length - 1];
        for (int i = 0; i < index; i++) {
            newFloor[i] = this.flats[i];
        }
        for (int i = index + 1; i < newFloor.length; i++) {
            newFloor[i - 1] = this.flats[i];
        }
        this.flats = newFloor;
    }

    @Override
    public Space getBestSpace() {
        int indexOfBestSpace = 0;
        double bestSpace = this.flats[indexOfBestSpace].getSquare();
        for (int i = 1; i < this.flats.length; i++) {
            if (this.flats[i].getSquare() > bestSpace) {
                indexOfBestSpace = i;
                bestSpace = this.flats[i].getSquare();
            }
        }
        return flats[indexOfBestSpace];
    }

    private boolean isCorrectFlatIndex(int index) {
        return (index >= 0) && (index < flats.length);
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
            DwellingFloor dwellingFloor = (DwellingFloor) object;
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
        DwellingFloor dwellingFloor = (DwellingFloor) super.clone();
        dwellingFloor.flats = this.flats.clone();
        for (int i = 0; i < dwellingFloor.getCount(); i++) {
            dwellingFloor.setSpace(i, (Space) this.getSpace(i).clone());
        }
        return dwellingFloor;
    }
}
