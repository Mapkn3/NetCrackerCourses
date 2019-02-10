package buildings.threads;

import myInterface.Floor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

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
            boolean permit = false;
            try {
                permit = semaphore.tryAcquire(200, TimeUnit.MILLISECONDS);
                if (permit) {
                    System.out.println(String.format("Repairing space number %d with total area %.1f square meters", i, floor.getSpace(i).getSquare()));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (permit) {
                    semaphore.release();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
