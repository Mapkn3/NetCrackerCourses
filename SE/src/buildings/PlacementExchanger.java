package buildings;

import myException.*;
import myInterface.*;

public class PlacementExchanger {
    public static boolean isExchangeSpace(Space destination, Space source) {
        return destination.getSquare() == source.getSquare() && destination.getCountRooms() == source.getCountRooms();
    }
    public static boolean isExchangeFloor(Floor destination, Floor source) {
        return destination.getTotalSquare() == source.getTotalSquare() && destination.getTotalRooms() == source.getTotalRooms();
    }
    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException {
        try {
            if (isExchangeSpace(floor1.getSpace(index1), floor2.getSpace(index2))) {
                Space tempSpace = floor1.getSpace(index1);
                floor1.setSpace(index1, floor2.getSpace(index2));
                floor2.setSpace(index2, tempSpace);
            } else {
                throw new InexchangeableSpacesException();
            }
        }
        catch (SpaceIndexOutOfBoundsException e) {
            throw e;
        }
    }
    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws InexchangeableFloorsException {
        try {
            if (isExchangeFloor(building1.getFloor(index1), building2.getFloor(index2))) {
                Floor tempFloor = building1.getFloor(index1);
                building1.setFloor(index1, building2.getFloor(index2));
                building2.setFloor(index2, tempFloor);
            } else {
                throw new InexchangeableFloorsException();
            }
        }
        catch (FloorIndexOutOfBoundsException e) {
            throw e;
        }
    }
}
