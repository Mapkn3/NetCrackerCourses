package buildings.threads;

import myInterface.Floor;

public class Repairer extends Thread {
    
    private final Floor floor;
    
    public Repairer(Floor floor) {
        super();
        this.floor = floor;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < floor.getCount(); i++) {
            System.out.println(String.format("Repairing space number %d with total area %.1f square meters", i, floor.getSpace(i).getSquare()));
        }
    }
}
