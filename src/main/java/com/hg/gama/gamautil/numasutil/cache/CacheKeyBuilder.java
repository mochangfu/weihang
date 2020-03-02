package com.hg.gama.gamautil.numasutil.cache;

import java.math.BigDecimal;

public class CacheKeyBuilder {
    private StringBuilder sbr = new StringBuilder();

    public CacheKeyBuilder add(Object key) {
        if (key == null) {
            sbr.append("_null_");
        } else if (key instanceof String
                || key instanceof Integer
                || key instanceof Long
                || key instanceof Boolean
                || key instanceof Double
                || key instanceof Float
                || key instanceof Short
                || key instanceof Byte) {
            sbr.append(key);
        } else if (key instanceof BigDecimal) {
            sbr.append(((BigDecimal) key).stripTrailingZeros().toPlainString());
        } else {
            sbr.append(key.hashCode());
        }
        sbr.append("^^");
        return this;
    }

    public String build() {
        return sbr.toString();
    }

    @Override
    public String toString() {
        return build();
    }
}