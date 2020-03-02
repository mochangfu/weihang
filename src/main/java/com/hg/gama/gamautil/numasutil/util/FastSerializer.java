/*
package com.hg.gama.gamautil.numasutil.util;

import org.apache.commons.collections4.CollectionUtils;
import org.nustaq.serialization.FSTConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FastSerializer {

    private static FSTConfiguration serializer = null;

    static {
        serializer = FSTConfiguration.createDefaultConfiguration();
        serializer.setForceSerializable(true);
        serializer.setClassLoader(Thread.currentThread().getContextClassLoader());
    }

    public static byte[] serialize(Object t) {
        if (t == null) {
            return null;
        }
        return serializer.asByteArray(t);
    }

    @SuppressWarnings("unchecked")
    public static <T> T deserialize(byte[] bytes, Class<T> clazz) {
        if (bytes == null) {
            return null;
        }
        return (T) serializer.asObject(bytes);
    }

    public static <T> T copy(Object t, Class<T> clazz) {
        return deserialize(serialize(t), clazz);
    }

    public static <T> List<T> copy(List<T> list, Class<T> clazz) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list.stream().map(t -> copy(t, clazz)).collect(Collectors.toList());
    }

}
*/
