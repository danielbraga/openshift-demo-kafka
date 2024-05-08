package com.redhat.demo.openshift.web.rest.controller;

import com.redhat.demo.openshift.web.rest.entidades.Assinatura;
import com.redhat.demo.openshift.web.rest.service.AssinaturaService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "pages")
public class PagesController extends BaseController {

    private final AssinaturaService assinaturaService;

    public PagesController(AssinaturaService assinaturaService) {
        this.assinaturaService = assinaturaService;
    }
    @GetMapping(value = "/assinatura")
    public ModelMap assinatura(@PageableDefault(value = 10, page = 0) Pageable pageable) {
        ModelMap map = new ModelMap();
        map.put("assinatura",new Assinatura());
        return map;
    }

    @GetMapping(value = "/dashboard")
    public ModelMap dashboard() {
        ModelMap map = new ModelMap();


        return map;
    }

    @PostMapping(value = "/assinatura/criar")
    public String criar(@ModelAttribute Assinatura assinatura, Model model) throws Exception {
       criar(assinatura);
       return goPagesConsulta(assinatura,"Criada",model);
    }

    private void criar(Assinatura assinatura) throws Exception {
       assinaturaService.criar(assinatura);
    }

    private String goPagesConsulta(Assinatura assinatura, String acao, Model model) {
        Pageable pageable = Pageable.ofSize(100);

        return "pages/assinatura";
    }

}

