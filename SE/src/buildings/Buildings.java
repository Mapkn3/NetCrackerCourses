package buildings;

import java.io.*;
import static java.lang.String.format;
import myFactory.*;
import myInterface.*;

public class Buildings {
    public static BuildingFactory factory = new DwellingFactory();
    
    public static void setBuildingFactory(BuildingFactory factory) {
        Buildings.factory = factory;
    }
    
    public static Space createSpace(double area) {
        return Buildings.factory.createSpace(area);
    }

    public static Space createSpace(int roomsCount, double area) {
        return Buildings.factory.createSpace(roomsCount, area);
    }

    public static Floor createFloor(int spacesCount) {
        return Buildings.factory.createFloor(spacesCount);
    }

    public static Floor createFloor(Space[] spaces) {
        return Buildings.factory.createFloor(spaces);
    }

    public static Building createBuilding(int floorsCount, int[] spacesCounts) {
        return Buildings.factory.createBuilding(floorsCount, spacesCounts);
    }

    public static Building createBuilding(Floor[] floors) {
        return Buildings.factory.createBuilding(floors);
    }
    
    public static Floor synchronizedFloor (Floor floor) {
        return new SynchronizedFloor(floor);
    }
    
    public static void outputBuilding (Building building, OutputStream out) throws IOException {
        StringBuilder obj = new StringBuilder();
        obj.append(building.getCountFloors());
        for (Floor floor : building.getFloors()) {
            obj.append(" ").append(floor.getCount());
            for (Space space : floor.getSpaces()) {
                obj.append(" ").append(space.getCountRooms()).append(" ").append(format("%.1f", space.getSquare()));
            }
        }
        out.write((obj.toString().replace(',', '.') + "\n").getBytes());
        out.flush();
    }

    public static Building inputBuilding (InputStream in) throws IOException {
        StringBuilder temp = new StringBuilder();
        int n;
        while ((n = in.read()) != -1) {
            temp.append((char) n);
        }
        String[] obj = temp.toString().replace(',', '.').split(" ");
        int k = 0;
        Floor[] floors = new Floor[Integer.parseInt(obj[k])];
        k++;
        for (int i = 0; i < floors.length; i++) {
            Space[] spaces = new Space[Integer.parseInt(obj[k])];
            k++;
            for (int j = 0; j < spaces.length; j++) {
                int rooms = Integer.parseInt(obj[k]);
                k++;
                double square = Double.parseDouble(obj[k]);
                k++;
                spaces[j] = Buildings.createSpace(rooms, square);
            }
            floors[i] = Buildings.createFloor(spaces);
        }
        return Buildings.createBuilding(floors);
    }
    public static void  writeBuilding (Building building, Writer out) throws IOException {
        StringBuilder obj = new StringBuilder();
        obj.append(building.getCountFloors());
        for (Floor floor : building.getFloors()) {
            obj.append(" ").append(floor.getCount());
            for (Space space : floor.getSpaces()) {
                obj.append(" ").append(space.getCountRooms()).append(" ").append(format("%.1f", space.getSquare()));
            }
        }
        out.write(obj.toString().replace(',', '.') + "\n");
        out.flush();
    }
    public static Building readBuilding (Reader in) throws IOException {
        StreamTokenizer st = new StreamTokenizer(in);
        st.nextToken();
        Floor[] floors = new Floor[(int) st.nval];
        st.nextToken();
        for (int i = 0; i < floors.length; i++) {
            Space[] spaces = new Space[(int) st.nval];
            st.nextToken();
            for (int j = 0; j < spaces.length; j++) {
                int rooms = (int) st.nval;
                st.nextToken();
                double square = st.nval;
                st.nextToken();
                spaces[j] = Buildings.createSpace(rooms, square);
            }
            floors[i] = Buildings.createFloor(spaces);
        }
        return Buildings.createBuilding(floors);
    }
    /*  Записанные данные о здании представляет собой последовательность чисел,
    первым из которых является количество этажей,
    далее следует количество помещений текущего этажа и
    соответствующие значения количества комнат и площадей помещений текущего этажа.
    Например, символьная запись для трехэтажного здания: 
    «3 2 3 150.0 2 100.0 1 3 250.0 3 2 140.0 1 60.0 1 50.0»
    Для чтения этажа из символьного потока рекомендуется использовать StreamTokenizer.
    Проверьте возможности всех реализованных методов,
    в качестве реальных потоков используя файловые потоки,
    а также потоки System.in и System.out.
*/
}
