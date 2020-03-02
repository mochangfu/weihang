package com.hg.gama.boot.config;

public interface OrderConfig {
    int LOWEST_ORDER = 10000;//最低优先级

    int TRANSACTION_ORDER = LOWEST_ORDER;
    int CACHE_ORDER = TRANSACTION_ORDER - 1;
    int VALIDATION_ORDER = CACHE_ORDER - 1; //优先级比缓存高

    int UNAUTH_DISPATCHER_ORDER = LOWEST_ORDER;
    int DEFAULT_DISPATCHER_ORDER = UNAUTH_DISPATCHER_ORDER - 1;

}
