public class MyRunnable implements Runnable {

    @Override
    public void run() {
        // Implementation for the thread's task
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: ");
            }
            if (i == 5) {
                System.out.println("Time's up!");
                System.exit(0);
            }
        }
    }
}
