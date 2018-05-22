package buildings.office;

import java.io.Serializable;
import myInterface.*;

public class NodeOfficeFloor implements Serializable, Cloneable {
    private Space office;
    private NodeOfficeFloor next = this;
    
    public NodeOfficeFloor() {
	this.office = new Office();
    }
    public NodeOfficeFloor(Space newOffice) {
	this.office = newOffice;
    }
    
    public void setOffice(Space office) {
	this.office = office;
    }
    public Space getOffice() {
	return office;
    }
    public void setNext(NodeOfficeFloor next) {
	this.next = next;
    }
    public NodeOfficeFloor getNext() {
	return next;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return (NodeOfficeFloor)super.clone();
    }
}
