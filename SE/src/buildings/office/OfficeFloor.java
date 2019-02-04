package buildings.office;

import myException.SpaceIndexOutOfBoundsException;
import myInterface.Floor;
import myInterface.Space;
import myIterator.FloorIterator;

import java.io.Serializable;
import java.util.Iterator;

public class OfficeFloor implements Floor, Serializable, Cloneable {
    private OfficeNode head;

    public OfficeFloor(int count) {
        try {
            for (int i = 0; i < count; i++) {
                this.addNode(i, new OfficeNode());
            }
        } catch (SpaceIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public OfficeFloor(Space[] offices) {
        try {
            for (int i = 0; i < offices.length; i++) {
                addNode(i, new OfficeNode(offices[i]));
            }
        } catch (SpaceIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private OfficeNode getNode(int index) throws SpaceIndexOutOfBoundsException {
        if (index < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        OfficeNode temp = this.head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
            if (temp == this.head) {
                throw new SpaceIndexOutOfBoundsException();
            }
        }
        return temp;
    }

    private OfficeNode getPrevForIndex(int index) {
        int prevIndex = index - 1;
        if (prevIndex < 0) {
            prevIndex += getCount();
        }
        return getNode(prevIndex);
    }

    private void addNode(int index, OfficeNode floor) throws SpaceIndexOutOfBoundsException {
        try {
            if (this.head != null) {
                OfficeNode prev = getPrevForIndex(index);
                floor.setNext(prev.getNext());
                prev.setNext(floor);
                if (index == 0) {
                    this.head = floor;
                }
            } else {
                this.head = floor;
                this.head.setNext(head);
            }
        } catch (SpaceIndexOutOfBoundsException e) {
            throw e;
        }
    }

    private void deleteNode(int index) throws SpaceIndexOutOfBoundsException {
        try {
            OfficeNode del = getNode(index);
            OfficeNode prev = getPrevForIndex(index);
            prev.setNext(del.getNext());
            del.setNext(null);
            del.setOffice(null);
        } catch (SpaceIndexOutOfBoundsException e) {
            throw e;
        }
    }

    @Override
    public int getCount() {
        int count = 0;
        if (this.head != null) {
            OfficeNode temp = this.head;
            do {
                count++;
                temp = temp.getNext();
            } while (temp != this.head);
        }
        return count;
    }

    @Override
    public double getTotalSquare() {
        double square = 0;
        if (this.head != null) {
            OfficeNode temp = this.head;
            do {
                square += temp.getOffice().getSquare();
                temp = temp.getNext();
            } while (temp != this.head);
        }
        return square;
    }

    @Override
    public int getTotalRooms() {
        int rooms = 0;
        if (this.head != null) {
            OfficeNode temp = this.head;
            do {
                rooms += temp.getOffice().getCountRooms();
                temp = temp.getNext();
            } while (temp != this.head);
        }
        return rooms;
    }

    @Override
    public Space[] getSpaces() {
        Space[] offices = new Office[this.getCount()];
        if (this.head != null) {
            OfficeNode temp = this.head;
            for (int i = 0; i < offices.length; i++) {
                offices[i] = temp.getOffice();
                temp = temp.getNext();
            }
        }
        return offices;
    }

    @Override
    public Space getSpace(int index) {
        return getNode(index).getOffice();
    }

    @Override
    public void setSpace(int index, Space newSpace) {
        getNode(index).setOffice(newSpace);
    }

    @Override
    public void addSpace(int index, Space newSpace) {
        try {
            addNode(index, new OfficeNode(newSpace));
            setSpace(index, newSpace);
        } catch (SpaceIndexOutOfBoundsException e) {
            throw e;
        }
    }

    @Override
    public void deleteSpace(int index) {
        try {
            deleteNode(index);
        } catch (SpaceIndexOutOfBoundsException e) {
            throw e;
        }
    }

    @Override
    public Space getBestSpace() {
        Space bestSpaceOffice = null;
        if (this.head != null) {
            OfficeNode temp = this.head;
            int countOffices = this.getCount();
            bestSpaceOffice = temp.getOffice();
            for (int i = 0; i < countOffices; i++) {
                temp = temp.getNext();
                if (temp.getOffice().getSquare() > bestSpaceOffice.getSquare()) {
                    bestSpaceOffice = temp.getOffice();
                }
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
            OfficeFloor officeFloor = (OfficeFloor) object;
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
        OfficeFloor officeFloor = (OfficeFloor) super.clone();
        officeFloor.head = new OfficeNode();
        for (int i = 0; i < this.getCount(); i++) {
            officeFloor.setSpace(i, (Space) this.getSpace(i).clone());
        }
        return officeFloor;
    }
}
