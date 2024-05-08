package com.redhat.demo.openshift.kafkaemail;

import org.apache.camel.component.kafka.KafkaComponent;
import org.apache.camel.component.kafka.KafkaConfiguration;
import org.apache.camel.main.Main;

public class Application {

    public static void main(String[] args) throws Exception {
        //kafka from
        KafkaComponent kafkaComponentFrom = new KafkaComponent();
        KafkaConfiguration kafkaConfigurationFrom = new KafkaConfiguration();
        kafkaConfigurationFrom.setBrokers(getSystemFrom());
        kafkaComponentFrom.setConfiguration(kafkaConfigurationFrom);

        //kafka to
        KafkaComponent kafkaComponentTo = new KafkaComponent();
        KafkaConfiguration kafkaConfigurationTo = new KafkaConfiguration();
        kafkaConfigurationTo.setBrokers(getSystemTo());
        kafkaComponentTo.setConfiguration(kafkaConfigurationTo);

        Main main = new Main();
        main.bind("kafka", kafkaComponentFrom);
        main.bind("kafka-to", kafkaComponentTo);
        main.configure().addRoutesBuilder(new EmailRouteBuilder());
        main.run();
    }

    private static String getSystemFrom() {
        return System.getProperty("broker.from", "tcp://192.168.0.13:29092");
    }

    private static String getSystemTo() {
        return System.getProperty("broker.to", getSystemFrom());
    }

}
