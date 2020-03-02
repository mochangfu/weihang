package com.hg.gama.gamautil.numasutil.util;

import java.security.SecureRandom;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public final class ExecutorUtil {

    private static volatile AtomicLong index = new AtomicLong(new SecureRandom().nextInt(899));

    private static String uuid() {
        StringBuilder sbr = new StringBuilder();
        sbr.append(Long.toString(System.currentTimeMillis(), 36))
                .append(Long.toString(Math.abs(index.addAndGet(1)) % 900 + 100, 36));

        return sbr.toString();
    }

    public static ExecutorService createExecutor(String name, int maxPoolSize) {
        return createExecutor(maxPoolSize, r -> {
            Thread thread = new Thread(r);
            thread.setName(name + "-" + uuid());
            return thread;
        });
    }

    public static ExecutorService createExecutor(int maxPoolSize, ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(0, maxPoolSize,
                1000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(Integer.MAX_VALUE), threadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

    public static ExecutorService createExecutor(String name) {
        return createExecutor(name, Runtime.getRuntime().availableProcessors());
    }

    public static ExecutorService createSingleExecutor(String name) {
        return createExecutor(name, 1);
    }
}
