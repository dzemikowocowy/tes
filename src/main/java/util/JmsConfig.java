package util;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

/**
 * Created by rodicad on 25/04/2016.
 */
public class JmsConfig {

    public static final String AMQ_CLIENT_USERNAME = "user";
    public static final String AMQ_CLIENT_PASSWORD = "password";

    private final String jmsConnectionString;
    private final String jmsQueueName;


    public JmsConfig( String jmsQueueName, String jmsConnectionString ) {
        this.jmsQueueName = jmsQueueName;
        this.jmsConnectionString = jmsConnectionString;
    }

    private ConnectionFactory cachingConnectionFactory() {
        ActiveMQConnectionFactory amqConnectionFactory = new ActiveMQConnectionFactory(
                AMQ_CLIENT_USERNAME,
                AMQ_CLIENT_PASSWORD,
                jmsConnectionString
        );

        return new CachingConnectionFactory( amqConnectionFactory );
    }

    private JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate( cachingConnectionFactory() );
        jmsTemplate.setDefaultDestination( queue() );
        return jmsTemplate;
    }

    private Queue queue() {
        return new ActiveMQQueue(jmsQueueName );
    }

    public JmsMessageTransmitter createJmsMessageTransmitter() {
        JmsMessageTransmitter transmitter = new JmsMessageTransmitter();
        transmitter.setJmsTemplate(jmsTemplate());
        transmitter.setJmsQueueName(jmsQueueName);
        transmitter.setJmsConnectionString(jmsConnectionString);
        return transmitter;
    }
}