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

    public OfficeBuilding(int count, int[] countOffices) {
        try {
            for (int i = 0; i < count; i++) {
                this.addNode(i, new OfficeFloorNode(countOffices[i]));
            }
        } catch (SpaceIndexOutOfBoundsException | FloorIndexOutOfBoundsException e) {
            throw e;
        }
    }

    public OfficeBuilding(Floor[] floors) {
        try {
            for (int i = 0; i < floors.length; i++) {
                this.addNode(i, new OfficeFloorNode(floors[i]));
            }
        } catch (FloorIndexOutOfBoundsException e) {
            throw e;
        }
    }

    private OfficeFloorNode getNode(int index) {
	if (index < 0) {
            throw new FloorIndexOutOfBoundsException();
	}
        OfficeFloorNode temp = this.head;
	for (; index > 0; index--) {
            temp = temp.getNext();
	}
	return temp;
    }

    private void addNode(int index, OfficeFloorNode building) {
        try {
            if (this.head != null) {
                OfficeFloorNode next = this.getNode(index);
                OfficeFloorNode prev = next.getPrev();
		building.setPrev(next.getPrev());
		building.setNext(prev.getNext());
		prev.setNext(building);
		next.setPrev(building);
		if (index == 0) {
                    this.head = building;
		}
            } else {
                building.setPrev(building);
                building.setNext(building);
		this.head = building;
            }
	}
	catch (FloorIndexOutOfBoundsException e) {
            throw e;
	}
    }

    private void deleteNode(int index) {
	try {
        OfficeFloorNode del = this.getNode(index);
        OfficeFloorNode prev = del.getPrev();
        OfficeFloorNode next = del.getNext();
            if (index == 0) {
		this.head = next;
            }
            prev.setNext(del.getNext());
            next.setPrev(del.getPrev());
            del.setPrev(null);
            del.setNext(null);
	}
	catch (FloorIndexOutOfBoundsException e) {
            throw e;
	}
    }
    
    @Override
    public int getCountFloors() {
        int count = 0;
        OfficeFloorNode temp = this.head;
	do {
            count++;
            temp = temp.getNext();
	} while(temp.getNext() != this.head);
	return count;
    }
    @Override
    public int getCountSpaces() {
	int count = 0;
        OfficeFloorNode temp = this.head;
	do {
            count += temp.getOfficeFloor().getCount();
            temp = temp.getNext();
	} while(temp.getNext() != this.head);
	return count;
    }
    @Override
    public double getTotalSquare() {
	double count = 0;
        OfficeFloorNode temp = this.head;
	do {
            count += temp.getOfficeFloor().getTotalSquare();
            temp = temp.getNext();
	} while(temp.getNext() != this.head);
	return count;
    }
    @Override
    public int getTotalRooms() {
	int count = 0;
        OfficeFloorNode temp = this.head;
	do {
            count += temp.getOfficeFloor().getTotalRooms();
            temp = temp.getNext();
	} while(temp.getNext() != this.head);
	return count;
    }
    @Override
    public Floor[] getFloors() {
	int i = 0;
	Floor[] floors = new OfficeFloor[this.getCountFloors()];
        OfficeFloorNode node = this.head;
	do {
            floors[i] = node.getOfficeFloor();
            i++;
            node = node.getNext();
	} while(node.getNext() != this.head);
	return floors;
    }
    @Override
    public Floor getFloor(int index) {
        OfficeFloorNode node = this.head;
	while (index > 1) {
            node = node.getNext();
            index--;
            if (node == this.head) {
		throw new FloorIndexOutOfBoundsException();
            }
	}
	return node.getOfficeFloor();
    }
    @Override
    public void setFloor(int index, Floor newFloor) {
        OfficeFloorNode node = this.head;
	while (index > 1) {
            node = node.getNext();
            index--;
            if (node == this.head) {
		throw new FloorIndexOutOfBoundsException();
            }
	}
	node.setOfficeFloor(newFloor);
    }
    @Override
    public Space getSpace(int index) {
	try {
        OfficeFloorNode floor = this.head;
            while (index > floor.getOfficeFloor().getCount()) {
		index -= floor.getOfficeFloor().getCount();
		floor = floor.getNext();
		if (floor == this.head) {
                    throw new FloorIndexOutOfBoundsException();
		}
            }
            return floor.getOfficeFloor().getSpace(index);
	}
	catch (SpaceIndexOutOfBoundsException e) {
            throw e;
	}
    }
    @Override
    public void setSpace(int index, Space newOffice) {
	try {
        OfficeFloorNode floor = this.head;
            while (index > floor.getOfficeFloor().getCount()) {
		index -= floor.getOfficeFloor().getCount();
		floor = floor.getNext();
		if (floor == this.head) {
                    throw new FloorIndexOutOfBoundsException();
		}
            }
            floor.getOfficeFloor().setSpace(index, newOffice);
	}
	catch (SpaceIndexOutOfBoundsException e) {
            throw e;
	}
    }
    @Override
    public void addSpace(int index, Space newOffice) {
	try {
        OfficeFloorNode floor = this.head;
            while (index > floor.getOfficeFloor().getCount()) {
                index -= floor.getOfficeFloor().getCount();
		floor = floor.getNext();
		if (floor == this.head) {
                    throw new FloorIndexOutOfBoundsException();
		}	
            }
            floor.getOfficeFloor().addSpace(index, newOffice);
	}
	catch (SpaceIndexOutOfBoundsException e) {
            throw e;
	}
    }
    @Override
    public void deleteSpace(int index) {
	try {
        OfficeFloorNode floor = this.head;
            int i = 0;
            while (index > floor.getOfficeFloor().getCount()) {
		index -= floor.getOfficeFloor().getCount();
		floor = floor.getNext();
		if (floor == this.head) {
                    throw new FloorIndexOutOfBoundsException();
		}
                i++;
            }
            floor.getOfficeFloor().deleteSpace(index);
            if (floor.getOfficeFloor().getCount() == 0) {
                this.deleteNode(i);
            }
	}
	catch (SpaceIndexOutOfBoundsException | FloorIndexOutOfBoundsException e) {
            throw e;
	}
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
	}
	catch (FloorIndexOutOfBoundsException e) {
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
            OfficeBuilding officeBuilding = (OfficeBuilding)object;
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
        OfficeBuilding officeBuilding = (OfficeBuilding)super.clone();
        officeBuilding.head = new OfficeFloorNode();
        for (int i = 0; i < this.getCountFloors(); i++) {
            officeBuilding.setFloor(i, this.getFloor(i));
        }
        return officeBuilding;
    }
}
