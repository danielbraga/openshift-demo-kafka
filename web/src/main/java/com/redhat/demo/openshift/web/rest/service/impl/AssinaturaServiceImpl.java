package com.redhat.demo.openshift.web.rest.service.impl;

import com.google.gson.Gson;
import com.redhat.demo.openshift.web.rest.entidades.Assinatura;
import com.redhat.demo.openshift.web.rest.service.AssinaturaService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AssinaturaServiceImpl implements AssinaturaService {

    private KafkaProducer kafkaProducer;

    private Gson gson;

    public AssinaturaServiceImpl() {
        kafkaProducer = new KafkaProducer();
        gson = new Gson();
    }

    @Override
    public void criar(Assinatura assinatura) {
        assinatura.setId(UUID.randomUUID().toString());
        kafkaProducer.send(gson.toJson(assinatura));
    }
}
