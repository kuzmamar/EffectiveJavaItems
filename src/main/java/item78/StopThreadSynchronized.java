package item78;

import java.util.concurrent.TimeUnit;

/**
 * Updating a boolean variable is an atomic operation. We don't need to ensure exclusive access to the stopRequested variable.
 * We still need to make sure that the other threads see the change. This is the purpose of the volatile keyword.
 * Synchronize both write and read operations for the program to work correctly.
 */
public class StopThreadSynchronized {

    private static boolean stopRequested = false;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (stopIsRequested()) {
                i++;
            }
        });

        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        requestStop();
    }

    private static synchronized boolean stopIsRequested() {
        return stopRequested;
    }

    private static synchronized void requestStop() {
        stopRequested = true;
    }
}
