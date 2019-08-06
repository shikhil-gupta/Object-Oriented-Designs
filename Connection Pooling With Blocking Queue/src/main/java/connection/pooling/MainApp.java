package connection.pooling;


public class MainApp {

//http://tutorials.jenkov.com/java-concurrency/thread-signaling.html
//http://tutorials.jenkov.com/java-concurrency/starvation-and-fairness.html#locks
    public static void main(String[] args) throws Exception {

        System.out.println("Main Thread id" + Thread.currentThread().getId());


        CustomRunable runable = new CustomRunable();
        Thread thread1 = new Thread(runable);
        Thread thread2 = new Thread(runable);
        Thread thread3 = new Thread(runable);
        Thread thread4 = new Thread(runable);
        Thread thread5 = new Thread(runable);
        Thread thread6 = new Thread(runable);
        Thread thread7 = new Thread(runable);
        Thread thread8 = new Thread(runable);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        System.out.println("exiting Main Thread id" + Thread.currentThread().getId());
    }

}

class CustomRunable implements Runnable {

    BasicConnectionPool connectionPool = new BasicConnectionPool();

    public void run() {
        try {

            Integer connection = connectionPool.getConnection();

            System.out.println("Thread Id " + Thread.currentThread().getId() + " got the connection " + connection);
            Thread.sleep(10000);
            connectionPool.releaseConnection(connection);


        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
