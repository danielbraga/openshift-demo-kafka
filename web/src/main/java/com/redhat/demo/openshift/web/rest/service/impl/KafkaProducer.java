package com.redhat.demo.openshift.web.rest.service.impl;

import com.redhat.demo.openshift.web.rest.service.KafkaGenericProducer;

public class KafkaProducer extends KafkaGenericProducer<String> {

    @Override
    protected String getTopico() {
        return "assinatura_cadastro";
    }
}
