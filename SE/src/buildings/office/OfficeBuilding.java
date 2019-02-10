package buildings.office;

import myException.FloorIndexOutOfBoundsException;
import myException.SpaceIndexOutOfBoundsException;
import myInterface.Building;
import myInterface.Floor;
import myInterface.Space;
import myIterator.BuildingIterator;

import java.io.Serializable;
import java.util.Iterator;

public class OfficeBuilding implements Building, Serializable, Cloneable {
    private OfficeFloorNode head;

    public OfficeBuilding(int count, int[] countOffices) throws SpaceIndexOutOfBoundsException, FloorIndexOutOfBoundsException {
        try {
            for (int i = count - 1; i >= 0; i--) {
                this.addNode(0, new OfficeFloorNode(countOffices[i]));
            }
        } catch (SpaceIndexOutOfBoundsException | FloorIndexOutOfBoundsException e) {
            throw e;
        }
    }

    public OfficeBuilding(Floor[] floors) throws FloorIndexOutOfBoundsException {
        try {
            for (int i = floors.length - 1; i >= 0; i--) {
                this.addNode(0, new OfficeFloorNode(floors[i]));
            }
        } catch (FloorIndexOutOfBoundsException e) {
            throw e;
        }
    }

    private OfficeFloorNode getNode(int index) throws FloorIndexOutOfBoundsException {
        if (index < 0) {
            throw new FloorIndexOutOfBoundsException();
        }
        OfficeFloorNode temp = this.head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
            if (temp == this.head) {
                throw new FloorIndexOutOfBoundsException();
            }
        }
        return temp;
    }

    private void addNode(int index, OfficeFloorNode floor) throws FloorIndexOutOfBoundsException {
        if (isCorrectFloorIndex(index)) {
            try {
                if (this.head != null) {
                    OfficeFloorNode next = getNode(index);
                    OfficeFloorNode prev = next.getPrev();
                    floor.setPrev(prev);
                    floor.setNext(next);
                    prev.setNext(floor);
                    next.setPrev(floor);
                    if (index == 0) {
                        this.head = floor;
                    }
                } else {
                    floor.setPrev(floor);
                    floor.setNext(floor);
                    this.head = floor;
                }
            } catch (FloorIndexOutOfBoundsException e) {
                throw e;
            }
        } else {
            throw new FloorIndexOutOfBoundsException();
        }
    }

    private void deleteNode(int index) throws FloorIndexOutOfBoundsException {
        try {
            OfficeFloorNode del = getNode(index);
            OfficeFloorNode prev = del.getPrev();
            OfficeFloorNode next = del.getNext();
            prev.setNext(next);
            next.setPrev(prev);
            del.setPrev(null);
            del.setNext(null);
            if (index == 0) {
                this.head = next;
            }
        } catch (FloorIndexOutOfBoundsException e) {
            throw e;
        }
    }

    @Override
    public int getCountFloors() {
        int count = 0;
        if (this.head != null) {
            OfficeFloorNode temp = this.head;
            do {
                count++;
                temp = temp.getNext();
            } while (temp != this.head);
        }
        return count;
    }

    @Override
    public int getCountSpaces() {
        int count = 0;
        if (this.head != null) {
            OfficeFloorNode temp = this.head;
            do {
                count += temp.getOfficeFloor().getCount();
                temp = temp.getNext();
            } while (temp != this.head);
        }
        return count;
    }

    @Override
    public double getTotalSquare() {
        double count = 0;
        if (this.head != null) {
            OfficeFloorNode temp = this.head;
            do {
                count += temp.getOfficeFloor().getTotalSquare();
                temp = temp.getNext();
            } while (temp != this.head);
        }
        return count;
    }

    @Override
    public int getTotalRooms() {
        int count = 0;
        if (this.head != null) {
            OfficeFloorNode temp = this.head;
            do {
                count += temp.getOfficeFloor().getTotalRooms();
                temp = temp.getNext();
            } while (temp != this.head);
        }
        return count;
    }

    @Override
    public Floor[] getFloors() {
        Floor[] floors = new OfficeFloor[this.getCountFloors()];
        if (this.head != null) {
            OfficeFloorNode node = this.head;
            for (int i = 0; i < floors.length; i++) {
                floors[i] = node.getOfficeFloor();
                node = node.getNext();
            }
        }
        return floors;
    }

    @Override
    public Floor getFloor(int index) throws FloorIndexOutOfBoundsException {
        if (isCorrectFloorIndex(index)) {
            return getNode(index).getOfficeFloor();
        } else {
            throw new FloorIndexOutOfBoundsException();
        }
    }

    @Override
    public void setFloor(int index, Floor newFloor) throws FloorIndexOutOfBoundsException {
        if (isCorrectFloorIndex(index)) {
            getNode(index).setOfficeFloor(newFloor);
        } else {
            throw new FloorIndexOutOfBoundsException();
        }
    }

    @Override
    public Space getSpace(int index) throws SpaceIndexOutOfBoundsException {
        if (isCorrectOfficeIndex(index)) {
            return getNode(getFloorIndexForOfficeIndex(index)).getOfficeFloor().getSpace(getLocalOfficeIndexOnFloor(index));
        } else {
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    @Override
    public void setSpace(int index, Space newOffice) throws SpaceIndexOutOfBoundsException {
        if (isCorrectOfficeIndex(index)) {
            getNode(getFloorIndexForOfficeIndex(index)).getOfficeFloor().setSpace(getLocalOfficeIndexOnFloor(index), newOffice);
        } else {
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    @Override
    public void addSpace(int index, Space newOffice) throws SpaceIndexOutOfBoundsException {
        if (isCorrectOfficeIndex(index)) {
            getNode(getFloorIndexForOfficeIndex(index)).getOfficeFloor().addSpace(getLocalOfficeIndexOnFloor(index), newOffice);
        } else {
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    @Override
    public void deleteSpace(int index) {
        if (isCorrectOfficeIndex(index)) {
            getNode(getFloorIndexForOfficeIndex(index)).getOfficeFloor().deleteSpace(getLocalOfficeIndexOnFloor(index));
            if (getNode(getFloorIndexForOfficeIndex(index)).getOfficeFloor().getCount() == 0) {
                deleteNode(getFloorIndexForOfficeIndex(index));
            }
        } else {
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    private boolean isCorrectOfficeIndex(int officeIndex) {
        return (officeIndex >= 0) && (officeIndex < getCountSpaces());
    }

    private boolean isCorrectFloorIndex(int floorIndex) {
        return (floorIndex == 0) || ((floorIndex > 0) && (floorIndex < getCountFloors()));
    }

    private int getLocalOfficeIndexOnFloor(int index) throws SpaceIndexOutOfBoundsException, FloorIndexOutOfBoundsException {
        if (isCorrectOfficeIndex(index)) {
            OfficeFloorNode floor = this.head;
            while (index >= floor.getOfficeFloor().getCount()) {
                index -= floor.getOfficeFloor().getCount();
                floor = floor.getNext();
                if (floor == this.head) {
                    throw new FloorIndexOutOfBoundsException();
                }
            }
            return index;
        } else {
            throw new SpaceIndexOutOfBoundsException();
        }
    }

    private int getFloorIndexForOfficeIndex(int index) throws FloorIndexOutOfBoundsException {
        int floorIndex = 0;
        OfficeFloorNode floor = this.head;
        while (index >= floor.getOfficeFloor().getCount()) {
            index -= floor.getOfficeFloor().getCount();
            floor = floor.getNext();
            if (floor == this.head) {
                throw new FloorIndexOutOfBoundsException();
            }
            floorIndex++;
        }
        return floorIndex;
    }

    @Override
    public Space getBestSpace() {
        try {
            Space[] bestOffices = new Office[this.getCountFloors()];
            for (int i = 0; i < this.getCountFloors(); i++) {
                bestOffices[i] = this.getFloor(i).getBestSpace();
            }
            Space best = bestOffices[0];
            for (Space office : bestOffices) {
                if (office.getSquare() > best.getSquare()) {
                    best = office;
                }
            }
            return best;
        } catch (FloorIndexOutOfBoundsException e) {
            throw e;
        }
    }

    @Override
    public Space[] getSortedSpace() {
        int count = 0;
        OfficeFloorNode temp = this.head;
        for (int i = 0; i < this.getCountFloors(); i++) {
            count += temp.getOfficeFloor().getCount();
            temp = temp.getNext();
        }
        Space[] SortedOffices = new Office[count];
        int k = 0;
        Floor[] floors = this.getFloors();
        Space[] offices;
        for (Floor floor : floors) {
            offices = floor.getSpaces();
            for (Space office : offices) {
                SortedOffices[k] = office;
                k++;
            }
        }
        Space t;
        for (int i = 0; i < k - 1; i++) {
            for (int j = 0; j < k - i - 1; j++) {
                if (SortedOffices[j].getSquare() < SortedOffices[j + 1].getSquare()) {
                    t = SortedOffices[j];
                    SortedOffices[j] = SortedOffices[j + 1];
                    SortedOffices[j + 1] = t;
                }
            }
        }
        return SortedOffices;
    }

    @Override
    public Iterator<Floor> iterator() {
        return new BuildingIterator(this);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("OfficeBuilding");
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
        if (object.getClass() == OfficeBuilding.class) {
            OfficeBuilding officeBuilding = (OfficeBuilding) object;
            if (officeBuilding.getCountFloors() == this.getCountFloors()) {
                for (int i = 0; i < officeBuilding.getCountFloors(); i++) {
                    if (!officeBuilding.getFloor(i).equals(this.getFloor(i))) {
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
        OfficeBuilding officeBuilding = (OfficeBuilding) super.clone();
        officeBuilding.head = new OfficeFloorNode();
        for (int i = 0; i < this.getCountFloors(); i++) {
            officeBuilding.setFloor(i, (Floor) this.getFloor(i).clone());
        }
        return officeBuilding;
    }
}
