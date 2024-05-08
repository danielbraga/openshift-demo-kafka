package com.redhat.demo.openshift.web.rest.controller;

import com.redhat.demo.openshift.web.rest.entidades.Assinatura;
import com.redhat.demo.openshift.web.rest.service.AssinaturaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/assinatura",produces = MediaType.APPLICATION_JSON_VALUE)
public class AssinaturaController extends BaseController{

	private final AssinaturaService assinaturaService;

    public AssinaturaController(AssinaturaService assinaturaService) {
		this.assinaturaService = assinaturaService;
    }

	@PostMapping(value = "/criar",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Assinatura> criar(@RequestBody Assinatura assinatura) {
		assinatura.setId(UUID.randomUUID().toString());
        assinaturaService.criar(assinatura);
		return ResponseEntity.ok().body(assinatura);
	}

}
