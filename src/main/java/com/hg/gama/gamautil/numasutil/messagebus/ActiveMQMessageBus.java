package com.hg.gama.gamautil.numasutil.messagebus;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import com.hg.gama.gamautil.numasutil.util.JsonUtil;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import javax.jms.*;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.Executors;

public class ActiveMQMessageBus implements MessageBus, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQMessageBus.class);

    private PooledConnectionFactory connectionFactory;

    @Value("${numas.activemq.connector:failover:(tcp://127.0.0.1:61616)}")
    private String activeMqUrl;

    private Map<String, ActiveMqMessageSubscribeTask> listenerMap = Maps.newConcurrentMap();

    @Override
    public void register(IMessageListener listener) {
        ActiveMqMessageSubscribeTask task = new ActiveMqMessageSubscribeTask(connectionFactory, listener);
        listenerMap.put(listener.getMessageType(), task);
        Executors.newCachedThreadPool().execute(task);
    }

    @Override
    public void send(Message message) {
        if (MessageBusAwareProvider.get() != null) {
            MessageBusAwareProvider.get().beforeSend(message);
        }
        Connection connection = null;
        try {
            // Create a Connection
            connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createTopic(message.getMessageType());

            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            TextMessage msg = session.createTextMessage(JsonUtil.toJson(message));
            producer.send(msg);
            logger.info("消息 {}(type:{}) 已发送.", message.getMessageId(),
                    message.getBody() == null ? "null" :
                            message.getBody().getClass().getName());
        } catch (Exception e) {
            logger.error("发送消息失败!", e);
        } finally {
            closeJmsConnection(connection);
        }
        if (MessageBusAwareProvider.get() != null) {
            MessageBusAwareProvider.get().afterSend(message);
        }
    }

    private void closeJmsConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (JMSException e) {
                logger.error("JMS exception!", e);
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //        guavaMessageBus.setAware(false);
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(activeMqUrl);

        connectionFactory = new PooledConnectionFactory(activeMQConnectionFactory);

        //        ExecutorUtil.createSingleExecutor(ActiveMQMessageBus.class.getSimpleName()).execute(new ActiveMqMessageSubscribeTask(connectionFactory));
    }

    private class ActiveMqMessageSubscribeTask implements Runnable, ExceptionListener {
        private PooledConnectionFactory factory;
        private Connection connection;
        private Session session;
        private Destination destination;
        private MessageConsumer consumer;
        private IMessageListener listener;

        public ActiveMqMessageSubscribeTask(PooledConnectionFactory factory, IMessageListener listener) {
            this.listener = listener;
            this.factory = factory;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    if (connection == null) {
                        newConnection();
                    }

                    // Wait for a message
                    javax.jms.Message message = consumer.receive();
                    if (message instanceof TextMessage) {
                        try {
                            String text = ((TextMessage) message).getText();
                            JsonNode jsonNode = JsonUtil.toObject(text);
                            String messageId = jsonNode.get("messageId").textValue();
                            String messageType = jsonNode.get("messageType").textValue();

                            logger.info("消息 {}(type:{}) 已接收.", messageId, messageType);

                            if (listener.getMessageType().equals(messageType)) {
                                JsonNode boday = jsonNode.get("body");
                                Object msg = JsonUtil.getMapper().treeToValue(boday, Class.forName(messageType));
                                listener.consumeMessage((Serializable) msg);
                            }
                        } catch (Exception e) {
                            logger.error("处理消息失败", e);
                        }
                    }
                } catch (Exception e) {
                    if (e instanceof JMSException) {
                        logger.error("ActiveMQ 已断连! " + e.getMessage());
                    } else {
                        logger.error("消息消费失败!", e);
                    }
                    closeConnection();
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e1) {
                        logger.error("Thread sleep failed!", e1);
                    }
                }
            }
        }

        private void closeConnection() {
            try {
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (JMSException e) {
                // do nothing
            }
        }

        private void newConnection() throws JMSException {
            connection = factory.createConnection();
            connection.start();

            connection.setExceptionListener(this);

            // Create a Session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            destination = session.createTopic(listener.getMessageType());

            // Create a MessageConsumer from the Session to the Topic or Queue
            consumer = session.createConsumer(destination);
        }

        @Override
        public void onException(JMSException exception) {
            logger.error("ActiveMQ 连接失败! " + exception.getMessage());
        }
    }
}
