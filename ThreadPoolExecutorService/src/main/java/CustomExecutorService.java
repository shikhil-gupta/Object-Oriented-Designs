import java.util.concurrent.LinkedBlockingQueue;

public class CustomExecutorService {

    private ThreadPoolExecutor threadPoolExecutor;

    public CustomExecutorService(int pool_size) {
        threadPoolExecutor = new ThreadPoolExecutor(pool_size, pool_size, new LinkedBlockingQueue());
    }
}
