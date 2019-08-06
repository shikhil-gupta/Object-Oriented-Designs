package connection.pooling;

import java.util.LinkedList;
import java.util.List;

public class CustomLock {

    private List<QueueObject> waitingThreads = new LinkedList<QueueObject>();
    private int counter = 0;

    public CustomLock(int counter) {
        this.counter=counter;
    }

    public void lock() throws InterruptedException {
        System.out.println("lock called for thread " + Thread.currentThread().getId());
        QueueObject queueObject = new QueueObject();
        synchronized (this) {
            waitingThreads.add(queueObject);
            System.out.println("Thread added in queue with thread id " + Thread.currentThread().getId()+ " with size " + waitingThreads.size() + " index " + waitingThreads.indexOf(queueObject));
        }

        while (true) {
            synchronized (this) {
                if (counter>0) {
                    counter--;
                    waitingThreads.remove(queueObject);
                    return;
                }
            }
            try {
                queueObject.doWait();
            } catch (InterruptedException e) {
                synchronized (this) {
                    waitingThreads.remove(queueObject);
                }
                throw e;
            }
        }
    }

    public synchronized void customnotify(){
        counter++;
        if(waitingThreads.size() > 0){
            waitingThreads.get(0).doNotify();
        }
    }

}

class QueueObject {

    public boolean isNotified = false;

    public synchronized void doWait() throws InterruptedException {
        isNotified=false;
        while(!isNotified){
            System.out.println("Thread  " + Thread.currentThread().getId() + " is waiting");
            this.wait();
        }
        System.out.println("Thread  " + Thread.currentThread().getId() + " is started");
    }

    public synchronized void doNotify() {
        System.out.println("Thread " + Thread.currentThread().getId() + " is notified");
        this.isNotified = true;
        this.notify();
    }

}

