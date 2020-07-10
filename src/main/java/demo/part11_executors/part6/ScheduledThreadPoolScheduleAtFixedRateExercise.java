package demo.part11_executors.part6;

import demo.common.Demo2;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolScheduleAtFixedRateExercise extends Demo2 {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

        LocalTime start = LocalTime.now();

        Runnable runnable = () -> logger.info("duration from start: {} second(s)", Duration.between(start, LocalTime.now()).getSeconds());
        int initialDelay = 3;
        int period = 1;
        ScheduledFuture<?> scheduledFuture = scheduledThreadPoolExecutor.scheduleAtFixedRate(runnable, initialDelay, period, TimeUnit.SECONDS);

        Runnable canceller = () -> {
            scheduledFuture.cancel(true);
            scheduledThreadPoolExecutor.shutdown();
        };
        scheduledThreadPoolExecutor.schedule(canceller, 10, TimeUnit.SECONDS);
    }
}
