package com.hg.gama.gamautil.numasutil.messagebus.message.constant;

public enum BaseStatus {

    ENABLE(1, "有效"),
    DISABLE(0, "无效");

    private int val;
    private String def;

    BaseStatus(int val, String def) {
        this.val = val;
        this.def = def;
    }

    public int getVal() {
        return val;
    }

    public String getDef() {
        return def;
    }
}


