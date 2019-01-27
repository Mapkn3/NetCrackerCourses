package buildings.dwelling;

import myException.InvalidRoomsCountException;
import myException.InvalidSpaceAreaException;
import myInterface.Space;

import java.io.Serializable;

public class Flat implements Space, Serializable, Cloneable {
    public static final int DEFAULT_ROOMS = 2;
    public static final double DEFAULT_SQUARE = 50.0;

    private double square;
    private int countRooms;

    public Flat() {
        this(DEFAULT_SQUARE, DEFAULT_ROOMS);
    }

    public Flat(double square) {
        this(square, DEFAULT_ROOMS);
    }

    public Flat(double square, int countRooms) throws InvalidSpaceAreaException, InvalidRoomsCountException {
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
    public void setCountRooms(int newCountRooms) {
        if (newCountRooms < 1) {
            throw new InvalidRoomsCountException();
        }
        this.countRooms = newCountRooms;
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
        return String.format("Flat (%d, %.1f)", this.countRooms, this.square);
    }

    @Override
    public boolean equals(Object object) {
        boolean isEquals = false;
        if (object.getClass() == Flat.class) {
            Flat flat = (Flat) object;
            if (flat.getCountRooms() == this.getCountRooms() && flat.getSquare() == this.getSquare()) {
                isEquals = true;
            }
        }
        return isEquals;
    }

    @Override
    public int hashCode() {
        return this.countRooms ^ (int) (Double.doubleToLongBits(this.square) ^ (Double.doubleToLongBits(this.square) >>> 32));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
