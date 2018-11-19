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
    
    
    

    /*@RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Conta> buscaConta(@PathVariable Long id){
        Optional<Conta> contaOptional = contaRepository.findById(id);
        return contaOptional.isPresent() ? ResponseEntity.ok(contaOptional.get()) :
                ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Conta> novaConta(@RequestBody Conta conta){
        if (conta.getCliente() == null || conta.getCliente().getId() == null)
            return ResponseEntity.badRequest().build();
        if (!clienteRepository.existsById(conta.getCliente().getId()))
            return ResponseEntity.notFound().build();
        Conta savedConta = contaRepository.save(conta);
        return ResponseEntity.ok(savedConta);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Conta> updateConta(@RequestBody Conta conta) {
        if (conta == null || conta.getId() == null)
            return ResponseEntity.badRequest().build();
        if (!contaRepository.existsById(conta.getId()))
            return ResponseEntity.notFound().build();

        Conta contaUpdated = contaRepository.save(conta);
        return ResponseEntity.ok(contaUpdated);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteConta(@PathVariable Long id) {
        if (id == null)
            return ResponseEntity.badRequest().build();
        if (!contaRepository.existsById(id))
            return ResponseEntity.notFound().build();

        contaRepository.deleteById(id);
        return ResponseEntity.ok("Conta removida com sucesso!");
    }*/
}
