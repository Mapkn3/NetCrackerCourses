package buildings;

import buildings.dwelling.Dwelling;
import buildings.dwelling.Flat;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFloor;
import buildings.net.client.BinaryClient;
import buildings.net.server.sequental.BinaryServer;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;
import buildings.threads.SequentalCleaner;
import buildings.threads.SequentalRepairer;
import myException.FloorIndexOutOfBoundsException;
import myException.InvalidRoomsCountException;
import myException.InvalidSpaceAreaException;
import myException.SpaceIndexOutOfBoundsException;
import myInterface.Building;
import myInterface.Floor;
import myInterface.Space;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            Random n = new Random();
            int countFloors = 1 + n.nextInt(8);
            int[] countFlats = new int[countFloors];
            for (int i = 0; i < countFloors; i++) {
                countFlats[i] = 1 + n.nextInt(4);
            }
            Building dwelling = new Dwelling(countFloors, countFlats);
            for (int i = 0; i < dwelling.getCountSpaces(); i++) {
                dwelling.setSpace(i, new Flat(50 * n.nextDouble(), 1 + n.nextInt(4)));
            }

/*____________________________________________________________________________*/

            countFloors = 1 + n.nextInt(8);
            Floor[] floors = new OfficeFloor[countFloors];
            Space[] offices;
            for (int i = 0; i < floors.length; i++) {
                int countOffices = 1 + n.nextInt(4);
                offices = new Office[countOffices];
                for (int j = 0; j < offices.length; j++) {
                    offices[j] = new Office(300 * n.nextDouble(), 1 + n.nextInt(4));
                }
                floors[i] = new OfficeFloor(offices);
            }
            Building office = new OfficeBuilding(floors);


/*____________________________________________________________________________*/

            countFloors = 1 + n.nextInt(8);
            HotelFloor[] hotelFloors = new HotelFloor[countFloors];
            for (int i = 0; i < countFloors; i++) {
                hotelFloors[i] = new HotelFloor(1 + n.nextInt(4));
            }
            Hotel hotel = new Hotel(hotelFloors);
            for (int i = 0; i < hotel.getCountSpaces(); i++) {
                hotel.setSpace(i, new Flat(50 * n.nextDouble(), 1 + n.nextInt(4)));
            }

            /*____________________________________________________________________________*/
            try {
                Floor threadFloor = Buildings.synchronizedFloor(dwelling.getFloor(0));
                Semaphore semaphore = new Semaphore(1);

                Thread repairer = new Thread(new SequentalRepairer(threadFloor, semaphore));
                repairer.setPriority(Thread.MAX_PRIORITY);
                repairer.start();
                Thread.sleep(5);
                Thread cleaner = new Thread(new SequentalCleaner(threadFloor, semaphore));
                cleaner.setPriority(Thread.MIN_PRIORITY);
                cleaner.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*____________________________________________________________________________*/

            String infoFilename = "info.txt";
            String typeFilename = "type.txt";
            String costFilename = "cost.txt";

            File infoFile = new File(infoFilename);
            infoFile.createNewFile();
            BufferedWriter infoWriter = new BufferedWriter(new FileWriter(infoFile));
            File typeFile = new File(typeFilename);
            typeFile.createNewFile();
            BufferedWriter typeWriter = new BufferedWriter(new FileWriter(typeFile));
            try {
                Buildings.writeBuilding(dwelling, infoWriter);
                infoWriter.newLine();
                typeWriter.write(dwelling.toString(), 0, dwelling.toString().indexOf(" "));
                typeWriter.newLine();
                Buildings.writeBuilding(office, infoWriter);
                infoWriter.newLine();
                typeWriter.write(office.toString(), 0, office.toString().indexOf(" "));
                typeWriter.newLine();
                Buildings.writeBuilding(hotel, infoWriter);
                infoWriter.newLine();
                typeWriter.write(hotel.toString(), 0, hotel.toString().indexOf(" "));
                typeWriter.newLine();
            } finally {
                infoWriter.close();
                typeWriter.close();
            }
            new Thread(() -> {
                try {
                    BinaryServer.main(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try {
                    BinaryClient.main(new String[]{infoFilename, typeFilename, costFilename});
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (InvalidSpaceAreaException | InvalidRoomsCountException | SpaceIndexOutOfBoundsException | FloorIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}