import java.util.concurrent.Semaphore;

public class Waiter {
    private final Semaphore[] forks;

    public Waiter(int numForks) {
        forks = new Semaphore[numForks];
        for (int i = 0; i < numForks; i++) {
            forks[i] = new Semaphore(1);
        }
    }

    public boolean requestForks(int leftForkId, int rightForkId) throws InterruptedException {
        if (forks[leftForkId].tryAcquire() && forks[rightForkId].tryAcquire()) {
            return true;
        } else {
            if (forks[leftForkId].availablePermits() == 0) {
                forks[leftForkId].release();
            }
            if (forks[rightForkId].availablePermits() == 0) {
                forks[rightForkId].release();
            }
            return false;
        }
    }

    public void releaseForks(int leftForkId, int rightForkId) {
        forks[leftForkId].release();
        forks[rightForkId].release();
    }
}


//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
//public class Waiter {
//
//    private final ConcurrentMap<Fork, Boolean> availableForks = new ConcurrentHashMap<>();
//
//    public Waiter(Fork[] forks) {
//        for (Fork fork : forks) {
//            availableForks.put(fork, true);
//        }
//    }
//
//    public boolean requestForks(Fork leftFork, Fork rightFork) {
//        boolean acquired = availableForks.get(leftFork) && availableForks.get(rightFork);
//        if (acquired) {
//            availableForks.put(leftFork, false);
//            availableForks.put(rightFork, false);
////            System.out.println("True:" + availableForks);
//            return true;
//        } else {
//            if (availableForks.get(leftFork)) {
//                availableForks.put(leftFork, false);
//            }
//            if (availableForks.get(rightFork)) {
//                availableForks.put(rightFork, false);
//            }
////            System.out.println("False: " + availableForks);
//            return false;
//        }
//    }
//
//    public void releaseForks(Fork leftFork, Fork rightFork) {
//        availableForks.put(leftFork, true);
//        availableForks.put(rightFork, true);
//    }
//
//    public synchronized ConcurrentMap<Fork, Boolean> getAvailableForks() {
//        return availableForks;
//    }
//}