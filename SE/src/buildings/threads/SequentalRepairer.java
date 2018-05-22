package buildings.threads;

import myInterface.Floor;

public class SequentalRepairer implements Runnable {

    private final Floor floor;
    private final Semaphore semaphore;
    
    public SequentalRepairer(Floor floor, Semaphore semaphore) {
        super();
        this.floor = floor;
        this.semaphore = semaphore;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < floor.getCount(); i++) {
            System.out.println("Repair try enter");
            semaphore.enter();
            System.out.println("Repair enter");
            System.out.println(String.format("Repairing space number %d with total area %.1f square meters", i, floor.getSpace(i).getSquare()));
            semaphore.leave();
            System.out.println("Repair leave");
        }
    }
}
