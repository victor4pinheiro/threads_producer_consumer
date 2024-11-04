/**
 * Main class to initialize and start Producer and Consumer threads.
 */
public class Main {
    public static void main(String[] args) {
        // Shared Number instance for producer and consumer
        Number number = new Number();

        // Initialize Producer and Consumer threads
        Producer producer = new Producer(number);
        Consumer consumer = new Consumer(number);

        // Add a shutdown hook to handle clean shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown initiated. Stopping threads...");
            producer.interrupt();
            consumer.interrupt();
        }));

        try {
            // Start the Producer and Consumer threads
            producer.start();
            consumer.start();

            // Wait for both threads to complete
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            // Log and handle any interruptions
            System.err.println("SOURCE=Main; TYPE=Thread Interruption; MESSAGE=" + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupt status
        } catch (Exception e) {
            // Catch other unexpected exceptions
            System.err.println("SOURCE=Main; TYPE=Error; MESSAGE=" + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Main thread finished.");
    }
}
