package buildings.office;

import myException.InvalidRoomsCountException;
import myException.InvalidSpaceAreaException;
import myInterface.Space;

import java.io.Serializable;

public class Office implements Space, Serializable, Cloneable {
    public static final double DEFAULT_SQUARE = 250.0;
    public static final int DEFAULT_ROOMS = 1;

    private double square;
    private int countRooms;

    public Office() {
        this(DEFAULT_SQUARE, DEFAULT_ROOMS);
    }

    public Office(double square) throws InvalidSpaceAreaException {
        this(square, DEFAULT_ROOMS);
    }

    public Office(double square, int countRooms) {
        if (square <= 0) {
            throw new InvalidSpaceAreaException();
        }
        if (countRooms <= 0) {
            throw new InvalidRoomsCountException();
        }
        this.square = square;
        this.countRooms = countRooms;
    }

    @Override
    public int getCountRooms() {
        return this.countRooms;
    }

    @Override
    public void setCountRooms(int newCountRooms) throws InvalidRoomsCountException {
        if (newCountRooms <= 0) {
            throw new InvalidRoomsCountException();
        } else {
            this.countRooms = newCountRooms;
        }
    }

    @Override
    public double getSquare() {
        return this.square;
    }

    @Override
    public void setSquare(double newSquare) throws InvalidSpaceAreaException {
        if (newSquare <= 0) {
            throw new InvalidSpaceAreaException();
        } else {
            this.square = newSquare;
        }
    }

    @Override
    public String toString() {
        return String.format("Office (%d, %.1f)", this.countRooms, this.square);
    }

    @Override
    public boolean equals(Object object) {
        boolean isEquals = false;
        if (object.getClass() == Office.class) {
            Office office = (Office) object;
            if (office.getCountRooms() == this.getCountRooms() && office.getSquare() == this.getSquare()) {
                isEquals = true;
            }
        }
        return isEquals;
    }

    @Override
    public int hashCode() {
        long square = Double.doubleToLongBits(this.square);
        return this.countRooms ^ (int) (square ^ (square >>> 32));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Office office = (Office) super.clone();
        office.setSquare(this.square);
        office.setCountRooms(this.countRooms);
        return office;
    }
}
