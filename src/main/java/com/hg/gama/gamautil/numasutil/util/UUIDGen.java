package com.hg.gama.gamautil.numasutil.util;

import org.bson.types.ObjectId;

import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class UUIDGen {
    private static final int RANDOM_ID = createMachineIdentifier();
    private static volatile AtomicLong index = new AtomicLong(new SecureRandom().nextInt(899));

    /**
     * 19位长度数字UUID， 3位随机机器标识+时间戳+3位随机数
     */
    public static String timestampUUID() {
        StringBuilder sbr = new StringBuilder();
        sbr.append(RANDOM_ID)
                .append(System.currentTimeMillis())
                .append(Math.abs(index.addAndGet(1)) % 900 + 100);

        return sbr.toString();
    }

    private static int createMachineIdentifier() {
        return (ObjectId.getGeneratedMachineIdentifier() + ObjectId.getGeneratedProcessIdentifier()) % 900 + 100;
    }

    /**
     * 推荐使用
     * @return
     */
    public static String UUID() {
        return ObjectId.get().toString();
    }

    public static String UUID32() {
        return UUID.randomUUID().toString().replaceAll("\\-", "");
    }
}
