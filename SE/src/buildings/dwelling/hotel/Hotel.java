package buildings.dwelling.hotel;

import buildings.dwelling.Dwelling;
import myInterface.Floor;
import myInterface.Space;

public class Hotel extends Dwelling implements Cloneable {

    public Hotel(int countFloors, int[] countFlats) {
        super(countFloors, countFlats);
    }

    public Hotel(Floor[] newFloors) {
        super(newFloors);
    }

    private boolean isHotelFloor(Floor floor) {
        return floor.getClass() == HotelFloor.class;
    }

    public int showStars() {
        int stars = 0;
        for (Floor floor : this.getFloors()) {
            if (isHotelFloor(floor) && ((HotelFloor) floor).getStars() > stars) {
                stars = ((HotelFloor) floor).getStars();
            }
        }
        return stars;
    }

    @Override
    public Space getBestSpace() {
        double[] coeff = {0.25, 0.5, 1, 1.25, 1.5};
        Space bestSpace = null;
        double bestValue = 0;
        double value;
        for (Floor floor : getFloors()) {
            if (isHotelFloor(floor)) {
                value = floor.getBestSpace().getSquare() * coeff[((HotelFloor) floor).getStars() - 1];
                if (value > bestValue) {
                    bestValue = value;
                    bestSpace = floor.getBestSpace();
                }
            }
        }
        return bestSpace;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Hotel");
        str.append(String.format(" (%d, %d", this.showStars(), this.getCountFloors()));
        for (Floor floor : this.getFloors()) {
            str.append(String.format(", %s", floor.toString()));
        }
        str.append(")");
        return str.toString();
    }

    @Override
    public boolean equals(Object object) {
        boolean isEquals = false;
        if (object.getClass() == Hotel.class) {
            Hotel hotel = (Hotel) object;
            if (hotel.getCountFloors() == this.getCountFloors() && hotel.showStars() == this.showStars()) {
                for (int i = 0; i < hotel.getCountFloors(); i++) {
                    if (!hotel.getFloor(i).equals(this.getFloor(i))) {
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
        int hash = this.getCountFloors() ^ this.showStars();
        for (Floor floor : this.getFloors()) {
            hash ^= floor.hashCode();
        }
        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Hotel hotel = (Hotel) super.clone();
        for (int i = 0; i < this.getCountFloors(); i++) {
            hotel.setFloor(i, (Floor) this.getFloor(i).clone());
        }
        return hotel;
    }
}
