package buildings.threads;

import myInterface.Floor;

public class SequentalCleaner implements Runnable {
    
    private final Floor floor;
    private final Semaphore semaphore;
    
    public SequentalCleaner(Floor floor, Semaphore semaphore) {
        super();
        this.floor = floor;
        this.semaphore = semaphore;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < floor.getCount(); i++) {
            System.out.println("Cleaner try enter");
            semaphore.enter();
            System.out.println("Cleaner enter");
            System.out.println(String.format("Cleaning room number %d with total area %.1f square meters", i, floor.getSpace(i).getSquare()));
            semaphore.leave();
            System.out.println("Cleaner leave");
        }
    }
}
