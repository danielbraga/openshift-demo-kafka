package com.redhat.demo.openshift.kafkaemail;

import org.apache.camel.builder.RouteBuilder;

public class EmailRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // lets shutdown faster in case of in-flight messages stack up
        getContext().getShutdownStrategy().setTimeout(10);

        from("kafka:" + getSystemTopicFrom() + "?groupId=" + getGroupId() + "&autoOffsetReset=earliest")
                .process(new SimpleProcessor()).to("kafka:" + getSystemTopicTo() + "?groupId=" + getGroupId());
    }

    private String getSystemTopicFrom() {
        return System.getProperty("topic.from", "assinatura_cadastro");
    }

    private String getSystemTopicTo() {
        String to = System.getProperty("topic.to", "assinatura_email");
        return to;
    }

    private String getGroupId() {
        return System.getProperty("topic.group", "kafkaemail");
    }
}
