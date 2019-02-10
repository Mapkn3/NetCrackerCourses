package buildings.net.client;

import buildings.Buildings;
import myFactory.BuildingFactory;
import myFactory.DwellingFactory;
import myFactory.HotelFactory;
import myFactory.OfficeFactory;
import myInterface.Building;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class BinaryClient {

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("Run with arguments. For example, java BinaryClient fileWithBuildingsInfo fileWithBuildingsType fileNameForResultBuildingsCost.");
            System.exit(1);
        }

        File buildingsInfo = new File(args[0]);
        if (!buildingsInfo.exists()) {
            System.out.println("File with buildings info not found!");
            System.exit(2);
        }
        File buildingsType = new File(args[1]);
        if (!buildingsType.exists()) {
            System.out.println("File with buildings type not found!");
            System.exit(2);
        }
        File buildingsCost = new File(args[2]);
        buildingsCost.createNewFile();

        try (
                BufferedReader infoReader = new BufferedReader(new FileReader(buildingsInfo));
                BufferedReader typeReader = new BufferedReader(new FileReader(buildingsType));
                BufferedWriter costWriter = new BufferedWriter(new FileWriter(buildingsCost))
        ) {
            String buildingType;
            while ((buildingType = typeReader.readLine()) != null) {
                try (
                        Socket socket = new Socket(InetAddress.getLocalHost(), 11111);
                        DataInputStream in = new DataInputStream(socket.getInputStream());
                        DataOutputStream out = new DataOutputStream(socket.getOutputStream())
                ) {
                    BuildingFactory buildingFactory = new DwellingFactory();
                    switch (buildingType) {
                        case "Dwelling":
                            buildingFactory = new DwellingFactory();
                            break;
                        case "OfficeBuilding":
                            buildingFactory = new OfficeFactory();
                            break;
                        case "Hotel":
                            buildingFactory = new HotelFactory();
                            break;
                    }
                    Buildings.setBuildingFactory(buildingFactory);
                    String buildingInfo = infoReader.readLine();
                    System.out.println("Read info about building: " + buildingInfo);
                    Building building = Buildings.readBuilding(new StringReader(buildingInfo));

                    System.out.println("Sending building type: " + buildingType);
                    out.writeUTF(buildingType);
                    System.out.println("Sending building:\n" + building.toString());
                    Buildings.outputBuilding(building, out);
                    System.out.println("Waiting cost for building...");
                    double buildingCost = in.readDouble();
                    System.out.println("Read cost: " + buildingCost);
                    costWriter.write(String.valueOf(buildingCost));
                    costWriter.newLine();
                }
            }
        }
    }
}
