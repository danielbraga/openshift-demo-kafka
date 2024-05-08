package com.redhat.demo.openshift.kafkadatabase;

import org.apache.camel.builder.RouteBuilder;

public class DatabaseRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // lets shutdown faster in case of in-flight messages stack up
        getContext().getShutdownStrategy().setTimeout(10);

        from("kafka:" + getSystemTopicFrom() + "?groupId=" + getGroupId() + "&autoOffsetReset=earliest")
                .process(new SimpleProcessor()).to("kafka:" + getSystemTopicTo() + "?groupId=" + getGroupId())
                .log("Kafka Migracao complete.");
    }

    private String getSystemTopicFrom() {
        return System.getProperty("topic.from", "assinatura_cadastro");
    }

    private String getSystemTopicTo() {
        String to = System.getProperty("topic.to", "assinatura_database");
        return to;
    }

    private String getGroupId() {
        return System.getProperty("topic.group", "kafkadatabase");
    }

}
