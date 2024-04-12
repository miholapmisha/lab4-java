public class PhilosophersWithWaiter implements Runnable {
    private final int id;
    private final int leftForkId;
    private final int rightForkId;
    private final Waiter waiter;

    public PhilosophersWithWaiter(int id, int leftForkId, int rightForkId, Waiter waiter) {
        this.id = id;
        this.leftForkId = leftForkId;
        this.rightForkId = rightForkId;
        this.waiter = waiter;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("Philosopher " + id + " is thinking for the " + (i + 1) + "th time");

                boolean acquired = waiter.requestForks(leftForkId, rightForkId);
                if (acquired) {
                    System.out.println("Philosopher " + id + " picked up forks " + leftForkId + " and " + rightForkId);

                    // Simulate eating
                    Thread.sleep((long) (Math.random() * 3 * 1000));
                    waiter.releaseForks(leftForkId, rightForkId);
                    System.out.println("Philosopher " + id + " put down forks " + leftForkId + " and " + rightForkId);
                } else {
                    System.out.println("Philosopher " + id + " couldn't acquire both forks, thinking...");
                }

                // Simulate thinking
                Thread.sleep((long) (Math.random() * 3 * 1000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


//public class PhilosophersWithWaiter implements Runnable {
//
//    private final Integer id;
//
//    private final Fork leftFork;
//
//    private final Fork rightFork;
//
//    private final Waiter waiter;
//
//    public PhilosophersWithWaiter(Integer id, Fork leftFork, Fork rightFork, Waiter waiter) {
//        this.id = id;
//        this.leftFork = leftFork;
//        this.rightFork = rightFork;
//        this.waiter = waiter;
//    }
//
//    @Override
//    public void run() {
//
//        for (int i = 0; i < 10; i++) {
//            boolean acquired = waiter.requestForks(leftFork, rightFork);
//            if (acquired) {
//                System.out.println("Philosopher " + id + " picked up forks " + leftFork.getName() + " and " + rightFork.getName());
//                try {
//                    Thread.sleep((long) (Math.random() * 1000));
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                waiter.releaseForks(leftFork, rightFork);
//
//                System.out.println("Philosopher " + id + " put down forks " + leftFork.getName() + " and " + rightFork.getName());
//            } else {
//                System.out.println("Philosopher " + id + " couldn't acquire both forks, waiting...");
//            }
//        }
//
//    }
//}