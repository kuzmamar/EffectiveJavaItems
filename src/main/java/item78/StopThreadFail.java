package item78;

import java.util.concurrent.TimeUnit;

/**
 * This demonstrates bad synchronization between threads.
 * It does not work because the background thread may not never see the stopRequested change to true.
 *
 * The compiler may also change the loop
 *
 * while (!stopRequested) {
 *                 i++;
 *             }
 *
 * to
 *
 * if (!stopRequested) {
 *     while (true) {
 *         i++;
 *     }
 * }
 *
 * This is known as hoisting. This results in liveliness failure - the program fails to make progress.
 */
public class StopThreadFail {

    private static boolean stopRequested = false;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                i++;
            }
        });

        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
