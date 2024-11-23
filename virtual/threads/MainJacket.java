package virtual.threads;

import java.util.ArrayList;

public class MainJacket {

    private static void handleUserRequest() {
        System.out.println("Starting  Thread" + Thread.currentThread());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("End  Thread" + Thread.currentThread());
    }
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting Main");

        var threads = new ArrayList<Thread>();
        for (int i = 0; i < 2000000; i++) {
            threads.add(startThread());
        }

        for (Thread thread: threads) {
            thread.join();
        }

        //join on the threads

        System.out.println("End Main");
    }

    private static Thread startThread() {
       return Thread.startVirtualThread(MainJacket::handleUserRequest);
    }
}
