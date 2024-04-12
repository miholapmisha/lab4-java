public class SecondSolution {

    public static void main(String[] args) {
        Waiter waiter = new Waiter(5);
        PhilosophersWithWaiter[] philosophers = new PhilosophersWithWaiter[5];

        for (int i = 0; i < 5; i++) {
            philosophers[i] = new PhilosophersWithWaiter(i, i, (i + 1) % 5, waiter);

            new Thread(philosophers[i]).start();
        }

    }
}



//        Fork[] forks = new Fork[5];
//        for (int i = 0; i < forks.length; i++) {
//            forks[i] = new Fork(i + "");
//        }
//        Waiter waiter = new Waiter(forks);
//        PhilosophersWithWaiter[] philosophers = new PhilosophersWithWaiter[5];
//
//        for(int i = 0; i < 5; i++) {
//            Fork leftFork = forks[i];
//            Fork rightFork = forks[(i + 1) % 5];
//            philosophers[i] = new PhilosophersWithWaiter(i, leftFork, rightFork, waiter);
//
//            new Thread(philosophers[i]).start();
//        }
