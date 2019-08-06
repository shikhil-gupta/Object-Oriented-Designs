import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutor {

    private int min_size;
    private int max_size;
    private AtomicInteger currentPoolSize;
    private BlockingQueue<Runnable> queue;
    private List<CustomThread> threads;

    public ThreadPoolExecutor(final int min_size, final int max_size, final BlockingQueue queue) {
        this.min_size = min_size;
        this.max_size = max_size;
        this.queue = queue;
        currentPoolSize = new AtomicInteger(0);
        threads = new ArrayList<>();
        initializePool();
    }

    public void submitTask(List<Runnable> runnables) {
        for (Runnable runnable : runnables) {
            queue.add(runnable);
        }
    }

    private void initializePool() {
        for (int i=0; i< max_size; i++) {
            CustomThread customThread = new CustomThread(queue);
            threads.add(customThread);
            customThread.start();
            currentPoolSize.incrementAndGet();
        }
    }
}
