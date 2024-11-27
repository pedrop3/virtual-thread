package virtual.creation;


public class BuilderThreadMethod {

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
        virtualBuilder();
        System.out.println("End Main");
    }

    private static void virtualBuilder() throws InterruptedException {
        Thread.Builder.OfVirtual vBuilder = Thread.ofVirtual().name("userthread",0); // not threads safe
        Thread vThread1 =  vBuilder.start(BuilderThreadMethod::handleUserRequest);
        Thread vThread2 =  vBuilder.start(BuilderThreadMethod::handleUserRequest);

        vThread1.join();
        vThread2.join();
    }


}
