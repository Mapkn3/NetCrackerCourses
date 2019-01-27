package buildings.office;

import myInterface.Space;

import java.io.Serializable;

public class OfficeNode implements Serializable, Cloneable {
    private Space office;
    private OfficeNode next = this;

    public OfficeNode() {
        this.office = new Office();
    }

    public OfficeNode(Space newOffice) {
        this.office = newOffice;
    }

    public Space getOffice() {
        return office;
    }

    public void setOffice(Space office) {
        this.office = office;
    }

    public OfficeNode getNext() {
        return next;
    }

    public void setNext(OfficeNode next) {
        this.next = next;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
