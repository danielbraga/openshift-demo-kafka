package com.redhat.demo.openshift.kafkadatabase;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SimpleProcessor implements Processor {

    private Gson gson = new Gson();

    @Override
    public void process(Exchange exchange) throws Exception {
        String json = (String) exchange.getIn().getBody();
        System.out.println("JSON: " + json);
        JsonElement jsonElement = gson.toJsonTree(json);
//        jsonElement.getAsJsonObject().addProperty("processor", "database");
        exchange.getOut().setBody(json);
    }
}
