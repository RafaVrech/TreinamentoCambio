package com.ibm.cambio.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.cambio.model.Conta;
import com.ibm.cambio.service.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {
    private ContaService contaService;

    @Autowired
    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> buscarConta(@PathVariable Long id) 
    {
        return ResponseEntity.ok(new Resposta(0, "", contaService.buscarConta(id)));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Object> buscaTodasConta(
							@RequestParam(value = "filtro", required = false) String filtro) 
    {
        return ResponseEntity.ok(new Resposta(0, "", contaService.buscarTodasConta(filtro)));
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> novaConta(@RequestBody Conta conta) 
    {
        return ResponseEntity.ok(new Resposta(0, "", contaService.salvarConta(conta)));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    public ResponseEntity<Object> updateConta(@RequestBody Conta conta) 
    {
        return ResponseEntity.ok(new Resposta(0, "", contaService.atualizarConta(conta)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteConta(@PathVariable Long id) 
    {
       contaService.deletarConta(id);
       return ResponseEntity.ok(new Resposta(0, "", "Conta removido com sucesso!")); 
    }
}
