import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomBlockingQueue<T> {

    private Queue<T> queue;
    private AtomicInteger queueSize;
    private int capacity;


    public CustomBlockingQueue(final int size) {
        queue = new LinkedList<>();
        queueSize = new AtomicInteger(0);
        this.capacity = size;

    }

    public synchronized void enQueue(T item) throws InterruptedException {

        while (queueSize.get() >= capacity) {
            wait();

        }
        queueSize.incrementAndGet();
        queue.add(item);
        notify();
    }

    public synchronized T deQueue() throws InterruptedException {

        while (queueSize.get() == 0) {
            wait();
        }

        queueSize.decrementAndGet();
        T item = queue.poll();
        notify();

        return item;
    }

}
