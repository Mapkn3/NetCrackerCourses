package buildings.office;

import java.io.Serializable;
import myException.*;
import myInterface.*;

public class Office implements Space, Serializable, Cloneable {
    public static final double DEFAULT_SQUARE = 250.0;
    public static final int DEFAULT_ROOMS = 1;
    
    private double square;
    private int rooms;
    
    public Office() {
	this.square = DEFAULT_SQUARE;
	this.rooms = DEFAULT_ROOMS;
    }
    public Office(double square) {
        if (square <= 0) {
            throw new InvalidSpaceAreaException();
	} else {
            this.square = square;
            this.rooms = DEFAULT_ROOMS;
	}
    }
    public Office(double square, int rooms) {
	if (square <= 0) {
            throw new InvalidSpaceAreaException();
	} else {
            this.square = square;
	}
	if (rooms <= 0) {
            throw new InvalidRoomsCountException();
	} else {
            this.rooms = rooms;
	}
    }
    
    @Override
    public int getCountRooms() {
        return this.rooms;
    }
    @Override
    public void setCountRooms(int newCountRooms) {
	if (newCountRooms <= 0) {
            throw new InvalidRoomsCountException();
	} else {
            this.rooms = newCountRooms;
	}
    }
    @Override
    public double getSquare() {
	return this.square;
    }
    @Override
    public void setSquare(double newSquare) {
        if (newSquare <= 0) {
            throw new InvalidSpaceAreaException();
	} else {
            this.square = newSquare;
	}
    }
    
    @Override
    public String toString() {
        return String.format("Office (%d, %.1f)", this.rooms, this.square);
    }
    
    @Override
    public boolean equals(Object object) {
        boolean isEquals = false;
        if (object.getClass() == Office.class) {
            Office office = (Office)object;
            if (office.getCountRooms() == this.getCountRooms() && office.getSquare() == this.getSquare()) {
                isEquals = true;
            }
        }
        return isEquals;
    }

    @Override
    public int hashCode() {
        return this.rooms ^ (int) (Double.doubleToLongBits(this.square) ^ (Double.doubleToLongBits(this.square) >>> 32));
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return (Office)super.clone();
    }
}
