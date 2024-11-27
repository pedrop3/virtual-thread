package virtual.creation;


import java.util.concurrent.ThreadFactory;

public class FactoryThreadMethod {

    private static void handleUserRequest() {
        System.out.println("Starting  Thread" + Thread.currentThread());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("End  Thread" + Thread.currentThread());
    }
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting Main");
        factoryThread();

        System.out.println("End Main");
    }


    private static void factoryThread() throws InterruptedException {

        /**
         * Returns a {@code ThreadFactory} to create threads from the current
         * state of the builder. The returned thread factory is safe for use by
         * multiple concurrent threads.
         *
         * @return a thread factory to create threads
         */
        ThreadFactory factory = Thread.ofVirtual().name("userthread",0).factory(); //thread safe

        Thread vThread1 = factory.newThread(FactoryThreadMethod::handleUserRequest);
        vThread1.start();

        Thread vThread2 = factory.newThread(FactoryThreadMethod::handleUserRequest);
        vThread2.start();

        vThread1.join();
        vThread2.join();

    }
}
