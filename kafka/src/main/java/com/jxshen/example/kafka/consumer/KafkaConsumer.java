package com.jxshen.example.kafka.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

/**
 * reference from: http://www.cnblogs.com/tengpan-cn/p/5909709.html
 */
public class KafkaConsumer {

    private final ConsumerConnector consumer;
    
    public KafkaConsumer() {
        Properties props = new Properties();
        // zookeeper config
        props.put("zookeeper.connect", "127.0.0.1:2181");
        // group represent a consumer group
        props.put("group.id", "jd-group");
        
        //zk connect timeout
        props.put("zookeeper.session.timeout.ms", "4000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "smallest");
        
        // Serializable class
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        ConsumerConfig config = new ConsumerConfig(props);
        consumer = Consumer.createJavaConsumerConnector(config);
    }
    
    public void consumer() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put("panteng", new Integer(1));
        
        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());
        
        Map<String, List<KafkaStream<String, String>>> consumerMap =
                consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
        KafkaStream<String, String> stream = consumerMap.get("panteng").get(0);
        ConsumerIterator<String, String> it = stream.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().message());
        }
    }
    
    public void stop() {
        consumer.shutdown();
    }
    
    public static void main(String[] args) {
        new KafkaConsumer().consumer();
    }
}
