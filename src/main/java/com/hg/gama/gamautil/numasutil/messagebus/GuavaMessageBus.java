package com.hg.gama.gamautil.numasutil.messagebus;

import com.google.common.collect.Maps;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import com.hg.gama.gamautil.numasutil.util.ExecutorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

public class GuavaMessageBus implements MessageBus {

    private static final Logger logger = LoggerFactory.getLogger(GuavaMessageBus.class);

    private AsyncEventBus eventBus = new AsyncEventBus(ExecutorUtil.createExecutor(GuavaMessageBus.class.getSimpleName()));

    private Map<String, GuavaListener> listenerMap = Maps.newConcurrentMap();

    private boolean aware = true;

    public boolean isAware() {
        return aware && MessageBusAwareProvider.get() != null;
    }

    public void setAware(boolean aware) {
        this.aware = aware;
    }

    @Override
    public void register(IMessageListener listener) {
        GuavaListener oldListener = listenerMap.get(listener.getClass().getName());
        if (oldListener != null) {
            listenerMap.remove(listener.getClass().getName());
            eventBus.unregister(oldListener);
        }
        GuavaListener guavaListener = new GuavaListener(listener);
        eventBus.register(guavaListener);
        listenerMap.put(listener.getClass().getName(), guavaListener);
    }

    @Override
    public void send(Message message) {
        if (isAware()) {
            MessageBusAwareProvider.get().beforeSend(message);
        }
        eventBus.post(message);
        if (isAware()) {
            MessageBusAwareProvider.get().afterSend(message);
        }
        logger.info("Message {}(type:{}) has been sent from local message bus.", message.getMessageId(),
                message.getBody() == null ? "null" :
                        message.getBody().getClass().getName());
    }

    private static class GuavaListener {
        private IMessageListener listener;

        public GuavaListener(IMessageListener listener) {
            this.listener = listener;
        }

        private static String getSetFormattedString(Set<String> set) {
            StringBuilder sb = new StringBuilder();
            sb.append('{');
            if (set != null) {
                for (String s : set) {
                    if (sb.length() > 1) {
                        sb.append(',');
                    }
                    sb.append(s);
                }
            }
            sb.append('}');
            return sb.toString();
        }

        @Subscribe
        public void execute(Message msg) {
            try {
                if (msg == null) {
                    logger.error("Message name is empty.");
                    return;
                }
                if (listener.getMessageType().equals(msg.getBody().getClass().getName())) {
                    logger.info("Message {}(type:{}) has been received by {}.", msg.getMessageId(),
                            msg.getBody() == null ? "null" :
                                    msg.getBody().getClass().getName(), listener.getClass().getName());

                    if (msg.getTokens().isEmpty() || msg.getTokens().contains(listener.getToken())) {
                        if (MessageBusAwareProvider.get() != null) {
                            MessageBusAwareProvider.get().beforeReceive(msg);
                        }
                        listener.consumeMessage(msg.getBody());
                        if (MessageBusAwareProvider.get() != null) {
                            MessageBusAwareProvider.get().afterReceive(msg);
                        }

                    } else {
                        logger.info("Message token {} is not matched {}", getSetFormattedString(msg.getTokens()), listener.getToken());
                    }
                }
            } catch (Exception e) {
                logger.error("Consume messaeg failed!", e);
            }
        }
    }
}
