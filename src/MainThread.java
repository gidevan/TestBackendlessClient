import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 6.6.17.
 */
public class MainThread {
    private static final int THREADS = 1215;
    private static final String THREAD_NAME_PREFIX = "Thread_";

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World! Backendless call threads");
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < THREADS; i++) {
            Thread th = new ServerCall(THREAD_NAME_PREFIX + i);
            threads.add(th);
        }

        for(Thread th : threads) {
            th.run();
        }
    }
}
