package buildings.threads;

public class Semaphore {
    private int counter;
    
    public Semaphore() {
        this.counter = 1;
    }
    
    public void init(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        counter = n;
    }
    
    public void enter() {
        System.out.println("until enter counter: " + counter);
        while(counter <= 0){
            System.out.println("waiting");
        }
        counter--;
        System.out.println("after enter counter: " + counter);
    }
    
    public void leave() {
        System.out.println("until leave counter: " + counter);
        counter++;
        System.out.println("after leave counter: " + counter);
    }
}
