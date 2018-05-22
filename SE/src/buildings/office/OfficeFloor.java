package buildings.office;

import java.io.Serializable;
import java.util.Iterator;
import myException.*;
import myInterface.*;
import myIterator.FloorIterator;

public class OfficeFloor implements Floor, Serializable, Cloneable {
    private NodeOfficeFloor head;
    
    private NodeOfficeFloor getNode(int index) {
	if (index < 0) {
            throw new SpaceIndexOutOfBoundsException();
	}
	NodeOfficeFloor temp = this.head;
	for (;  index > 0; index--) {
            temp = temp.getNext();
            if (temp == this.head) {
		throw new SpaceIndexOutOfBoundsException();
            }
	}
	return temp;
    }
    private void addNode(int index, NodeOfficeFloor floor) {
	try {
            if (this.head != null) {
		NodeOfficeFloor prev;
		if (index == 0) {
                    for (prev = this.head; prev.getNext() != this.head; prev = prev.getNext()) {}
		} else {
                    prev = this.getNode(index - 1);
		}
		floor.setNext(prev.getNext());
		prev.setNext(floor);
		if (index == 0) {
                    this.head = floor;
		}
            } else {
                this.head = floor;
                this.head.setNext(head);
            }
	}
	catch (SpaceIndexOutOfBoundsException e) {
            throw e;
        }
    }
    private void deleteNode(int index) {
	try {
            NodeOfficeFloor del = this.getNode(index);
            NodeOfficeFloor prev;
            for (prev = del; prev.getNext() != del; prev = prev.getNext()) {}
		prev.setNext(del.getNext());
		del.setNext(null);
		del.setOffice(null);
        }
        catch (SpaceIndexOutOfBoundsException e) {
            throw e;
	}
    }
    
    public OfficeFloor(int count) {
	try {
            for (int i = 0; i < count; i++) {
		this.addNode(i, new NodeOfficeFloor());
            }
	}
	catch (SpaceIndexOutOfBoundsException e) {
            throw e;
	}
    }
    public OfficeFloor(Space[] offices) {
	try {
            for (int i = 0; i < offices.length; i++) {
		addNode(i, new NodeOfficeFloor(offices[i]));
            }
	}
	catch (SpaceIndexOutOfBoundsException e) {
            throw e;
	}
    }
    
    @Override
    public int getCount() {
	int count = 1;
	if (this.head != null) {
            for (NodeOfficeFloor temp = this.head; temp.getNext() != this.head; count++, temp = temp.getNext()) {}
	}
	return count;
    }
    @Override
    public double getTotalSquare() {
        double square = 0;
	NodeOfficeFloor temp = this.head;
	do {
            square += temp.getOffice().getSquare();
            temp = temp.getNext();
	} while (temp != this.head);
	return square;
    }
    @Override
    public int getTotalRooms() {
	int rooms = 0;
	NodeOfficeFloor temp = this.head;
	do
        {
            rooms += temp.getOffice().getCountRooms();
            temp = temp.getNext();
	} while (temp != this.head);
	return rooms;
    }
    @Override
    public Space[] getSpaces() {
	Space[] offices = new Office[this.getCount()];
	NodeOfficeFloor temp = this.head;
	for (int i = 0; i < this.getCount(); i++) {
            offices[i] = temp.getOffice();
            temp = temp.getNext();
	}
	return offices;
    }
    @Override
    public Space getSpace(int index) {
        NodeOfficeFloor temp = this.head;
	for (; index > 0; index--) {
            temp = temp.getNext();
            if (temp == this.head) {
		throw new SpaceIndexOutOfBoundsException();
            }
	}
	return temp.getOffice();
    }
    @Override
    public void setSpace(int index, Space newSpace) {
	NodeOfficeFloor temp = this.head;
	for (; index > 0; index--) {
            temp = temp.getNext();
            if (temp == this.head) {
		throw new SpaceIndexOutOfBoundsException();
            }
	}
	temp.setOffice(newSpace);
    }
    @Override
    public void addSpace(int index, Space newSpace) {
	try {
            this.addNode(index, new NodeOfficeFloor());
            this.setSpace(index, newSpace);
	}
	catch (SpaceIndexOutOfBoundsException e) {
            throw e;
	}
    }
    @Override
    public void deleteSpace(int index) {
	try {
            this.setSpace(index, null);
            this.deleteNode(index);
        }
	catch (SpaceIndexOutOfBoundsException e) {
            throw e;
	}
    }
    @Override
    public Space getBestSpace() {
	NodeOfficeFloor temp = this.head;
	Space bestSpaceOffice = temp.getOffice();
	for (int i = 0; i < this.getCount(); i++) {
            temp = temp.getNext();
            if (temp.getOffice().getSquare() > bestSpaceOffice.getSquare()) {
		bestSpaceOffice = temp.getOffice();
            }
	}
	return bestSpaceOffice;
    }

    @Override
    public Iterator<Space> iterator() {
        return new FloorIterator(this);
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("OfficeFloor");
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
        if (object.getClass() == OfficeFloor.class) {
            OfficeFloor officeFloor = (OfficeFloor)object;
            if (officeFloor.getCount() == this.getCount()) {
                for (int i = 0; i < officeFloor.getCount(); i++) {
                    if (!officeFloor.getSpace(i).equals(this.getSpace(i))) {
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
        OfficeFloor officeFloor = (OfficeFloor)super.clone();
        officeFloor.head = new NodeOfficeFloor();
        for (int i = 0; i < this.getCount(); i++) {
            officeFloor.setSpace(i, (Space)this.getSpace(i).clone());
        }
        return officeFloor;
    }
}
