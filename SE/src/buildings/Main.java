package buildings;

import java.io.*;
import java.util.Random;
import buildings.dwelling.*;
import buildings.dwelling.hotel.*;
import buildings.net.server.sequental.BinaryServer;
import buildings.office.*;
import buildings.threads.*;
import buildings.net.client.BinaryClient;
import myException.*;
import myInterface.*;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            Random n = new Random();
            int countFloors = 1 + n.nextInt(8);
            int[] countFlats = new int[countFloors];
            int flats = 0;
            for (int i = 0; i < countFloors; i++) {
		countFlats[i] = 1 + n.nextInt(4);
		flats += countFlats[i];
            }
            Building dwelling = new Dwelling(countFloors, countFlats);
            for (int i = 0; i < flats; i++) {
		dwelling.setSpace(i, new Flat(50 * n.nextDouble(), 1 + n.nextInt(4)));
            }
            
/*____________________________________________________________________________*/
            countFloors = 1 + n.nextInt(8);
            Floor[] floors = new OfficeFloor[countFloors];
            int countOffices;
            Space[] offices;
            for (int i = 0; i < countFloors; i++) {
                countOffices = 1 + n.nextInt(4);
		offices = new Office[countOffices];
		for (int j = 0; j < countOffices; j++) {
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
/*
            Floor threadFloor = Buildings.synchronizedFloor(new DwellingFloor(10));
            Semaphore semaphore = new Semaphore();
            semaphore.init(1);

            Thread r1 = new Thread(new SequentalRepairer(threadFloor, semaphore));
            Thread r2 = new Thread(new SequentalCleaner(threadFloor, semaphore));
            r1.start();
            r2.start();
*/         
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
                typeWriter.write(dwelling.toString(), 0, dwelling.toString().indexOf(" "));
                typeWriter.newLine();
                Buildings.writeBuilding(office, infoWriter);
                typeWriter.write(office.toString(), 0, office.toString().indexOf(" "));
                typeWriter.newLine();
                Buildings.writeBuilding(hotel, infoWriter);
                typeWriter.write(hotel.toString(), 0, hotel.toString().indexOf(" "));
                typeWriter.newLine();
            } finally {
                infoWriter.close();
                typeWriter.close();
            }
            BinaryServer.main(null);
            String[] myArgs = {infoFilename, typeFilename, costFilename};
            BinaryClient.main(myArgs);
	}
	catch (InvalidSpaceAreaException | InvalidRoomsCountException | SpaceIndexOutOfBoundsException | FloorIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
	}
    }
}