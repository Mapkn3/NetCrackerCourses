package buildings.net.server.sequental;

import buildings.Buildings;
import myFactory.BuildingFactory;
import myFactory.DwellingFactory;
import myFactory.HotelFactory;
import myFactory.OfficeFactory;
import myInterface.Building;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BinaryServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(11111);
        System.out.println("ServerSocket start.");
        while (true) {
            System.out.println("Wait client...");
            try (
                    Socket clientSocket = serverSocket.accept();
                    DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                    DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())
            ) {
                System.out.println("Client connected.");
                String buildingType = in.readUTF();
                System.out.println("Read type of building: " + buildingType);
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
                System.out.println("Set factory is successful.");
                Building building = Buildings.inputBuilding(in);
                System.out.println("Read building: " + building.toString());
                double cost = generateCost(buildingType, building);
                System.out.println("Send cost: " + cost);
                out.writeDouble(cost);
                System.out.println("End connection.");
            }
        }
    }

    private static double generateCost(String type, Building building) {
        int multiplier = 0;
        switch (type) {
            case "Dwelling":
                multiplier = 1000;
                break;
            case "OfficeBuilding":
                multiplier = 1500;
                break;
            case "Hotel":
                multiplier = 2000;
                break;
        }
        return building.getTotalSquare() * multiplier;
    }
}
