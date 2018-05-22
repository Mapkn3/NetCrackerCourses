package buildings.dwelling.hotel;

import buildings.dwelling.DwellingFloor;
import myInterface.Space;

public class HotelFloor extends DwellingFloor implements Cloneable {
    
    public static final int DEFAULT_COUNT_STARS = 3;
    private int countStars;
    
    public HotelFloor(int countFlats) {
        super(countFlats);
        this.countStars = HotelFloor.DEFAULT_COUNT_STARS;
    }
    public HotelFloor(Space[] flats) {
        super(flats);
        this.countStars = HotelFloor.DEFAULT_COUNT_STARS;
    }
    
    public int getStars() {
        return this.countStars;
    }
    
    public void setStars(int countStars) {
        if (countStars >= 1 && countStars <= 5) {
            this.countStars = countStars;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("HotelFloor");
        str.append(String.format(" (%d, %d", this.getStars(), this.getCount()));
        for (Space space : this.getSpaces()) {
            str.append(String.format(", %s", space.toString()));
        }
        str.append(")");
        return str.toString();
    }
    
    @Override
    public boolean equals(Object object) {
        boolean isEquals = false;
        if (object.getClass() == HotelFloor.class) {
            HotelFloor hotelFloor = (HotelFloor)object;
            if (hotelFloor.getCount() == this.getCount() && hotelFloor.getStars() == this.getStars()) {
                for (int i = 0; i < hotelFloor.getCount(); i++) {
                    if (!hotelFloor.getSpace(i).equals(this.getSpace(i))) {
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
        int hash = this.getCount() ^ this.getStars();
        for (Space space : this.getSpaces()) {
            hash ^= space.hashCode();
        }
        return hash;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        HotelFloor hotelFloor = (HotelFloor)super.clone();
        hotelFloor.floor = this.floor.clone();
        for (int i = 0; i < hotelFloor.getCount(); i++) {
            hotelFloor.floor[i] = (Space)this.floor[i].clone();
        }
        return hotelFloor;
    }
}
