package virtual.creation;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ExecutorThreadMethod {

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
        executorService();

        System.out.println("End Main");
    }


    private static void executorService() throws InterruptedException {

        /**
         Note the try with resource which will make sure all virtual threads  are terminated
         ExecutorService implements AutoCloseable in the Loom JDK version
         */
        ThreadFactory factory = Thread.ofVirtual().name("userthreads",0).factory();
        try (ExecutorService executorService = Executors.newThreadPerTaskExecutor(factory)) {
            executorService.submit(ExecutorThreadMethod::handleUserRequest);
            executorService.submit(ExecutorThreadMethod::handleUserRequest);
        }
    }

    private static void executorService2() throws InterruptedException {

        /**
         Note the try with resource which will make sure all virtual threads  are terminated
         ExecutorService implements AutoCloseable in the Loom JDK version
         */

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            executorService.submit(ExecutorThreadMethod::handleUserRequest);
            executorService.submit(ExecutorThreadMethod::handleUserRequest);
        }
    }
}
