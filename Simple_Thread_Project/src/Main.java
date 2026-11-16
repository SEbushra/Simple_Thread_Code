import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        MyRunnable myRunnable = new MyRunnable();

        Thread thread = new Thread(myRunnable);

        thread.setDaemon(true); // Set the thread as a daemon thread

        thread.start(); // Start the thread

        System.out.println("You have 10 seconds to enter your name");

        System.out.println("Enter your name: ");

        String name = scanner.nextLine();

        System.out.println("Hello " + name);

        scanner.close();

    }
}
