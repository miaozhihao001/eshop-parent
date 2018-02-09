package com.zhihao.miao.cache.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 * @Auther : 苗志浩 (zhihao.miao)
 * @Date :2018/2/9
 * @since 1.0
 */
public class KafkaConsumer implements Runnable {

    private ConsumerConnector consumerConnector;
    private String topic;

    public KafkaConsumer(String topic) {
        this.consumerConnector = Consumer.createJavaConsumerConnector(createConsumerConfig());
        this.topic = topic;
    }

    @SuppressWarnings("rawtypes")
    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<>();
        //打算用几个线程去消费
        topicCountMap.put(topic, 1);

        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap =
                consumerConnector.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

        for (KafkaStream stream : streams) {
            //消费消息的具体逻辑
            new Thread(new KafkaMessageProcessor(stream)).start();
        }
    }

    /**
     * 创建kafka cosumer config
     * @return
     */
    private static ConsumerConfig createConsumerConfig() {
        Properties props = new Properties();
        props.put("zookeeper.connect", "192.168.1.88:2181,192.168.1.96:2181,192.168.1.55:2181");
        props.put("group.id", "eshop-cache-group");
        props.put("zookeeper.session.timeout.ms", "40000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        return new ConsumerConfig(props);
    }

}

