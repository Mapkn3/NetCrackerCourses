package buildings.office;

import myInterface.Floor;

import java.io.Serializable;

public class OfficeFloorNode implements Serializable, Cloneable {
    private Floor officeFloor;
    private OfficeFloorNode prev = this;
    private OfficeFloorNode next = this;

    public OfficeFloorNode() {
        this.officeFloor = new OfficeFloor(12);
    }

    public OfficeFloorNode(int count) {
        this.officeFloor = new OfficeFloor(count);
    }

    public OfficeFloorNode(Floor newFloor) {
        this.officeFloor = newFloor;
    }

    public Floor getOfficeFloor() {
        return this.officeFloor;
    }

    public void setOfficeFloor(Floor floor) {
        this.officeFloor = floor;
    }

    public OfficeFloorNode getPrev() {
        return this.prev;
    }

    public void setPrev(OfficeFloorNode prev) {
        this.prev = prev;
    }

    public OfficeFloorNode getNext() {
        return this.next;
    }

    public void setNext(OfficeFloorNode next) {
        this.next = next;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
