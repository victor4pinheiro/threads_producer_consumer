/**
 * Consumer class that represents a consumer thread which processes numbers.
 * This thread retrieves data from a shared resource and performs consumption.
 */
public class Consumer extends Thread {
    // Shared Number object to consume data from; set as final for immutability.
    private final Number number;

    /**
     * Constructor for Consumer.
     * Initializes the Consumer with the shared Number object.
     *
     * @param number The shared Number instance to consume data from.
     * @throws IllegalArgumentException if the provided number is null.
     */
    public Consumer(Number number) {
        if (number == null) {
            throw new IllegalArgumentException("Number object cannot be null");
        }
        this.number = number;
    }

    /**
     * The main logic of the Consumer thread. Attempts to consume data
     * from the shared Number object, handling any interruptions gracefully.
     */
    @Override
    public void run() {
        try {
            // Attempt to consume data from the shared Number object.
            number.consume();
        } catch (InterruptedException e) {
            // Log an error message if interrupted during consumption and reset the interrupt status.
            System.err.println("SOURCE=Consumer; TYPE=Consumption Error; MESSAGE=" + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupted status
        } catch (Exception e) {
            // Catch any other unexpected exceptions and log with stack trace.
            System.err.println("Unexpected error in Consumer: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
