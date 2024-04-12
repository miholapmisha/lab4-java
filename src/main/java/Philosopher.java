public class Philosopher implements Runnable {

    private final Integer id;

    private final Fork leftFork;

    private final Fork rightFork;

    public Philosopher(Integer id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {

                System.out.println("Philosopher " + id + " is thinking for the " + (i + 1) + "th time");
                // Simulate thinking
                Thread.sleep((long) (Math.random() * 3 * 1000));
                synchronized (leftFork) {
                    System.out.println("Philosopher " + id + " picked up left fork: " + leftFork.getName());
                    synchronized (rightFork) {
                        System.out.println("Philosopher " + id + " picked up right fork: " + rightFork.getName());
                        // Simulate eating
                        Thread.sleep((long) (Math.random() * 3 * 1000));

                        System.out.println("Philosopher " + id + " put down right fork: " + rightFork.getName());
                    }
                    System.out.println("Philosopher " + id + " put down left fork: " + rightFork.getName());
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
