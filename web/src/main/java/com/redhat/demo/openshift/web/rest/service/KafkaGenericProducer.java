package com.redhat.demo.openshift.web.rest.service;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public abstract class KafkaGenericProducer<T> {

    private final CountDownLatch shutdownLatch;

    protected final String KAFKA_SERVER = System.getProperty("OP_DEMO_KAFKA_ADDRESS", getServer());

    public KafkaGenericProducer() {
        this.shutdownLatch = new CountDownLatch(1);
    }

    final Producer producer = createProducer();

    protected abstract String getTopico();

    protected String getServer() {
        return "my-cluster-kafka-brokers.default.svc.cluster.local:9092";
    }

    public void send(T value){
        send(null,value);
    }
    public void send(Long key,T value){

        final ProducerRecord<Long, T> record = new ProducerRecord<>(getTopico(), key, value);
        Future<RecordMetadata> future = producer.send(record);
        try {
            future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    protected Producer<Long, String> createProducer() {
        final Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, serializer());
        props.put(ProducerConfig.ACKS_CONFIG, "all");

        return new KafkaProducer<>(props);
    }

    protected String serializer(){
        return StringSerializer.class.getName();
    }

    public void shutdown() throws InterruptedException {
        producer.close();
        shutdownLatch.await();
    }

}
