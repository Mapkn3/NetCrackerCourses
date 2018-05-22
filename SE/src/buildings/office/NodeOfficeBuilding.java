package buildings.office;

import java.io.Serializable;
import myInterface.*;

public class NodeOfficeBuilding implements Serializable, Cloneable {
    private Floor officeFloor;
    private NodeOfficeBuilding prev = this;
    private NodeOfficeBuilding next = this;
    
    public NodeOfficeBuilding() {
	this.officeFloor = new OfficeFloor(12);
    }
    public NodeOfficeBuilding(int count) {
        this.officeFloor = new OfficeFloor(count);
    }
    public NodeOfficeBuilding(Floor newFloor) {
        this.officeFloor = newFloor;
    }
    
    public void setOfficeFloor(Floor floor) {
	this.officeFloor = floor;
    }
    public Floor getOfficeFloor() {
	return this.officeFloor;
    }
    public void setPrev(NodeOfficeBuilding prev) {
        this.prev = prev;
    }
    public NodeOfficeBuilding getPrev() {
	return this.prev;
    }
    public void setNext(NodeOfficeBuilding next) {
	this.next = next;
    }
    public NodeOfficeBuilding getNext() {
	return this.next;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return (NodeOfficeBuilding)super.clone();
    }
}
