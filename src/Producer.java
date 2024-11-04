/**
 * Producer class that represents a producer thread which generates numbers.
 * This thread places data into a shared resource for consumers to retrieve.
 */
public class Producer extends Thread {
    // Shared Number object to produce data into; set as final for immutability.
    private final Number number;

    /**
     * Constructor for Producer.
     * Initializes the Producer with the shared Number object.
     *
     * @param number The shared Number instance where data will be produced.
     * @throws IllegalArgumentException if the provided number is null.
     */
    public Producer(Number number) {
        if (number == null) {
            throw new IllegalArgumentException("Number object cannot be null");
        }
        this.number = number;
    }

    /**
     * The main logic of the Producer thread. Attempts to produce data
     * into the shared Number object, handling any interruptions gracefully.
     */
    @Override
    public void run() {
        try {
            // Attempt to produce data to the shared Number object.
            number.produce();
        } catch (InterruptedException e) {
            // Log an error message if interrupted during production and reset the interrupt status.
            System.err.println("SOURCE=Producer; TYPE=Production Error; MESSAGE=" + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupted status
        } catch (Exception e) {
            // Catch any other unexpected exceptions and log with stack trace.
            System.err.println("Unexpected error in Producer: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
