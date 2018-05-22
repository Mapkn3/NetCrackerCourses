package buildings.dwelling;

import java.io.Serializable;
import myException.*;
import myInterface.*;

public class Flat implements Space, Serializable, Cloneable {
    public static final int DEFAULT_ROOMS = 2;
    public static final double DEFAULT_SQUARE = 50.0;

    protected double square;
    protected int rooms;

    public Flat() {
	this.square = DEFAULT_SQUARE;
	this.rooms = DEFAULT_ROOMS;
    }
    public Flat(double flatSquare) {
	if (flatSquare <= 0) {
            throw new InvalidSpaceAreaException();
	}
	this.square = flatSquare;
	this.rooms = DEFAULT_ROOMS;
    }
    public Flat(double flatSquare, int flatRooms) {
        if (flatSquare <= 0) {
            throw new InvalidSpaceAreaException();
	}
	if (flatRooms <= 0) {
            throw new InvalidRoomsCountException();
	}
	this.square = flatSquare;
	this.rooms = flatRooms;
    }
    
    @Override
    public int getCountRooms() {
	return this.rooms;
    }
    @Override
    public void setCountRooms(int newRooms) {
	if (newRooms <= 0) {
            throw new InvalidRoomsCountException();
	}
	this.rooms = newRooms;
    }
    @Override
    public double getSquare() {
	return this.square;
    }
    @Override
    public void setSquare(double newSquare) {
	if (newSquare <= 0) {
            throw new InvalidSpaceAreaException();
	}
	this.square = newSquare;
    }
    
    @Override
    public String toString() {
        return String.format("Flat (%d, %.1f)", this.rooms, this.square);
    }
    
    @Override
    public boolean equals(Object object) {
        boolean isEquals = false;
        if (object.getClass() == Flat.class) {
            Flat flat = (Flat)object;
            if (flat.getCountRooms() == this.getCountRooms() && flat.getSquare() == this.getSquare()) {
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
        return (Flat)super.clone();
    }
}
