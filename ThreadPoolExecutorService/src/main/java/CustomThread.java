import java.util.concurrent.BlockingQueue;

public class CustomThread extends Thread {

    private BlockingQueue<Runnable> blockingQueue;

    public CustomThread(final BlockingQueue<Runnable> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        Runnable runnable = null;
        while ((runnable = blockingQueue.poll()) != null) {
            runnable.run();
        }
    }
}
