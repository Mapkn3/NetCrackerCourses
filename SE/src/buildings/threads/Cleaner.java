package buildings.threads;

import myInterface.Floor;

public class Cleaner extends Thread {
    
    private final Floor floor;
    
    public Cleaner(Floor floor) {
        super();
        this.floor = floor;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < floor.getCount(); i++) {
            System.out.println(String.format("Cleaning room number %d with total area %.1f square meters", i, floor.getSpace(i).getSquare()));
        }
    }
}
