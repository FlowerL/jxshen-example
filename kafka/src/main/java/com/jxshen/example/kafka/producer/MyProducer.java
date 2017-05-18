package com.jxshen.example.kafka.producer;

import java.util.Date;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * reference from: http://www.cnblogs.com/tengpan-cn/p/5909709.html
 */
public class MyProducer {

    public static void sendMsg(String msg) {
        Properties props = new Properties();
        // broker list
        props.put("metadata.broker.list", "127.0.0.1:9092,127.0.0.1:9093");
        /* *
         * the serializer when preparing the message for transmission to the Broker
         * Note that the encoder must accept the same type 
         * as defined in the KeyedMessage object in the next step. 
         * 
         */
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        /* *
         * defines what class to use to determine 
         * which Partition in the Topic the message is to be sent to
         */
        props.put("partitioner.class", "example.producer.SimplePartitioner");
        /* *
         *  tells Kafka that you want your Producer to require an 
         *  acknowledgement from the Broker that the message was received
         */
        props.put("request.required.acks", "1");
        
        ProducerConfig config = new ProducerConfig(props);
        /*
         * Note that the Producer is a Java Generic and you need to tell it the type of two parameters.
         * The first is the type of the Partition key, the second the type of the message.
         */
        Producer<String, String> producer = new Producer<String, String>(config);

        long runtime = new Date().getTime();
        String ip = "127.0.0.1";
        /*
         * The “panteng” is the Topic to write to. 
         * Here we are passing the IP as the partition key.
         * Note that if you do not include a key, 
         * even if you've defined a partitioner class, Kafka will assign the message to a random partition.
         */
        KeyedMessage<String, String> data = new KeyedMessage<String, String>(
                "panteng", ip, msg);
        producer.send(data);
        producer.close();
    }
}
