package buildings.net.client;

import java.io.*;

import buildings.Buildings;

import java.net.InetAddress;
import java.net.Socket;
import myFactory.*;
import myInterface.Building;

public class BinaryClient {

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("Run with arguments. For example, java BinaryClient fileWithBuildingsInfo fileWithBuildingsType fileNameForResultBuildingsCost.");
            System.exit(1);
        }

        File buildingsInfo = new File(args[0]);
        if (!buildingsInfo.exists()) {
            System.out.println("File with buidings info not found!");
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
                BufferedWriter costWriter = new BufferedWriter(new FileWriter(buildingsCost));
                Socket socket = new Socket(InetAddress.getLocalHost(), 11111);
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                DataInputStream in = new DataInputStream(inputStream);
                DataOutputStream out = new DataOutputStream(outputStream)
        ) {
            String buildingType;
            while ((buildingType = typeReader.readLine()) != null) {
                switch (buildingType) {
                    case "Dwelling":
                        Buildings.setBuildingFactory(new DwellingFactory());
                        break;
                    case "OfficeBuilding":
                        Buildings.setBuildingFactory(new OfficeFactory());
                        break;
                    case "Hotel":
                        Buildings.setBuildingFactory(new HotelFactory());
                        break;
                }
                String buildingInfo = infoReader.readLine();
                System.out.println("Read info about building: " + buildingInfo);
                Building building = Buildings.readBuilding(new StringReader(buildingInfo));

                System.out.println("Sending building type: " + buildingType);
                out.writeUTF(buildingType);
                System.out.println("Sending building:\n" + building.toString());
                Buildings.outputBuilding(building, outputStream);
                System.out.println("Waiting cost for building...");
                String buildingCost = in.readUTF();
                System.out.println("Read cost: " + buildingCost);
                costWriter.write(buildingCost);
                costWriter.newLine();
            }
        }
    }
}
