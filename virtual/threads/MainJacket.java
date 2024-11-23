package virtual.threads;

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
    public static void main(String[] args) {

        System.out.println("Starting Main");

        for (int i = 0; i < 10; i++) {
            startThread();
        }

        System.out.println("End Main");
    }

    private static void startThread() {
        Thread.startVirtualThread(MainJacket::handleUserRequest);

    }
}
