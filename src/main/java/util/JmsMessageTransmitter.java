package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by rodicad on 25/04/2016.
 */
public class JmsMessageTransmitter {

    private static final Logger LOG = LoggerFactory.getLogger(JmsMessageTransmitter.class);

    private static final String JMS_X_GROUP_ID_HEADER_NAME = "JMSXGroupID";

    private JmsTemplate jmsTemplate;
    private String jmsConnectionString;
    private String jmsQueueName;

    public void sendMessage( final String message ) {
        LOG.info("Posting message for event [{}] to Queue [{}] on JMS broker [{}]", jmsQueueName, jmsConnectionString);

            jmsTemplate.send( new MessageCreator() {

                public Message createMessage( Session session ) {

                    Message msg = null;

                    try {
                        msg = session.createTextMessage(message);

                    }  catch ( JMSException e ) {
                        //TODO: Handle exception
                    }

                    return msg;
                }
        });
    }

    public void setJmsTemplate( JmsTemplate jmsTemplate ) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setJmsConnectionString( String jmsConnectionString ) {
        this.jmsConnectionString = jmsConnectionString;
    }

    public void setJmsQueueName( String jmsQueueName ) {
        this.jmsQueueName = jmsQueueName;
    }
}