package buildings.net.server.sequental;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import buildings.Buildings;
import myFactory.*;
import myInterface.Building;

public class BinaryServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(11111);
        System.out.println("ServerSocket start.");
        while (true) {
            System.out.println("Wait client...");
            try (
                    Socket clientSocket = serverSocket.accept();
                    InputStream inputStream = clientSocket.getInputStream();
                    OutputStream outputStream = clientSocket.getOutputStream();
                    DataInputStream in = new DataInputStream(inputStream);
                    DataOutputStream out = new DataOutputStream(outputStream)
            ) {
                System.out.println("Client connected.");
                String buildingType = in.readUTF();
                System.out.println("Read type of building: " + buildingType);
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
                System.out.println("Set factory is successful.");
                Building building = Buildings.inputBuilding(inputStream);
                System.out.println("Read building: " + building.toString());
                double cost = generateCost(buildingType, building);
                System.out.println("Send cost: " + cost);
                out.writeUTF(Double.toString(cost));
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
