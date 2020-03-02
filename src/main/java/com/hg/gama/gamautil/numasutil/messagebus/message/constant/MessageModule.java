package com.hg.gama.gamautil.numasutil.messagebus.message.constant;

public enum MessageModule {
    WORK_FLOW("WORK_FLOW", "流程模块");
    //...

    private String key;
    private String name;

    MessageModule(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
