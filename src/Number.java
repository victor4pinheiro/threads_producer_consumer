import java.util.LinkedList;
import java.util.Random;

/**
 * Number class that serves as a shared resource with methods for producing
 * and consuming numbers in a synchronized manner.
 */
public class Number {
    // Queue of numbers for storage, with a fixed maximum capacity.
    private final LinkedList<Integer> listNumbers = new LinkedList<>();
    private final int capacity = 32; // Max number of items in the list

    /**
     * Produces numbers and adds them to the list.
     * If the list is at capacity, waits until space is available.
     * @throws InterruptedException if thread is interrupted while waiting.
     */
    public void produce() throws InterruptedException {
        Random randomNumbers = new Random();

        while (true) {
            int number = randomNumbers.nextInt(); // Generate a random number

            synchronized (this) {
                // Wait if list is full
                while (listNumbers.size() >= capacity) {
                    wait();
                }

                // Add number to the list and notify consumers
                listNumbers.add(number);
                System.out.println("Producer produced " + number);

                // Notify consumers that a new item is available
                notifyAll();
            }

            // Simulate production time
            Thread.sleep(1000);
        }
    }

    /**
     * Consumes numbers by removing them from the list.
     * If the list is empty, waits until an item is available.
     * @throws InterruptedException if thread is interrupted while waiting.
     */
    public void consume() throws InterruptedException {
        while (true) {
            int consumedValue;

            synchronized (this) {
                // Wait if list is empty
                while (listNumbers.isEmpty()) {
                    wait();
                }

                // Remove number from the list and notify producers
                consumedValue = listNumbers.removeFirst();
                System.out.println("Consumer consumed " + consumedValue);

                // Notify producers that space is available
                notifyAll();
            }

            // Simulate consumption time
            Thread.sleep(1000);
        }
    }
}
