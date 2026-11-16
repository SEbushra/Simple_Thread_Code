# Simple Thread Project in Java

## 1. Project Overview

This project demonstrates the basic concept of **Multithreading in Java** by running two tasks concurrently:

- **Main Thread:** Asks the user to enter their name.
- **Worker Thread:** Counts down for 10 seconds while the Main Thread waits for user input.

If the user does not enter their name before the countdown finishes, the program displays `Time's up!` and terminates.

## 2. What is a Thread?

A **Thread** is an independent path of execution within a program.

Multithreading allows a program to execute multiple tasks concurrently instead of waiting for one task to finish before starting another.

For example, in this project, the program can wait for user input while another thread performs the countdown.

## 3. Creating a Thread in Java

There are two common ways to create a Thread:

1. **Extending the Thread class:** Create a class that extends `Thread` and override its `run()` method.
2. **Implementing the Runnable interface:** Create a class that implements `Runnable` and override its `run()` method.

This project uses the second approach.

### Why Runnable?

`Runnable` represents a task that needs to be executed.

It separates the task itself from the Thread responsible for executing it. It is also useful because Java does not support extending multiple classes.

## 4. Project Structure

The project contains two main classes:

- `Main.java`: Creates and starts the Thread, and handles user input.
- `MyRunnable.java`: Contains the task that the new Thread executes.

## 5. Code Explanation

### MyRunnable.java

This class implements the `Runnable` interface.

```java
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {

            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                System.out.println("Thread was interrupted");
            }

            if (i == 10) {
                System.out.println("Time's up!");
                System.exit(0);
            }
        }
    }
}
```

**Explanation:**

- `implements Runnable`: Makes the class implement the Runnable interface.
- `run()`: Contains the task that the Thread will execute.
- `for` loop: Repeats the countdown 10 times.
- `Thread.sleep(1000)`: Pauses the currently executing Thread for 1000 milliseconds (1 second).
- `InterruptedException`: A checked exception that may occur if the sleeping Thread is interrupted.
- `System.exit(0)`: Terminates the entire Java application when the countdown reaches 10.

**Important:** `Thread.sleep()` pauses only the Thread that calls it, not all the Threads in the program.

### Main.java

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        MyRunnable myRunnable = new MyRunnable();

        Thread thread = new Thread(myRunnable);

        thread.setDaemon(true);

        thread.start();

        System.out.println("You have 10 seconds to enter your name");
        System.out.print("Enter your name: ");

        String name = scanner.nextLine();

        System.out.println("Hello " + name);

        scanner.close();
    }
}
```

**Explanation:**

- `Scanner`: Reads the user's input from the keyboard.
- `new MyRunnable()`: Creates an object containing the task to be executed.
- `new Thread(myRunnable)`: Creates a new Thread and assigns the Runnable task to it.
- `thread.setDaemon(true)`: Marks the new Thread as a Daemon Thread.
- `thread.start()`: Starts a new execution thread, which executes the `run()` method.
- `scanner.nextLine()`: Waits for the user to enter their name.
- `scanner.close()`: Closes the Scanner and its underlying input source.

## 6. User Threads vs. Daemon Threads

Java has two types of Threads based on their role in keeping the application running:

| User Thread | Daemon Thread |
|---|---|
| A normal Thread. | A background Thread. |
| Prevents the JVM from shutting down while it is running. | Does not prevent the JVM from shutting down. |
| Completes its task normally. | May be stopped when all User Threads finish. |

In this project, the countdown Thread is marked as a Daemon Thread using:

```java
thread.setDaemon(true);
```

**Important:** `setDaemon(true)` must be called before `start()`. Otherwise, Java throws an `IllegalThreadStateException`.

## 7. Important Thread Methods

| Method | Description |
|---|---|
| `start()` | Starts a new Thread and executes its `run()` method. |
| `run()` | Contains the task performed by the Thread. |
| `sleep()` | Pauses the currently executing Thread for a specified time. |
| `setDaemon(true)` | Marks a Thread as a Daemon Thread. |
| `isAlive()` | Checks whether a Thread is still running. |
| `join()` | Makes the calling Thread wait for another Thread to finish. |

**Note:** Calling `run()` directly does not create a new Thread. To start a separate Thread, use `start()`.

## 8. How the Program Works

The program starts two execution paths:

**Main Thread:**
1. Creates the Scanner.
2. Creates and starts the worker Thread.
3. Asks the user to enter their name.
4. Waits for input.
5. Prints a greeting if the input is received in time.

**Worker Thread:**
1. Starts the countdown.
2. Sleeps for one second during each iteration.
3. Repeats the process 10 times.
4. Prints `Time's up!` and terminates the application if the countdown finishes.

The two Threads execute concurrently, so the countdown can continue while the Main Thread waits for user input.

## 9. Running the Project

Compile both Java files:

```bash
javac Main.java MyRunnable.java
```

Run the Main class:

```bash
java Main
```

**Why run Main instead of MyRunnable?**

`Main` contains the `main()` method, which is the entry point of the application. `MyRunnable` only defines the task that the new Thread executes.